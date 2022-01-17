package pl.teo.crm.service;

import pl.teo.crm.model.User;
import pl.teo.crm.model.dto.UserCreationDto;
import pl.teo.crm.model.dto.UserDto;

import java.security.Principal;
import java.util.List;

public interface UserService {
    boolean addNewUser(UserCreationDto dto);
    List<UserCreationDto> findUser(String query);
    UserDto makeAdmin(String username);
    UserDto removeAdmin(String username);
    List<UserDto> getAdmins();
    User getCurrentUser(Principal principal);
}
