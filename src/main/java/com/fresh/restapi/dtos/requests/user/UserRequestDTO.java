package com.fresh.restapi.dtos.requests.user;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Builder
@EqualsAndHashCode()
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "username is required")
    private String username;

    @NotNull(message = "password is required")
    private String password;

    @NotNull(message = "roles must not be null")
    private List<String> roles;
}
