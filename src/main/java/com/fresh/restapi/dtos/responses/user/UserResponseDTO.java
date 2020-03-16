package com.fresh.restapi.dtos.responses.user;

import com.fresh.restapi.dtos.BaseEntityResponseDTO;
import lombok.*;

import java.util.List;


@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO extends BaseEntityResponseDTO {
    private String name;
    private String username;
    private List<String> roles;
}


