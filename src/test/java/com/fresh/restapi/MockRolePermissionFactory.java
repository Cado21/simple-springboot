package com.fresh.restapi;

import com.fresh.restapi.models.role.RolePermissionEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MockRolePermissionFactory {
    public static List<RolePermissionEntity> getRolePermissionsEntityByRoleName(String roleName) {
        return Collections.singletonList(
                RolePermissionEntity.builder()
                        .permission(MockPermissionFactory.getPermissionEntity(roleName))
                        .role(MockRoleFactory.getRoleEntity(roleName)).build()
        );
    }

    public static List<RolePermissionEntity> getAllRolePermissionEntity() {
        return Arrays.asList(
                RolePermissionEntity.builder()
                        .permission(MockPermissionFactory.getPermissionEntity(TestConstants.ROLE_ADMIN))
                        .role(MockRoleFactory.getRoleEntity(TestConstants.ROLE_ADMIN))
                        .build(),
                RolePermissionEntity.builder()
                        .permission(MockPermissionFactory.getPermissionEntity(TestConstants.ROlE_CUSTOMER))
                        .role(MockRoleFactory.getRoleEntity(TestConstants.ROlE_CUSTOMER))
                        .build()
        );
    }
}
