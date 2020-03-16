package com.fresh.restapi.dtos.responses.permission;

import com.fresh.restapi.dtos.BaseEntityResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponseDTO extends BaseEntityResponseDTO {
    private String httpMethod;
    private String pathURI;
}
