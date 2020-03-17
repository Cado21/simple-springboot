package com.fresh.restapi.dtos.responses.permission;

import com.fresh.restapi.dtos.BaseEntityResponseDTO;
import lombok.*;


@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponseDTO extends BaseEntityResponseDTO {
    private String httpMethod;
    private String pathURI;
}
