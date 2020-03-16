package com.fresh.restapi.services.user.impl;

import com.fresh.restapi.MockGeneralFactory;
import com.fresh.restapi.MockRoleFactory;
import com.fresh.restapi.MockUserFactory;
import com.fresh.restapi.TestConstants;
import com.fresh.restapi.converters.UserConverter;
import com.fresh.restapi.dtos.requests.user.UserRequestDTO;
import com.fresh.restapi.dtos.responses.DeleteResponseDTO;
import com.fresh.restapi.dtos.responses.user.UserResponseDTO;
import com.fresh.restapi.models.user.UserEntity;
import com.fresh.restapi.repositories.RoleRepository;
import com.fresh.restapi.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserConverter userConverter;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(userRepository, roleRepository, passwordEncoder, userConverter);
    }

    @Test
    void createNewUser() {
        when(userRepository.findByUsername(TestConstants.NAME)).thenReturn(Optional.empty());
        when(roleRepository.findAll()).thenReturn(MockRoleFactory.getAllRoleEntities());
        when(passwordEncoder.encode(TestConstants.PASSWORD)).thenReturn(TestConstants.ENCODED_PASSWORD);
        when(userConverter.toResponseDTO(MockUserFactory.getUserEntity())).thenReturn(MockUserFactory.getUserResponseDTO());
        when(userRepository.save(any(UserEntity.class))).thenReturn(MockUserFactory.getUserEntity());

        UserRequestDTO userRequest = MockUserFactory.getUserRequestDTO();
        UserResponseDTO expected = MockUserFactory.getUserResponseDTO();
        UserResponseDTO actual = userService.createNewUser(userRequest);
        Assert.assertEquals(expected, actual);

        verify(userRepository, times(1)).findByUsername(TestConstants.NAME);
        verify(roleRepository, times(1)).findAll();
        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(passwordEncoder, times(1)).encode(TestConstants.PASSWORD);
        verify(userConverter, times(1)).toResponseDTO(MockUserFactory.getUserEntity());
    }

    @Test
    void getAllUser() {
        when(userRepository.findAll()).thenReturn(MockUserFactory.getAllUserEntity());
        when(userConverter.toResponseDTO(MockUserFactory.getUserEntity())).thenReturn(MockUserFactory.getUserResponseDTO());

        List<UserResponseDTO> expected = MockUserFactory.getAllUserResponseDTO();
        List<UserResponseDTO> actual = userService.getAllUsers();
        Assert.assertEquals(expected, actual);

        verify(userRepository, times(1)).findAll();
        verify(userConverter, times(1)).toResponseDTO(MockUserFactory.getUserEntity());
    }

    @Test
    void deleteUserByUsername() {

        when(userRepository.findByUsername(TestConstants.NAME)).thenReturn(Optional.of(MockUserFactory.getUserEntity()));

        DeleteResponseDTO expected = MockGeneralFactory.getDeleteResponseDTO("Delete Success", 1);
        DeleteResponseDTO actual = userService.deleteUserByUsername(TestConstants.NAME);
        Assert.assertEquals(expected, actual);

        verify(userRepository, times(1)).findByUsername(TestConstants.NAME);
        verify(userRepository, times(1)).deleteByUsername(TestConstants.NAME);
    }

    @Test
    void updateRolesByUsername() {
        when(userRepository.findByUsername(TestConstants.NAME)).thenReturn(Optional.of(MockUserFactory.getUserEntity()));
        when(roleRepository.findByName(TestConstants.ROLE_ADMIN)).thenReturn(Optional.of(MockRoleFactory.getRoleEntity(TestConstants.ROLE_ADMIN)));
        when(userConverter.toResponseDTO(any(UserEntity.class))).thenReturn(MockUserFactory.getUserResponseDTO());

        UserResponseDTO expected = MockUserFactory.getUserResponseDTO();
        UserResponseDTO actual = userService.updateRolesByUsername(
                TestConstants.NAME,
                Collections.singletonList(TestConstants.ROLE_ADMIN)
        );
        Assert.assertEquals(expected, actual);

        verify(userRepository, times(1)).findByUsername(TestConstants.NAME);
        verify(roleRepository, times(1)).findByName(TestConstants.ROLE_ADMIN);
        verify(userConverter, times(1)).toResponseDTO(any(UserEntity.class));
    }

    @Test
    void updatePasswordByUsername() {
        when(userRepository.findByUsername(TestConstants.NAME)).thenReturn(Optional.of(MockUserFactory.getUserEntity()));
        when(userConverter.toResponseDTO(any(UserEntity.class))).thenReturn(MockUserFactory.getUserResponseDTO());
        when(userRepository.save(any(UserEntity.class))).thenReturn(MockUserFactory.getUserEntity());
        when(passwordEncoder.matches(TestConstants.PASSWORD, TestConstants.ENCODED_PASSWORD)).thenReturn(true);
        when(passwordEncoder.encode(TestConstants.PASSWORD_2)).thenReturn(TestConstants.ENCODED_PASSWORD);

        UserResponseDTO expected = MockUserFactory.getUserResponseDTO();
        UserResponseDTO actual = userService.updatePasswordByUsername(TestConstants.NAME, TestConstants.PASSWORD, TestConstants.PASSWORD_2);
        Assert.assertEquals(expected, actual);

        verify(userRepository, times(1)).findByUsername(TestConstants.NAME);
        verify(passwordEncoder, times(1)).matches(TestConstants.PASSWORD, TestConstants.ENCODED_PASSWORD);
        verify(passwordEncoder, times(1)).encode(TestConstants.PASSWORD_2);
        verify(userConverter, times(1)).toResponseDTO(any(UserEntity.class));
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }
}
