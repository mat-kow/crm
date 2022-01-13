package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.teo.crm.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> getUserByUsername(String username);
    boolean existsByUsername(String username);
    @Query("SELECT u FROM users u WHERE LOWER(u.firstname) LIKE LOWER(concat('%', concat(?1, '%')))" +
            "OR LOWER(u.lastname) LIKE LOWER(concat('%', concat(?1, '%')))" +
            "OR LOWER(u.username) LIKE LOWER(concat('%', concat(?1, '%')))")
    List<User> findAllByQuery(String query);
    @Query(
            value = "SELECT * FROM users WHERE id IN (SELECT users_id FROM users_roles WHERE roles='ROLE_ADMIN')",
            nativeQuery = true
    )
    List<User> findAllAdmins();
}
