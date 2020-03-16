package com.fresh.restapi.dtos.requests.user;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@EqualsAndHashCode()
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatePasswordDTO {
    @NotNull(message = "password is required")
    private String password;

    @NotNull(message = "new_password is required")
    private String newPassword;
}
