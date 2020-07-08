package app.service;

import app.DTO.NiveauDTO;
import app.DTO.TypePasseDTO;

import java.util.List;

public interface TypePasseService {
    public List<TypePasseDTO> findAllOrberByCodeTypePasseAsc();
}
