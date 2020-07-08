package app.repository;

import app.entity.BackupPasse;
import app.entity.Passe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BackupPasseRepository extends JpaRepository<BackupPasse, Integer> {
}
