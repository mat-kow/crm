package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.teo.crm.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    List<Task> findAllByProjectId(int projectId);
    @Query(nativeQuery = true,
        value = "SELECT name FROM statuses WHERE id = (SELECT status_id FROM tasks WHERE id = ?1)"
    )
    String getStatusNameByTaskId(int id);
}
