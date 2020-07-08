package app.service;

import app.DTO.NiveauDTO;
import app.DTO.PaginatedPassesDTO;
import app.DTO.PasseDTO;
import app.DTO.PasseFilterDTO;
import app.entity.Niveau;
import app.entity.Passe;
import app.repository.NiveauRepository;
import app.repository.PasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NiveauServiceImpl implements NiveauService {

    @Autowired
    private NiveauRepository niveauRepository;

    @Override
    public List<NiveauDTO> findAllOrderByIdNiveauAsc() {
        List<NiveauDTO> result = new ArrayList<>();
        List<Niveau> icones = niveauRepository.findAllByOrderByIdniveauAsc();
        result = icones.stream().map(NiveauDTO::new).collect(Collectors.toList());
        return result;
    }
}
