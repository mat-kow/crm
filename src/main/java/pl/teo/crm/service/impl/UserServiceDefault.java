package pl.teo.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.teo.crm.service.UserService;
import pl.teo.crm.model.User;
import pl.teo.crm.model.dto.UserDto;
import pl.teo.crm.model.Role;
import pl.teo.crm.model.repository.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class UserServiceDefault implements UserService {
    private final ModelMapper mapper;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean addNewUser(UserDto userDto) {
        if (userRepo.existsByUsername(userDto.getUsername())) {
            return false;
        }
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(Role.ROLE_USER);
        userRepo.save(user);
        return true;
    }

    @Override
    public List<UserDto> findUser(String query) {
        List<User> users = userRepo.findAllByQuery(query);
        return users.stream().map(user -> {
            UserDto dto = mapper.map(user, UserDto.class);
            dto.setPassword("");
            return dto;
        }).collect(Collectors.toList());
    }
}
