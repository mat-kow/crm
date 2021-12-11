package pl.teo.crm.service;

import pl.teo.crm.model.dto.UserDto;

public interface UserService {
    boolean addNewUser(UserDto userDto);
}
