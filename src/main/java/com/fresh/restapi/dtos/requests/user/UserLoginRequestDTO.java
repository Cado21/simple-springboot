package com.fresh.restapi.dtos.requests.user;


import lombok.*;

@Data
@Builder
@EqualsAndHashCode()
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDTO {
    private String username;
    private String password;
}
