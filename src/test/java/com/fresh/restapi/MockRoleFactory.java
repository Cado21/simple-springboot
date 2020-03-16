package com.fresh.restapi;

import com.fresh.restapi.models.role.RoleEntity;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MockRoleFactory {
    public static RoleEntity getRoleEntity(String roleConstantName) {
        return RoleEntity.builder()
                .name(roleConstantName)
                .build();
    }
    public static RoleEntity getRoleEntity() {
        return RoleEntity.builder()
                .name(TestConstants.ROLE_ADMIN)
                .build();
    }

    public static List<RoleEntity> getAllRoleEntities() {
        return Arrays.asList(
                RoleEntity
                        .builder()
                        .name(TestConstants.ROLE_ADMIN)
                        .build(),
                RoleEntity
                        .builder()
                        .name(TestConstants.ROlE_CUSTOMER)
                        .build()
        );
    }
}
