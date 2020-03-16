package com.fresh.restapi.dtos.responses.user;


import com.fresh.restapi.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO extends BaseEntity {
    private String username;
    private String role;
}
