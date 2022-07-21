package app.controller;

import app.DTO.*;
import app.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(
        //origins = "http://localhost:4200",
        origins = {"http://passes-salsa.ddns.net", "http://romain33.ddns.net"},
        //origins = "http://passes-salsa.ddns.net",
        allowedHeaders = "*",
        allowCredentials = "true"
)
public class RestController {

    @Autowired
    PasseService passeService;
    @Autowired
    BackupPasseService backupPasseService;
    @Autowired
    NiveauService niveauService;
    @Autowired
    TypePasseService typepasseService;
    @Autowired
    StatService statService;

    // application.properties
    @Value("${spring.jpa.show-sql}")
    private Boolean showSQL;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    // application.yml
    @Value("${app.yml.string}")
    private String _string;
    @Value("${app.yml.boolean.true}")
    private Boolean _true;
    @Value("${app.yml.boolean.false}")
    private Boolean _false;
    @Value("#{${app.yml.map.property-name}}")
    private Map<String,String> propertyMap;

    private ObjectMapper jacksonMapper = new ObjectMapper();

    @ResponseBody
    @RequestMapping("/")
    public String home() {
        return getHomeContent();
    }

    @ResponseBody
    @RequestMapping(
            value = "/createPasse",
            produces = "application/json",
            method = RequestMethod.POST)
    public String createPasse(@RequestBody PasseDTO p_passe)
            throws JsonProcessingException {
        PasseDTO insertedPasseDTO = null;
        if(p_passe != null) {
            insertedPasseDTO = passeService.createPasse(p_passe);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling 'createPasse' : p_passe vaut null");
        }

        return jacksonMapper.writeValueAsString(insertedPasseDTO);
    }

    @ResponseBody
    @RequestMapping(
            value = "/savePasse",
            produces = "application/json",
            method = RequestMethod.POST)
    public String savePasse(@RequestBody PasseDTO p_passe)
            throws JsonProcessingException {
        PasseDTO editedPasseDTO = null;
        if(p_passe != null) {
            if(p_passe.getId() != null) {
                editedPasseDTO = passeService.editPasse(p_passe);
            } else {
                editedPasseDTO = passeService.createPasse(p_passe);
            }

            // Une fois la modif/creation effectuée, on effectue un backup
            backupPasseService.createBackup();

            // Puis on ajoute la stat
            if(p_passe.getId() != null) {
                StatDTO statDTO = new StatDTO("MODIFICATION", p_passe.getId());
                statService.createStat(statDTO);
            } else {
                StatDTO statDTO = new StatDTO("CREATION", editedPasseDTO.getId());
                statService.createStat(statDTO);
            }

        } else {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling 'savePasse' : p_passe vaut null");
        }

        return jacksonMapper.writeValueAsString(editedPasseDTO);
    }

    @ResponseBody
    @RequestMapping(
            value = "/readPasse",
            produces = "application/json")
    public String readPasse(
            @RequestParam(value = "id", defaultValue = "") String p_idPasse
        )
            throws JsonProcessingException {
        PasseDTO editedPasseDTO = null;
        if(p_idPasse != null) {
            StatDTO statDTO = new StatDTO("CONSULTATION", new Integer(p_idPasse));
            statService.createStat(statDTO);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling 'readPasse' : p_idPasse vaut null");
        }

        return jacksonMapper.writeValueAsString(editedPasseDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/showAllPassePaginated", produces = "application/json")
    public String showAllPassePaginated(
            @RequestParam(value = "page", defaultValue = "") String p_page,
            @RequestParam(value = "size", defaultValue = "") String p_pagesize,
            @RequestParam(value = "sortfield", defaultValue = "id") String p_sortField,
            @RequestParam(value = "sortorder", defaultValue = "1") String p_sortOrder,
            @RequestParam(value = "filters", defaultValue = "") String p_filters
    ) throws JsonProcessingException {
        try {
            if(!p_page.isEmpty() && !p_pagesize.isEmpty()) {
                int page_int = Integer.parseInt(p_page);
                int pagesize_int = Integer.parseInt(p_pagesize);
                String sortOrder = p_sortOrder.equals("-1") ? "DESC" : "ASC";
                String sortField = p_sortField.equals("undefined") ? "id" : p_sortField;
                PaginatedPassesDTO paginatedUsersDTO = null;
                if(p_filters.isEmpty() || p_filters.equals("{}")) {
                    paginatedUsersDTO = passeService.findAllPaginated(
                            page_int, pagesize_int, sortField, sortOrder);
                } else {
                    HashMap<String, PasseFilterDTO> filterHashMap = toMapPasseFilterDTO(p_filters);
                    paginatedUsersDTO = passeService.findAllPaginated(
                            page_int, pagesize_int, sortField, sortOrder, filterHashMap);
                }
                return jacksonMapper.writeValueAsString(paginatedUsersDTO);
            }
            else {
                Iterable<PasseDTO> usersDTO = passeService.findAllByOrderByIdAsc();
                return jacksonMapper.writeValueAsString(usersDTO);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling 'showAllUserPaginated' : " + e.getMessage(), e);
        }
    }

    // {"nom":{"value":"a","matchMode":"contains"},"cavalier":{"value":"faire","matchMode":"contains"}}
    private HashMap<String /*field*/, PasseFilterDTO /*criteres*/> toMapPasseFilterDTO(String p_filterStr)
            throws IOException {
        HashMap<String, PasseFilterDTO> result = new HashMap<>();

        HashMap<String,Object> mainMap = new ObjectMapper().readValue(p_filterStr, HashMap.class);
        for(Map.Entry<String, Object> entry : mainMap.entrySet()) {
            LinkedHashMap<String, Object> subMap = (LinkedHashMap<String, Object>) mainMap.get(entry.getKey());
            result.put(
                    entry.getKey(),
                    new PasseFilterDTO(subMap.get("value").toString(), subMap.get("matchMode").toString()));
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/backupAllPasses", produces = "application/json")
    public String backupAllPasses() throws JsonProcessingException {
        // enregistrement d'un json dans backup_passes
        Integer idBackup = backupPasseService.createBackup();

        return jacksonMapper.writeValueAsString(idBackup);
    }

    @ResponseBody
    @RequestMapping(value = "/restoreBackupPasses", produces = "application/json")
    public String restoreBackupPasses(
            @RequestParam(value = "id", defaultValue = "") String p_id
    ) throws IOException {
        Integer nbPassesRestored = backupPasseService.restoreById(new Integer(p_id));
        return jacksonMapper.writeValueAsString(nbPassesRestored);
    }

    @ResponseBody
    @RequestMapping(value = "/showAllNiveau", produces = "application/json")
    public String showAllNiveau() throws JsonProcessingException {
        try {
            Iterable<NiveauDTO> niveauxDTO = niveauService.findAllOrderByIdNiveauAsc();
            return jacksonMapper.writeValueAsString(niveauxDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Exception while calling 'showAllNiveau'", e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/showAllTypePasse", produces = "application/json")
    public String showAllTypePasse() throws JsonProcessingException {
        try {
            Iterable<TypePasseDTO> typePasseDTOs = typepasseService.findAllOrberByCodeTypePasseAsc();
            return jacksonMapper.writeValueAsString(typePasseDTOs);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Exception while calling 'showAllTypePasse'", e);
        }
    }

    private String getHomeContent() {
        String html = "";
        html += "<ul>";
        html += " <li><a href='/null'>Rien pour l'instant</a></li>";
        html += "</ul>";
        return html;
    }
}
