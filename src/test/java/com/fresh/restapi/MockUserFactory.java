package com.fresh.restapi;

import com.fresh.restapi.dtos.requests.user.UserLoginRequestDTO;
import com.fresh.restapi.dtos.requests.user.UserRequestDTO;
import com.fresh.restapi.dtos.responses.user.UserLoginResponseDTO;
import com.fresh.restapi.dtos.responses.user.UserResponseDTO;
import com.fresh.restapi.models.user.UserEntity;
import com.fresh.restapi.models.user.UserRoleEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class MockUserFactory {
    public static UserLoginRequestDTO getUserLoginRequestDTO () {
        return UserLoginRequestDTO.builder()
                .username(TestConstants.NAME)
                .password(TestConstants.PASSWORD)
                .build();
    }
    public static UserLoginResponseDTO getUserLoginResponseDTO () {
        return UserLoginResponseDTO.builder()
                .token(TestConstants.TOKEN)
                .build();
    }
    public static UserRequestDTO getUserRequestDTO() {
        return UserRequestDTO
                .builder()
                .name(TestConstants.NAME)
                .password(TestConstants.PASSWORD)
                .username(TestConstants.NAME)
                .roles(Collections.singletonList(TestConstants.ROLE_ADMIN))
                .build();
    }

    public static UserResponseDTO getUserResponseDTO() {
        return UserResponseDTO
                .builder()
                .name(TestConstants.NAME)
                .roles(Collections.singletonList(TestConstants.ROLE_ADMIN))
                .username(TestConstants.NAME)
                .build();
    }


    public static Optional<UserEntity> getOptionalUserEntity() {
        return Optional.of(UserEntity
                .builder()
                .name(TestConstants.NAME)
                .username(TestConstants.NAME)
                .password(TestConstants.PASSWORD)
                .build());
    }

    public static UserEntity getUserEntity() {
        UserEntity user = new UserEntity();
        user.setName(TestConstants.NAME);
        user.setUsername(TestConstants.NAME);
        user.setPassword(TestConstants.ENCODED_PASSWORD);
        user.setCreatedAt(TestConstants.DEFAULT_TIME);
        user.setUpdatedAt(TestConstants.DEFAULT_TIME);
        List<UserRoleEntity> userRolesEntity = new ArrayList<>();
        userRolesEntity.add(
                UserRoleEntity
                        .builder()
                        .role(MockRoleFactory.getRoleEntity())
                        .build()
        );
        user.setUserRoles(userRolesEntity);
        return user;
    }

    public static List<UserResponseDTO> getAllUserResponseDTO() {
        return Collections.singletonList(getUserResponseDTO());
    }
    public static List<UserEntity> getAllUserEntity() {
        return Collections.singletonList(getUserEntity());
    }
}
