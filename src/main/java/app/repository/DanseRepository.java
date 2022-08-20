package app.repository;

import app.entity.Danse;
import app.entity.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DanseRepository extends JpaRepository<Danse, Integer> {
    List<Danse> findAllByOrderByIddanseAsc();
    Optional<Danse> findByCodedanse(String codeDanse);
}
