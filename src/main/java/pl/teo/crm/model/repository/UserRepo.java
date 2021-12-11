package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.crm.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> getUserByUsername(String username);
    boolean existsByUsername(String username);
}
