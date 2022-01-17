package pl.teo.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.teo.crm.app.exception.ApiNotFoundException;
import pl.teo.crm.model.dto.UserDto;
import pl.teo.crm.service.UserService;
import pl.teo.crm.model.User;
import pl.teo.crm.model.dto.UserCreationDto;
import pl.teo.crm.model.Role;
import pl.teo.crm.model.repository.UserRepo;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class UserServiceDefault implements UserService {
    private final ModelMapper mapper;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean addNewUser(UserCreationDto dto) {
        if (userRepo.existsByUsername(dto.getUsername())) {
            return false;
        }
        User user = mapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(Role.ROLE_USER);
        userRepo.save(user);
        return true;
    }

    @Override
    public List<UserCreationDto> findUser(String query) {
        List<User> users = userRepo.findAllByQuery(query);
        return users.stream().map(user -> {
            UserCreationDto dto = mapper.map(user, UserCreationDto.class);
            dto.setPassword("");
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto makeAdmin(String username) {
        User user = userRepo.getUserByUsername(username).orElseThrow(ApiNotFoundException::new);
        user.addRole(Role.ROLE_ADMIN);
        return mapper.map(userRepo.save(user), UserDto.class);
    }

    @Override
    @Transactional
    public UserDto removeAdmin(String username) {
        User user = userRepo.getUserByUsername(username).orElseThrow(ApiNotFoundException::new);
        user.removeRole(Role.ROLE_ADMIN);
        return mapper.map(userRepo.save(user), UserDto.class);
    }

    @Override
    public List<UserDto> getAdmins() {
        return userRepo.findAllAdmins().stream()
                .map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public User getCurrentUser(Principal principal) {
        return userRepo.getUserByUsername(principal.getName()).orElseThrow(ApiNotFoundException::new);
    }
}
