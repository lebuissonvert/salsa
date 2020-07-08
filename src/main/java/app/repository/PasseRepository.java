package app.repository;

import app.entity.Passe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PasseRepository extends JpaRepository<Passe, Integer>, PasseRepositoryCustom {
    Optional<Passe> findById(Integer id);
    List<Passe> findAllByOrderByIdAsc();
    long count();
}
