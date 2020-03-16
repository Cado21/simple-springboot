package com.fresh.restapi.converters;

import com.fresh.restapi.dtos.responses.permission.PermissionResponseDTO;
import com.fresh.restapi.dtos.responses.role.RoleResponseDTO;
import com.fresh.restapi.models.permission.PermissionEntity;
import com.fresh.restapi.models.role.RoleEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleConverter extends BaseEntityConverter<RoleEntity, RoleResponseDTO> {

    public RoleResponseDTO toResponseDTO (RoleEntity roleEntity, List<PermissionEntity> permissions) {
        RoleResponseDTO responseDTO = new RoleResponseDTO();
        BeanUtils.copyProperties(roleEntity, responseDTO);
        this.setBaseEntityDTO(roleEntity,responseDTO);
        responseDTO.setRole(roleEntity.getName());

        List<PermissionResponseDTO> permissionResponseDTOList = new ArrayList<>();
        for ( PermissionEntity permission : permissions ) {
            PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();
            BeanUtils.copyProperties(permission, permissionResponseDTO);
            permissionResponseDTO.setPathURI(permission.getPathUri());
            permissionResponseDTOList.add(permissionResponseDTO);
        }
        responseDTO.setPermissions(permissionResponseDTOList);
        return responseDTO;

    }
}
