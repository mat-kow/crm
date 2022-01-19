package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.app.exception.ApiBadRequestException;
import pl.teo.crm.model.dto.NewPasswordForm;
import pl.teo.crm.model.dto.UserCreationDto;
import pl.teo.crm.model.dto.UserDto;
import pl.teo.crm.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public List<UserCreationDto> findUsers(@RequestParam String q) {
        q = q.trim();
        log.info(String.format("user search query <%s>", q));
        if (q.length() < 3) {
            throw new ApiBadRequestException("Query too short");
        }
        return userService.findUser(q);
    }

    @PostMapping("/admins")
    public UserDto makeAdmin(@Valid @RequestBody String username, Principal principal) {
        log.info("{} makes {} an admin", principal.getName(), username);
        return userService.makeAdmin(username);
    }

    @GetMapping("/admins")
    public List<UserDto> getAdmins() {
        log.info("fetching admins");
        return userService.getAdmins();
    }

    @DeleteMapping("/admins/{username}")
    public UserDto removeAdmin(@PathVariable String username, Principal principal) {
        log.info("{} removes admin from {}", principal.getName(), username);
        return userService.removeAdmin(username);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@RequestBody @Valid UserDto dto, Principal principal, @PathVariable int userId) {
        if (principal.getName().equals(dto.getUsername()) && userId == dto.getId()) {
            return userService.update(dto);
        }
        throw new ApiBadRequestException();
    }

    @GetMapping("/user/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @PutMapping("/password")
    public UserDto changePassword(@RequestBody @Valid NewPasswordForm form, Principal principal) {
        return userService.changePassword(form, principal);
    }

}
