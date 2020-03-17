package com.fresh.restapi;

import com.fresh.restapi.dtos.responses.role.RoleResponseDTO;
import com.fresh.restapi.models.role.RoleEntity;

import java.util.Arrays;
import java.util.List;

public class MockRoleFactory {
    public static RoleEntity getRoleEntity(String roleName) {
        return RoleEntity.builder()
                .name(roleName)
                .build();
    }

    public static RoleEntity getRoleEntity() {
        return RoleEntity.builder()
                .name(TestConstants.ROLE_ADMIN)
                .build();
    }

    public static List<RoleEntity> getAllRoleEntities() {
        return Arrays.asList(
                getRoleEntity(TestConstants.ROLE_ADMIN),
                getRoleEntity(TestConstants.ROlE_CUSTOMER)
        );
    }

    public static List<RoleResponseDTO> getAllRolesResponseDTO() {
        return Arrays.asList(
                RoleResponseDTO.builder()
                        .role(TestConstants.ROLE_ADMIN)
                        .permissions(MockPermissionFactory.getPermissionsResponseDTO())
                        .build(),
                RoleResponseDTO.builder()
                        .role(TestConstants.ROlE_CUSTOMER)
                        .permissions(MockPermissionFactory.getPermissionsResponseDTO())
                        .build()
        );
    }

    public static RoleResponseDTO getRoleResponseDTO(String roleName) {
        return RoleResponseDTO.builder()
                .role(roleName)
                .permissions(MockPermissionFactory.getPermissionsResponseDTO())
                .build();
    }
}
