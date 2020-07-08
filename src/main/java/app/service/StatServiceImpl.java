package app.service;

import app.DTO.NiveauDTO;
import app.DTO.PasseDTO;
import app.DTO.StatDTO;
import app.entity.Niveau;
import app.entity.Passe;
import app.entity.Stat;
import app.repository.NiveauRepository;
import app.repository.PasseRepository;
import app.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatRepository statRepository;
    @Autowired
    private PasseRepository passeRepository;

    @Override
    public List<StatDTO> findAllOrderByIdAsc() {
        List<StatDTO> result = new ArrayList<>();
        List<Stat> stats = statRepository.findAllByOrderByIdAsc();
        result = stats.stream().map(StatDTO::new).collect(Collectors.toList());
        return result;
    }

    @Override
    public void createStat(StatDTO p_statDTO) {
        Passe passe = passeRepository.getOne(p_statDTO.getPasseDTO().getId());
        if(passe != null) {
            Stat stat = new Stat();
            stat.setPasse(passe);
            stat.setTypeaction(p_statDTO.getTypeaction());
            stat.setHorodatage(new Date());
            statRepository.save(stat);
        }
    }
}
