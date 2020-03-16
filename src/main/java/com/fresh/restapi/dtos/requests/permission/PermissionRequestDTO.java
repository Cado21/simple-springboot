package com.fresh.restapi.dtos.requests.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequestDTO {
    private String httpMethod;
    private String pathUri;
}
