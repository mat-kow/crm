package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.crm.model.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityRepo extends JpaRepository<Priority, Integer> {
    List<Priority> getAllByActiveTrue();
    Optional<Priority> findById(int id);
    Optional<Priority> findByName(String name);

}
