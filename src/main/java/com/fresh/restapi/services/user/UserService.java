package com.fresh.restapi.services.user;

import com.fresh.restapi.dtos.requests.user.UserLoginRequestDTO;
import com.fresh.restapi.dtos.requests.user.UserRequestDTO;
import com.fresh.restapi.dtos.responses.DeleteResponseDTO;
import com.fresh.restapi.dtos.responses.user.UserLoginResponseDTO;
import com.fresh.restapi.dtos.responses.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO createNewUser(UserRequestDTO request);
    UserResponseDTO updatePasswordByUsername( String username, String password, String newPassword );
    UserResponseDTO updateRolesByUsername ( String username , List<String> roles );
    DeleteResponseDTO deleteUserByUsername (String username );
    UserLoginResponseDTO login(UserLoginRequestDTO request);
}
