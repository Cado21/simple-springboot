package com.fresh.restapi.services.role.impl;

import com.fresh.restapi.MockPermissionFactory;
import com.fresh.restapi.MockRoleFactory;
import com.fresh.restapi.MockRolePermissionFactory;
import com.fresh.restapi.TestConstants;
import com.fresh.restapi.converters.RoleConverter;
import com.fresh.restapi.dtos.responses.role.RoleResponseDTO;
import com.fresh.restapi.models.role.RoleEntity;
import com.fresh.restapi.repositories.PermissionRepository;
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
import java.util.Optional;

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

    @Mock
    PermissionRepository permissionRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(roleRepository, roleConverter, permissionRepository);
    }

    @Test
    void getAllRoles() {
        when(roleRepository.findAll()).thenReturn(MockRoleFactory.getAllRoleEntities());
        when(roleConverter.toResponseDTO(any(RoleEntity.class), anyList()))
                .thenReturn(MockRoleFactory.getRoleResponseDTO());
        when(rolePermissionRepository.findAllByRole_Name(anyString()))
                .thenReturn(MockRolePermissionFactory.getAllRolePermissionEntity());

        List<RoleResponseDTO> expected = MockRoleFactory.getAllRolesResponseDTO();
        List<RoleResponseDTO> actual = roleService.getAllRoles();
        Assert.assertEquals(expected, actual);

        verify(roleRepository, times(1)).findAll();
        verify(roleConverter, atLeast(1)).toResponseDTO(any(RoleEntity.class), anyList());
        verify(rolePermissionRepository, atLeast(1)).findAllByRole_Name(anyString());
    }

    @Test
    void getRoleById() {
        when(roleRepository.findById(anyInt())).thenReturn(Optional.of(MockRoleFactory.getRoleEntity()));
        when(rolePermissionRepository.findAllByRole_Name(anyString())).thenReturn(MockRolePermissionFactory.getAllRolePermissionEntity());
        when(roleConverter.toResponseDTO(any(RoleEntity.class), anyList())).thenReturn(MockRoleFactory.getRoleResponseDTO());

        RoleResponseDTO expected = MockRoleFactory.getRoleResponseDTO();
        RoleResponseDTO actual = roleService.getRoleById(anyInt());
        Assert.assertEquals(expected, actual);

        verify(roleRepository, times(1)).findById(anyInt());
        verify(rolePermissionRepository, times(1)).findAllByRole_Name(anyString());
        verify(roleConverter, times(1)).toResponseDTO(any(RoleEntity.class), anyList());
    }

    @Test
    void createNewRole() {
        when(roleRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(roleConverter.toResponseDTO(any(RoleEntity.class), anyList())).thenReturn(MockRoleFactory.getRoleResponseDTO());

        RoleResponseDTO expected = MockRoleFactory.getRoleResponseDTO();
        RoleResponseDTO actual = roleService.createNewRole(TestConstants.ROLE_ADMIN, anyList());
        Assert.assertEquals(expected, actual);

        verify(roleRepository, times(1)).findByName(anyString());
        verify(roleConverter, times(1)).toResponseDTO(any(RoleEntity.class), anyList());
    }

    @Test
    void updateRole() {
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(MockRoleFactory.getRoleEntity()));
        when(permissionRepository.findByHttpMethodAndPathUri(anyString(), anyString())).thenReturn(Optional.of(MockPermissionFactory.getPermissionEntity()));
        when(roleConverter.toResponseDTO(any(RoleEntity.class), anyList())).thenReturn(MockRoleFactory.getRoleResponseDTO());

        RoleResponseDTO expected = MockRoleFactory.getRoleResponseDTO();
        RoleResponseDTO actual = roleService.updateRole(TestConstants.ROLE_ADMIN, anyList());
        Assert.assertEquals(expected, actual);

        verify(roleRepository, times(1)).findByName(anyString());
        verify(roleConverter, times(1)).toResponseDTO(any(RoleEntity.class), anyList());
    }

}
