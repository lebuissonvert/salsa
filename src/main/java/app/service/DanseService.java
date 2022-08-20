package app.service;

import app.DTO.DanseDTO;
import app.DTO.NiveauDTO;

import java.util.List;

public interface DanseService {
    public List<DanseDTO> findAllOrderByIdDanseAsc();
}
