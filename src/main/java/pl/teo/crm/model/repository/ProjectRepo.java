package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.crm.model.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer> {
}
