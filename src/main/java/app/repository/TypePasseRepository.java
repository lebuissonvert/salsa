package app.repository;

import app.entity.Niveau;
import app.entity.TypePasse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypePasseRepository extends JpaRepository<TypePasse, Integer> {
    List<TypePasse> findAllByOrderByCodetypepasseAsc();
}
