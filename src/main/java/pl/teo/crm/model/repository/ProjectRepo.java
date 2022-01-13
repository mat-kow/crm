package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.crm.model.Project;

import java.util.Optional;

public interface ProjectRepo extends JpaRepository<Project, Integer> {
    Optional<Project> findById(int id);
}
