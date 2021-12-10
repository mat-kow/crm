package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.crm.model.Status;

public interface StatusRepo extends JpaRepository<Status, Integer> {
}
