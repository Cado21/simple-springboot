package com.fresh.restapi.services.role.impl;

import com.fresh.restapi.MockPermissionFactory;
import com.fresh.restapi.MockRoleFactory;
import com.fresh.restapi.MockRolePermissionFactory;
import com.fresh.restapi.TestConstants;
import com.fresh.restapi.converters.RoleConverter;
import com.fresh.restapi.dtos.responses.role.RoleResponseDTO;
import com.fresh.restapi.models.permission.PermissionEntity;
import com.fresh.restapi.models.role.RoleEntity;
import com.fresh.restapi.models.role.RolePermissionEntity;
import com.fresh.restapi.repositories.RolePermissionRepository;
import com.fresh.restapi.repositories.RoleRepository;
import com.fresh.restapi.services.role.Impl.RoleServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class RoleServiceImplTest {
    @InjectMocks
    RoleServiceImpl roleService;


    @Mock
    RoleRepository roleRepository;

    @Mock
    RoleConverter roleConverter;

    @Mock
    RolePermissionRepository rolePermissionRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(roleRepository, roleConverter, rolePermissionRepository);
    }

    @Test
    void getAllRoles() {
        when(roleRepository.findAll()).thenReturn(MockRoleFactory.getAllRoleEntities());
        when(roleConverter.toResponseDTO(MockRoleFactory.getRoleEntity(TestConstants.ROLE_ADMIN), anyList()))
                .thenReturn(MockRoleFactory.getRoleResponseDTO(TestConstants.ROLE_ADMIN));
        when(roleConverter.toResponseDTO(MockRoleFactory.getRoleEntity(TestConstants.ROlE_CUSTOMER), anyList()))
                .thenReturn(MockRoleFactory.getRoleResponseDTO(TestConstants.ROlE_CUSTOMER));
        when(rolePermissionRepository.findAllByRole_Name(anyString()))
                .thenReturn(MockRolePermissionFactory.getAllRolePermissionEntity());

        List<RoleResponseDTO> expected = MockRoleFactory.getAllRolesResponseDTO();
        List<RoleResponseDTO> actual = roleService.getAllRoles();
        Assert.assertEquals(expected, actual);

        verify(roleRepository, times(1)).findAll();
        verify(roleConverter, atLeast(1)).toResponseDTO(any(RoleEntity.class), anyList());
        verify(rolePermissionRepository, atLeast(1)).findAllByRole_Name(anyString());
    }
//    public List<RoleResponseDTO> getAllRoles() {
//        List<RoleEntity> roles = roleRepository.findAll();
//        List<RoleResponseDTO> roleResponseDTOList = new ArrayList<>();
//        for (RoleEntity role : roles) {
//            List<RolePermissionEntity> rolePermissionEntities = rolePermissionRepository.findAllByRole_Name(role.getName());
//            List<PermissionEntity> permissions = rolePermissionEntities
//                    .stream()
//                    .map(RolePermissionEntity::getPermission)
//                    .collect(Collectors.toList());
//            RoleResponseDTO roleResponse = roleConverter.toResponseDTO(role, permissions);
//            roleResponseDTOList.add(roleResponse);
//        }
//        return roleResponseDTOList;
//    }

}
