package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.service.UserService;
import pl.teo.crm.model.Role;
import pl.teo.crm.model.User;
import pl.teo.crm.model.dto.UserDto;
import pl.teo.crm.model.repository.UserRepo;

import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final UserRepo userRepo;

    @PostMapping("")
    public String register(@RequestBody UserDto dto) {
        if (userService.addNewUser(dto)) {
            return "Register successful"; //todo response code 200
        } else {
            return "Something went wrong"; //todo error, username taken
        }
    }

    @GetMapping("/make_admin")
    public User makeAdmin() {
        Collection<Role> roles = Arrays.asList(Role.ROLE_ADMIN, Role.ROLE_USER);
        User user = new User(999, "admin", "admin", "admin", "admin", roles);
        return userRepo.save(user);

    }
}
