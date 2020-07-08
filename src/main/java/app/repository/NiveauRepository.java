package app.repository;

import app.entity.Niveau;
import app.entity.Passe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NiveauRepository extends JpaRepository<Niveau, Integer> {
    List<Niveau> findAllByOrderByIdniveauAsc();
}
