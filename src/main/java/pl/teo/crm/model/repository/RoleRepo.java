package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.crm.model.Role;

public interface RoleRepo extends JpaRepository<Role, String> {
}
