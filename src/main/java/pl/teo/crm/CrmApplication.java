package pl.teo.crm;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.teo.crm.model.Role;
import pl.teo.crm.model.Status;
import pl.teo.crm.model.User;
import pl.teo.crm.model.repository.StatusRepo;
import pl.teo.crm.model.repository.UserRepo;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class CrmApplication {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final StatusRepo statusRepo;

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

    @Bean
    public InitializingBean initDatabase() {
        return () -> {
            if (userRepo.count() == 0) {
                userRepo.save(new User(1, "user", "user", "user",
                        encoder.encode("user1"), List.of(Role.ROLE_USER)));
                userRepo.save(new User(2, "admin", "admin", "admin",
                        encoder.encode("admin1"), List.of(Role.ROLE_USER, Role.ROLE_ADMIN)));
                statusRepo.save(new Status(3, "default" , -1, true));
            }
        };
    }
}
