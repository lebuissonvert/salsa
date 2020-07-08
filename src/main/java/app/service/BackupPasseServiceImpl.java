package app.service;

import app.DTO.BackupPasseDTO;
import app.DTO.PasseDTO;
import app.entity.BackupPasse;
import app.repository.BackupPasseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BackupPasseServiceImpl implements BackupPasseService {

    @Autowired
    private BackupPasseRepository backupPasseRepository;
    @Autowired
    private PasseService passeService;

    private ObjectMapper jacksonMapper = new ObjectMapper();

    @Override
    public Integer createBackup() throws JsonProcessingException {
        Integer result = null;

        // Creation du backupDTO depuis l'ensemble des passes
        List<PasseDTO> passesDTO = passeService.findAllByOrderByIdAsc();
        byte[] curBackup = jacksonMapper.writeValueAsBytes(passesDTO);
        BackupPasseDTO backupPasseDTO = new BackupPasseDTO(
                null, curBackup, passesDTO.size(), new Long(curBackup.length), new Date());

        // Enregistrement de l'objet dans la table de backup
        BackupPasse backupPasse = new BackupPasse();
        backupPasse.setIdbackup(backupPasseDTO.getIdbackup());
        backupPasse.setBackup(backupPasseDTO.getBackup());
        backupPasse.setNbpasses(backupPasseDTO.getNbpasses());
        backupPasse.setBackupsize(backupPasseDTO.getBackupsize());
        backupPasse.setHorodatage(backupPasseDTO.getHorodatage());

        BackupPasse savedBackupPasse = backupPasseRepository.save(backupPasse);
        if(savedBackupPasse!=null) {
            result = savedBackupPasse.getIdbackup();
        }

        return result;
    }

    @Override
    public Integer restoreById(Integer p_id) throws IOException {
        Integer result = null;
        // récupération du backup
        Optional<BackupPasse> backupopt = backupPasseRepository.findById(p_id);
        if(backupopt.isPresent()) {
            BackupPasseDTO backupPasseDTO = new BackupPasseDTO(backupopt.get());
            String passesJson = new String(backupPasseDTO.getBackup(), StandardCharsets.UTF_8);
            List<PasseDTO> passesDTO = jacksonMapper.readValue(passesJson, new TypeReference<List<PasseDTO>>(){});

            // delete + restore de la table passe
            passeService.deleteAllInBatch();
            result = passeService.saveAll(passesDTO);
        }
        return result;
    }
}
