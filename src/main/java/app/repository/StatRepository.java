package app.repository;

import app.entity.Niveau;
import app.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatRepository extends JpaRepository<Stat, Integer> {
    List<Stat> findAllByOrderByIdAsc();
}
