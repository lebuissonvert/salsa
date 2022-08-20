package app.repository;

import app.entity.TypePasse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypePasseRepositoryCustom {
    public List<TypePasse> findAllByCodeDanseOrderByCodeTypePasseAsc(String codeDanse);
}
