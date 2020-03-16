package com.fresh.restapi.converters;

import com.fresh.restapi.dtos.responses.user.UserResponseDTO;
import com.fresh.restapi.models.user.UserEntity;
import com.fresh.restapi.models.user.UserRoleEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter extends BaseEntityConverter<UserEntity, UserResponseDTO>{
    public UserResponseDTO toResponseDTO ( UserEntity entity ) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        List<String> roles = new ArrayList<>();
        BeanUtils.copyProperties(entity, responseDTO, "password");
        if( !entity.getUserRoles().isEmpty()) {
            for( UserRoleEntity userRole : entity.getUserRoles())  {
                roles.add(userRole.getRole().getName());
            }
        }
        responseDTO.setRoles(roles);
        this.setBaseEntityDTO( entity, responseDTO );
        return responseDTO;
    }
}
