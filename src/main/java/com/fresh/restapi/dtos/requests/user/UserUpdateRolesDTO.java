package com.fresh.restapi.dtos.requests.user;

import lombok.*;

import java.util.List;


@Data
@Builder
@EqualsAndHashCode()
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRolesDTO {
    private List<String> roles;
}
