package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.service.UserService;
import pl.teo.crm.model.dto.UserDto;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterController {
    private final UserService userService;

    @PostMapping("")
    public boolean register(@RequestBody UserDto dto) {
        if (userService.addNewUser(dto)) {
            System.out.println("Success regisetred user: " + dto.getUsername());
            return true; //todo response code 200
        } else {
            System.out.println("failed regisetred user: " + dto.getUsername());
            return false; //todo error, username taken
        }
    }
}
