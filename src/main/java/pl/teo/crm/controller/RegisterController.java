package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.app.exception.ApiBadRequestException;
import pl.teo.crm.service.UserService;
import pl.teo.crm.model.dto.UserCreationDto;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {
    private final UserService userService;

    @PostMapping("")
    public boolean register(@RequestBody UserCreationDto dto) {
        dto.setUsername(dto.getUsername().strip());
        if (userService.addNewUser(dto)) {
            log.info(String.format("Success registering user: <%s>", dto.getUsername()));
            return true;
        } else {
            log.info(String.format("failed registering user: <%s>", dto.getUsername()));
            throw new ApiBadRequestException("username taken");
        }
    }
}
