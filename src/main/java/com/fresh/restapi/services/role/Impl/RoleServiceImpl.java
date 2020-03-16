package com.fresh.restapi.services.role.Impl;

import com.fresh.restapi.converters.RoleConverter;
import com.fresh.restapi.dtos.requests.permission.PermissionRequestDTO;
import com.fresh.restapi.dtos.responses.role.RoleResponseDTO;
import com.fresh.restapi.exceptions.BadRequestException;
import com.fresh.restapi.exceptions.NotFoundException;
import com.fresh.restapi.models.permission.PermissionEntity;
import com.fresh.restapi.models.role.RoleEntity;
import com.fresh.restapi.models.role.RolePermissionEntity;
import com.fresh.restapi.repositories.PermissionRepository;
import com.fresh.restapi.repositories.RolePermissionRepository;
import com.fresh.restapi.repositories.RoleRepository;
import com.fresh.restapi.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private RoleConverter roleConverter;


    @Override
    public List<RoleResponseDTO> getAllRoles() {
        List<RoleEntity> roles = roleRepository.findAll();
        List<RoleResponseDTO> roleResponseDTOList = new ArrayList<>();
        for (RoleEntity role : roles) {
            List<RolePermissionEntity> rolePermissionEntities = rolePermissionRepository.findAllByRole_Name(role.getName());
            List<PermissionEntity> permissions = rolePermissionEntities
                    .stream()
                    .map(RolePermissionEntity::getPermission)
                    .collect(Collectors.toList());
            RoleResponseDTO roleResponse = roleConverter.toResponseDTO(role, permissions);
            roleResponseDTOList.add(roleResponse);
        }
        return roleResponseDTOList;
    }

    @Override
    public RoleResponseDTO getRoleById(Integer id) {
        Optional<RoleEntity> optionalRole = roleRepository.findById(id);
        RoleEntity roleEntity = optionalRole.orElseThrow(() -> new NotFoundException("Id Not Found"));
        List<RolePermissionEntity> rolePermissionEntities = rolePermissionRepository.findAllByRole_Name(roleEntity.getName());
        List<PermissionEntity> permissions = rolePermissionEntities
                .stream()
                .map(RolePermissionEntity::getPermission)
                .collect(Collectors.toList());
        return roleConverter.toResponseDTO(roleEntity, permissions);
    }

    @Override
    public RoleResponseDTO createNewRole(String role, List<PermissionRequestDTO> permissions) {
        Optional<RoleEntity> optionalRole = roleRepository.findByName(role);
        if (optionalRole.isPresent()) throw new BadRequestException("Role already exist!");

        RoleEntity newRole = RoleEntity.builder()
                .name(role.toUpperCase())
                .build();

        List<PermissionEntity> newPermissions = new ArrayList<>();
        for (PermissionRequestDTO permission : permissions) {
            // validate valid path
            Optional<PermissionEntity> optionalPermissionEntity = permissionRepository
                    .findByHttpMethodAndPathUri(
                            permission.getHttpMethod(),
                            permission.getPathUri()
                    );
            PermissionEntity validPermission = optionalPermissionEntity.orElseThrow(() ->
                    new NotFoundException("PathURI not found!")
            );

            roleRepository.save(newRole);
            RolePermissionEntity newRolePermission = RolePermissionEntity
                    .builder()
                    .permission(validPermission)
                    .role(newRole)
                    .build();
            rolePermissionRepository.save(newRolePermission);

            newPermissions.add(validPermission);
        }

        return roleConverter.toResponseDTO(newRole, newPermissions);
    }

    @Override
    public RoleResponseDTO updateRole(String role, List<PermissionRequestDTO> permissions) {
        Optional<RoleEntity> optionalRole = roleRepository.findByName(role);
        RoleEntity roleToBeUpdated = optionalRole.orElseThrow(() -> new NotFoundException("Role not found!"));


        List<RolePermissionEntity> newRolePermissions = new ArrayList<>();
        List<PermissionEntity> newPermissionEntities = new ArrayList<>();
        for (PermissionRequestDTO permissionRequest : permissions) {
            Optional<PermissionEntity> optionalPermissionEntity = permissionRepository.findByHttpMethodAndPathUri(
                    permissionRequest.getHttpMethod(),
                    permissionRequest.getPathUri()
            );
            PermissionEntity newPermission = optionalPermissionEntity.orElseThrow(() ->
                    new NotFoundException("PathUri not found!")
            );

            RolePermissionEntity newRolePermission = RolePermissionEntity
                    .builder()
                    .role(roleToBeUpdated)
                    .permission(newPermission)
                    .build();
            newRolePermissions.add(newRolePermission);
            newPermissionEntities.add(newPermission);
        }
        rolePermissionRepository.deleteAllByRoleId(roleToBeUpdated.getId());
        rolePermissionRepository.saveAll(newRolePermissions);
        return roleConverter.toResponseDTO(roleToBeUpdated, newPermissionEntities);
    }




}
