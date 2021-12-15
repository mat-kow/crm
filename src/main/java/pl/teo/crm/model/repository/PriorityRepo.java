package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.crm.model.Priority;

import java.util.List;

public interface PriorityRepo extends JpaRepository<Priority, String> {
    List<Priority> getAllByActiveTrue();

}
