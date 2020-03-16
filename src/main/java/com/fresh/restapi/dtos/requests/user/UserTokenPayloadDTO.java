package com.fresh.restapi.dtos.requests.user;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@ToString(callSuper = true)
public class UserTokenPayloadDTO {
    private String username;
    private List<String> roles;
}
