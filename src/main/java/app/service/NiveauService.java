package app.service;

import app.DTO.NiveauDTO;
import app.DTO.PaginatedPassesDTO;
import app.DTO.PasseDTO;
import app.DTO.PasseFilterDTO;

import java.util.HashMap;
import java.util.List;

public interface NiveauService {
    public List<NiveauDTO> findAllOrderByIdNiveauAsc();
}
