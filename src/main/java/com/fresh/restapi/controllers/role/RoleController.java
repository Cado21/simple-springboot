package com.fresh.restapi.controllers.role;

import com.fresh.restapi.aspect.CheckPermission;
import com.fresh.restapi.constants.API;
import com.fresh.restapi.converters.ResponseConverter;
import com.fresh.restapi.dtos.BaseResponseDTO;
import com.fresh.restapi.dtos.requests.role.RoleRequestDTO;
import com.fresh.restapi.dtos.responses.role.RoleResponseDTO;
import com.fresh.restapi.services.role.RoleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = API.V1 + API.ROLES, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {
    @Autowired
    private RoleService roleService;


    @CheckPermission
    @GetMapping
    public ResponseEntity getAllRoles() {
        List<RoleResponseDTO> allRoles = roleService.getAllRoles();
        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(allRoles);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CheckPermission
    @PostMapping
    public ResponseEntity createNewRoles(
            @Valid
            @RequestBody RoleRequestDTO requestDTO
    ) {
        RoleResponseDTO newRole = roleService.createNewRole(requestDTO.getRole(), requestDTO.getPermissions());
        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(newRole);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @CheckPermission
    @PutMapping
    public ResponseEntity updateRolePermissions(
            @Valid
            @RequestBody RoleRequestDTO requestDTO
    ) {
        RoleResponseDTO newRole = roleService.updateRole(requestDTO.getRole(), requestDTO.getPermissions());
        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(newRole);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
