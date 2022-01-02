package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.crm.model.Status;

import java.util.List;
import java.util.Optional;

public interface StatusRepo extends JpaRepository<Status, Integer> {
    List<Status> getAllByActiveTrue();
    Optional<Status> findByName(String name);
}
