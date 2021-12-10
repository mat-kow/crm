package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.crm.model.Priority;

public interface PriorityRepo extends JpaRepository<Priority, Integer> {
}
