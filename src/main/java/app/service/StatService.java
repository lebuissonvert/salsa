package app.service;

import app.DTO.NiveauDTO;
import app.DTO.StatDTO;

import java.util.List;

public interface StatService {
    public List<StatDTO> findAllOrderByIdAsc();
    public void createStat(StatDTO p_statDTO);
}
