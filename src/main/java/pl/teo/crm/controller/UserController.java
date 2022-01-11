package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.app.exception.ApiBadRequestException;
import pl.teo.crm.model.dto.UserDto;
import pl.teo.crm.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/search")
    public List<UserDto> findUsers(@RequestParam String q) {
        q = q.trim();
        log.info(String.format("user search query <%s>", q));
        if (q.length() < 3) {
            throw new ApiBadRequestException("Query too short");
        }
        return userService.findUser(q);
    }

    @GetMapping("/searchq")
    public List<UserDto> findUsersTest() {
        log.info(String.format("user search query <%s>", "teo"));
        return userService.findUser("teo");
    }

}
