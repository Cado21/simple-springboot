package com.fresh.restapi.services.role;

import com.fresh.restapi.dtos.requests.permission.PermissionRequestDTO;
import com.fresh.restapi.dtos.responses.role.RoleResponseDTO;

import java.util.List;

public interface RoleService {
    List<RoleResponseDTO> getAllRoles();
    RoleResponseDTO getRoleById(Integer id);
    RoleResponseDTO createNewRole(String role, List<PermissionRequestDTO> permissions);
    RoleResponseDTO updateRole(String role, List<PermissionRequestDTO> permissions);
}
