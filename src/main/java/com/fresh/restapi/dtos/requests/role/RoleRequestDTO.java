package com.fresh.restapi.dtos.requests.role;

import com.fresh.restapi.dtos.requests.permission.PermissionRequestDTO;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Builder
@EqualsAndHashCode()
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDTO {
    @NotNull(message = "role must not be null")
    private String role;

    @NotNull(message = "permissions must not be null")
    private List<PermissionRequestDTO> permissions;
}
