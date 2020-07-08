package app.service;

import app.DTO.TypePasseDTO;
import app.entity.TypePasse;
import app.repository.TypePasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypePasseServiceImpl implements TypePasseService {

    @Autowired
    private TypePasseRepository typePasseRepository;

    @Override
    public List<TypePasseDTO> findAllOrberByCodeTypePasseAsc() {
        List<TypePasseDTO> result = new ArrayList<>();
        List<TypePasse> icones = typePasseRepository.findAllByOrderByCodetypepasseAsc();
        result = icones.stream().map(TypePasseDTO::new).collect(Collectors.toList());
        return result;
    }
}
