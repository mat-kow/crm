package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.crm.model.Task;

public interface TaskRepo extends JpaRepository<Task, Integer> {
}
