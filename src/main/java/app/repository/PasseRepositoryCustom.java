package app.repository;

import app.DTO.PasseFilterDTO;
import app.entity.Passe;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface PasseRepositoryCustom {
    public List<Passe> findAllFiltered(Pageable pageable, HashMap<String, PasseFilterDTO> filters);
    public long count(HashMap<String, PasseFilterDTO> filters);
}
