package com.fresh.restapi.services.user.impl;

import com.fresh.restapi.constants.Constants;
import com.fresh.restapi.converters.UserConverter;
import com.fresh.restapi.dtos.AuthenticationCustom;
import com.fresh.restapi.dtos.requests.user.UserLoginRequestDTO;
import com.fresh.restapi.dtos.requests.user.UserRequestDTO;
import com.fresh.restapi.dtos.responses.DeleteResponseDTO;
import com.fresh.restapi.dtos.responses.user.UserLoginResponseDTO;
import com.fresh.restapi.dtos.responses.user.UserResponseDTO;
import com.fresh.restapi.exceptions.BadRequestException;
import com.fresh.restapi.exceptions.NotFoundException;
import com.fresh.restapi.models.role.RoleEntity;
import com.fresh.restapi.models.user.UserEntity;
import com.fresh.restapi.models.user.UserRoleEntity;
import com.fresh.restapi.repositories.RoleRepository;
import com.fresh.restapi.repositories.UserRepository;
import com.fresh.restapi.services.auth.JwtAuthenticationService;
import com.fresh.restapi.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private JwtAuthenticationService authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<UserEntity> users = userRepo.findAll();
        List<UserResponseDTO> usersResponse = new ArrayList<>();

        for (UserEntity userEntity : users) {
            UserResponseDTO user = userConverter.toResponseDTO(userEntity);
            usersResponse.add(user);
        }
        return usersResponse;
    }


    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO request) {
        Authentication auth = authenticationManager.authenticate(
                new AuthenticationCustom(request.getUsername(), request.getPassword())
        );
        return (UserLoginResponseDTO) auth.getDetails();
    }

    public boolean validPassword(String password) {
        return password.length() > 4 && password.length() < 21;
    }

    @Override
    public UserResponseDTO createNewUser(UserRequestDTO request) {
        String username = request.getUsername();
        String name = request.getName();
        String password = request.getPassword();
        List<String> roles = request.getRoles();

        // Username Validation
        Optional<UserEntity> optionalUser = userRepo.findByUsername(username);
        optionalUser.ifPresent(userEntity -> {
            throw new BadRequestException("User with username : " + username + " already Exist");
        });

        // Role Validation
        List<RoleEntity> allAvailableRoles = roleRepository.findAll();
        List<RoleEntity> userRoles = new ArrayList<>();
//        List<String> availableRoles = allAvailableRoles.stream()
//                .map(RoleEntity::getName)
//                .collect(Collectors.toList());

        if (!roles.isEmpty()) {
            for (String role : roles) {
                for (RoleEntity availRole : allAvailableRoles) {
                    if (role.toUpperCase().equals(availRole.getName())) userRoles.add(availRole);
                }
            }
        } else {
            Optional<RoleEntity> optionalRole = roleRepository.findByName(Constants.CUSTOMER);
            String errMsg = "Role " + Constants.CUSTOMER + " doesn't exist!";
            RoleEntity defaultRole = optionalRole.orElseThrow(() -> new NotFoundException(errMsg));
            userRoles.add(defaultRole);
        }


        // Password Validation
        if (!validPassword(password))
            throw new BadRequestException("Password length must be [5-20]char");


        UserEntity user = new UserEntity();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        List<UserRoleEntity> userRolesEntity = new ArrayList<>();
        if (userRoles.size() == 1) {
            userRolesEntity.add(
                    UserRoleEntity.builder()
                            .user(user)
                            .role(userRoles.get(0))
                            .build()
            );
        } else {
            for (RoleEntity role : userRoles) {
                userRolesEntity.add(
                        UserRoleEntity.builder()
                                .user(user)
                                .role(role)
                                .build()
                );
            }
        }
        user.setUserRoles(userRolesEntity);

        UserEntity newUser = userRepo.save(user);
        return userConverter.toResponseDTO(newUser);
    }

    @Override
    public UserResponseDTO updatePasswordByUsername(String username, String password, String newPassword) {
        Optional<UserEntity> optionalUser = userRepo.findByUsername(username);
        UserEntity user = optionalUser.orElseThrow(() ->
                new NotFoundException("User with username: " + username + " Not Found")
        );

        String prevPassword = user.getPassword();
        if (!passwordEncoder.matches(password, prevPassword))
            throw new BadRequestException("Invalid Prev Password, please insert your previous password");

        user.setPassword(passwordEncoder.encode(newPassword));
        UserEntity updatedUser = userRepo.save(user);

        return userConverter.toResponseDTO(updatedUser);
    }

    @Override
    public UserResponseDTO updateRolesByUsername(String username, List<String> roles) {
        Optional<UserEntity> optionalUser = userRepo.findByUsername(username);
        UserEntity user = optionalUser.orElseThrow(() ->
                new NotFoundException("User with username: " + username + " Not Found")
        );
        List<UserRoleEntity> newUserRoles = new ArrayList<>();
        for (String role : roles) {
            Optional<RoleEntity> optionalRole = roleRepository.findByName(role);
            RoleEntity newRole = optionalRole.orElseThrow(() -> new BadRequestException("Role: " + role + " doesn't Exist"));
            newUserRoles.add(
                    UserRoleEntity.builder()
                            .user(user)
                            .role(newRole)
                            .build()
            );
        }
        user.setUserRoles(newUserRoles);
        return userConverter.toResponseDTO(user);
    }

    @Override
    public DeleteResponseDTO deleteUserByUsername(String username) {
        Optional<UserEntity> optionalUser = userRepo.findByUsername(username);
        boolean userIsExist = optionalUser.isPresent();
        if(userIsExist) userRepo.deleteByUsername(username);
        Integer deletedCount = userIsExist ? 1 : 0;
        return new DeleteResponseDTO("Delete Success", deletedCount);
    }

}
