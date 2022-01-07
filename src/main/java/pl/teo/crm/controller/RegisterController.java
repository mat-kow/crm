package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.service.UserService;
import pl.teo.crm.model.dto.UserDto;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {
    private final UserService userService;

    @PostMapping("")
    public boolean register(@RequestBody UserDto dto) {
        if (userService.addNewUser(dto)) {
            log.info(String.format("Success registering user: ,%s>", dto.getUsername()));
            return true; //todo response code 200
        } else {
            log.info(String.format("failed registering user: ,%s>", dto.getUsername()));
            return false; //todo error, username taken
        }
    }
}
