package app.service;

import app.DTO.BackupPasseDTO;
import app.DTO.PaginatedPassesDTO;
import app.DTO.PasseDTO;
import app.DTO.PasseFilterDTO;
import app.entity.Niveau;
import app.entity.Passe;
import app.entity.TypePasse;
import app.repository.NiveauRepository;
import app.repository.PasseRepository;
import app.repository.TypePasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PasseServiceImpl implements PasseService {

    @Autowired
    private PasseRepository passeRepository;
    @Autowired
    private NiveauRepository niveauRepository;
    @Autowired
    private TypePasseRepository typePasseRepository;

    @Override
    public PaginatedPassesDTO findAllPaginated(int p_page, int p_pageSize, String sortField, String sortOrder) {
        Sort sort = new Sort(Sort.Direction.fromString(sortOrder), sortField);
        Pageable pageable = PageRequest.of(p_page, p_pageSize, sort/*, Sort.by("id").descending()*/);
        // Retreive users
        Page<Passe> passes = passeRepository.findAll(pageable);
        List<Passe> passesList = new ArrayList<>();
        passes.forEach(passesList::add);
        // Retreive count
        long totalRecords = passeRepository.count();
        // Return result
        return new PaginatedPassesDTO(passesList, totalRecords);
    }

    @Override
    public PaginatedPassesDTO findAllPaginated(
            int p_page, int p_pageSize, String sortField, String sortOrder,
            HashMap<String, PasseFilterDTO> filterMap) {
        Sort sort = new Sort(Sort.Direction.fromString(sortOrder), sortField);
        Pageable pageable = PageRequest.of(p_page, p_pageSize, sort/*, Sort.by("id").descending()*/);
        // Retreive users
        List<Passe> passesList = passeRepository.findAllFiltered(pageable, filterMap);
        // Retreive count
        long totalRecords = passeRepository.count(filterMap);
        // Return result
        return new PaginatedPassesDTO(passesList, totalRecords);
    }

    @Override
    public List<PasseDTO> findAllByOrderByIdAsc() {
        List<PasseDTO> result = new ArrayList<>();
        Iterable<Passe> passes = passeRepository.findAllByOrderByIdAsc();
        for(Passe curPasse : passes) {
            result.add(new PasseDTO(curPasse));
        }
        return result;
    }

    @Override
    public PasseDTO createPasse(PasseDTO p_passeDTO) {
        PasseDTO result = null;
        java.util.Optional<Niveau> optNiveau = niveauRepository.findById(p_passeDTO.getNiveau().getIdniveau());
        java.util.Optional<TypePasse> optTypePasse = typePasseRepository.findById(p_passeDTO.getTypepasse().getIdtypepasse());
        if(optNiveau.isPresent() && optTypePasse.isPresent()) {
            Passe passe = new Passe();
            passe.setCavalier(p_passeDTO.getCavalier());
            passe.setCavaliere(p_passeDTO.getCavaliere());
            passe.setId(p_passeDTO.getId());
            passe.setNiveau(optNiveau.get());
            passe.setTypepasse(optTypePasse.get());
            passe.setNom(p_passeDTO.getNom());
            passe.setVideo(p_passeDTO.getVideo());
            Passe savedPasse = passeRepository.save(passe);
            if (savedPasse != null) {
                result = new PasseDTO(savedPasse);
            }
        }
        return result;
    }

    @Override
    public PasseDTO editPasse(PasseDTO p_passeDTO) {
        PasseDTO result = null;
        java.util.Optional<Passe> optPasse = passeRepository.findById(p_passeDTO.getId());
        java.util.Optional<Niveau> optNiveau = niveauRepository.findById(p_passeDTO.getNiveau().getIdniveau());
        java.util.Optional<TypePasse> optTypePasse = typePasseRepository.findById(p_passeDTO.getTypepasse().getIdtypepasse());
        if(optPasse.isPresent() && optNiveau.isPresent()
                && optTypePasse.isPresent() && optTypePasse.isPresent()) {
            Passe passe = optPasse.get();
            passe.setCavalier(p_passeDTO.getCavalier());
            passe.setCavaliere(p_passeDTO.getCavaliere());
            passe.setId(p_passeDTO.getId());
            passe.setNiveau(optNiveau.get());
            passe.setTypepasse(optTypePasse.get());
            passe.setNom(p_passeDTO.getNom());
            passe.setVideo(p_passeDTO.getVideo());
            Passe savedPasse = passeRepository.save(passe);
            if (savedPasse != null) {
                result = new PasseDTO(savedPasse);
            }
        }
        return result;
    }

    @Override
    public void deleteAllInBatch() {
        passeRepository.deleteAllInBatch();
    }

    @Override
    public Integer saveAll(List<PasseDTO> p_passeDTO) {
        List<Passe> passes = new ArrayList<>();
        for(PasseDTO curPasseDTO : p_passeDTO) {
            Passe curPasse = new Passe();
            curPasse.setCavalier(curPasseDTO.getCavalier());
            curPasse.setCavaliere(curPasseDTO.getCavaliere());
            curPasse.setId(curPasseDTO.getId());
            Niveau curNiveau = new Niveau();
            curNiveau.setIdniveau(curPasseDTO.getNiveau().getIdniveau());
            curNiveau.setCodeniveau(curPasseDTO.getNiveau().getCodeniveau());
            curPasse.setNiveau(curNiveau);
            TypePasse curTypePasse = new TypePasse();
            curTypePasse.setIdtypepasse(curPasseDTO.getTypepasse().getIdtypepasse());
            curTypePasse.setCodetypepasse(curPasseDTO.getTypepasse().getCodetypepasse());
            curPasse.setTypepasse(curTypePasse);
            curPasse.setNom(curPasseDTO.getNom());
            curPasse.setVideo(curPasseDTO.getVideo());
            passes.add(curPasse);
        }
        return passeRepository.saveAll(passes).size();
    }
}
