package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.crm.model.Task;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    List<Task> findAllByProjectId(int projectId);
}
