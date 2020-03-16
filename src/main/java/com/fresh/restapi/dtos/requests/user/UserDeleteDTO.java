package com.fresh.restapi.dtos.requests.user;


import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@EqualsAndHashCode()
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDeleteDTO {
    @NotNull(message = "username is required")
    private String username;
}
