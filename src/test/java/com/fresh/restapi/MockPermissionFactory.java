package com.fresh.restapi;

import com.fresh.restapi.dtos.responses.permission.PermissionResponseDTO;
import com.fresh.restapi.models.permission.PermissionEntity;
import com.fresh.restapi.models.role.RolePermissionEntity;

import java.util.Collections;
import java.util.List;

public class MockPermissionFactory {
    public static List<PermissionEntity> getPermissionsEntityByRoleName(String roleName) {
        return Collections.singletonList(
                PermissionEntity.builder()
                        .httpMethod(TestConstants.HTTP_METHOD_GET)
                        .pathUri(TestConstants.URL)
                        .rolePermissions(
                                MockRolePermissionFactory.getRolePermissionsEntityByRoleName(roleName)
                        )
                        .build()
        );
    }

    public static List<PermissionResponseDTO> getPermissionsResponseDTO() {
        return Collections.singletonList(
                PermissionResponseDTO.builder()
                        .httpMethod(TestConstants.HTTP_METHOD_GET)
                        .pathURI(TestConstants.URL)
                        .build()
        );
    }

    public static PermissionEntity getPermissionEntity(String roleName) {
        return PermissionEntity.builder()
                .rolePermissions(
                        Collections.singletonList(
                                RolePermissionEntity
                                        .builder()
                                        .role(MockRoleFactory.getRoleEntity(roleName))
                                        .build()
                        )
                )
                .pathUri(TestConstants.URL)
                .httpMethod(TestConstants.HTTP_METHOD_GET)
                .build();
    }
    public static PermissionEntity getPermissionEntity() {
        return PermissionEntity.builder()
                .rolePermissions(
                        Collections.singletonList(
                                RolePermissionEntity
                                        .builder()
                                        .role(MockRoleFactory.getRoleEntity())
                                        .build()
                        )
                )
                .pathUri(TestConstants.URL)
                .httpMethod(TestConstants.HTTP_METHOD_GET)
                .build();
    }
}
