package com.fresh.restapi.dtos.responses.role;


import com.fresh.restapi.dtos.BaseEntityResponseDTO;
import com.fresh.restapi.dtos.responses.permission.PermissionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDTO extends BaseEntityResponseDTO {
        private String role;
        private List<PermissionResponseDTO> permissions;
}
