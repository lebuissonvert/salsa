package app.service;

import app.DTO.PaginatedPassesDTO;
import app.DTO.PasseDTO;
import app.DTO.PasseFilterDTO;

import java.util.HashMap;
import java.util.List;

public interface PasseService {
    public PaginatedPassesDTO findAllPaginated(int p_page, int p_pageSize, String sortField, String sortOrder);
    public PaginatedPassesDTO findAllPaginated(
            int p_page, int p_pageSize,
            String sortField, String sortOrder,
            HashMap<String, PasseFilterDTO> filterMap);
    public List<PasseDTO> findAllByOrderByIdAsc();
    public PasseDTO createPasse(PasseDTO passeDTO);
    public PasseDTO editPasse(PasseDTO passeDTO);
    public void deleteAllInBatch();
    public Integer saveAll(List<PasseDTO> passeDTO);
}
