package pl.teo.crm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/login")
@Slf4j
public class LoginController {

    @GetMapping("")
    public boolean login(Principal principal) {
        log.info(String.format("User: <%s> has logged in", principal.getName()));
        return true;
    }

}
