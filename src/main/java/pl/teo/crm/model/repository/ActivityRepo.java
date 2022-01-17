package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.teo.crm.model.Activity;

import java.util.List;

public interface ActivityRepo extends JpaRepository<Activity, Integer> {
    @Query(
            nativeQuery = true,
            value = "SELECT a.id, action, created, subject, username, p.name AS project_name, t.topic AS task_topic, a.project_id, subject_id " +
                    "FROM activities AS a " +
                    "JOIN projects p ON project_id=p.id " +
                    "LEFT JOIN tasks t ON subject_id=t.id " +
                    "WHERE p.active=true " +
                    "ORDER BY created DESC LIMIT ?1"
    )
    List<Activity> findAllOrderedDesc(int limit);
}
