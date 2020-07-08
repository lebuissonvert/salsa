package app.service;

import app.DTO.BackupPasseDTO;
import app.DTO.PaginatedPassesDTO;
import app.DTO.PasseDTO;
import app.DTO.PasseFilterDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface BackupPasseService {
    public Integer createBackup() throws JsonProcessingException;
    public Integer restoreById(Integer id) throws IOException;
}
