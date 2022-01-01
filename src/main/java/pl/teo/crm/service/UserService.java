package pl.teo.crm.service;

import pl.teo.crm.model.dto.UserDto;

import java.util.List;

public interface UserService {
    boolean addNewUser(UserDto userDto);
    List<UserDto> findUser(String query);
}
