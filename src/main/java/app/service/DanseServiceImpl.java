package app.service;

import app.DTO.DanseDTO;
import app.entity.Danse;
import app.repository.DanseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DanseServiceImpl implements DanseService {

    @Autowired
    private DanseRepository danseRepository;

    @Override
    public List<DanseDTO> findAllOrderByIdDanseAsc() {
        List<DanseDTO> result = new ArrayList<>();
        List<Danse> icones = danseRepository.findAllByOrderByIddanseAsc();
        result = icones.stream().map(DanseDTO::new).collect(Collectors.toList());
        return result;
    }
}
