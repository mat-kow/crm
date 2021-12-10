package pl.teo.crm.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.crm.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
