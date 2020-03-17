package com.fresh.restapi.controllers.user;

import com.fresh.restapi.aspect.CheckPermission;
import com.fresh.restapi.utils.JwtTokenUtil;
import com.fresh.restapi.constants.API;
import com.fresh.restapi.converters.ResponseConverter;
import com.fresh.restapi.dtos.BaseResponseDTO;
import com.fresh.restapi.dtos.requests.user.*;
import com.fresh.restapi.dtos.responses.DeleteResponseDTO;
import com.fresh.restapi.dtos.responses.user.UserLoginResponseDTO;
import com.fresh.restapi.dtos.responses.user.UserResponseDTO;
import com.fresh.restapi.services.user.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = API.V1 + API.USERS, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @CheckPermission
    @GetMapping
    public ResponseEntity getAllUsers() {
        List<UserResponseDTO> allUsers = userService.getAllUsers();
        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(allUsers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(
            @Valid @RequestBody
                    UserLoginRequestDTO req
    ) {
        UserLoginResponseDTO dto = userService.login(req);
        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register(
            @Valid @RequestBody
                    UserRequestDTO req
    ) {
        UserResponseDTO dto = userService.createNewUser(req);
        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CheckPermission
    @PutMapping(path = "/password")
    public ResponseEntity updatePassword(
            @Valid @RequestBody
                    UserUpdatePasswordDTO req,
            @RequestHeader String authorization
    ) {
        String jwtToken = jwtTokenUtil.removeAuthorizationPrefix(authorization);
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        UserResponseDTO dto = userService.updatePasswordByUsername(
                username,
                req.getPassword(),
                req.getNewPassword()
        );

        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CheckPermission
    @PutMapping(path = "/roles")
    public ResponseEntity updateRolesByUsername(
            @Valid @RequestBody
                    UserUpdateRolesDTO req,
            @RequestHeader String authorization
    ) {
        String jwtToken = jwtTokenUtil.removeAuthorizationPrefix(authorization);
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        UserResponseDTO dto = userService.updateRolesByUsername(username ,req.getRoles());
        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CheckPermission
    @DeleteMapping
    public ResponseEntity deleteUser(
            @RequestHeader String authorization
    ) {
        String jwtToken = jwtTokenUtil.removeAuthorizationPrefix(authorization);
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        DeleteResponseDTO dto = userService.deleteUserByUsername(username);
        BaseResponseDTO response = ResponseConverter.anyToBasicResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
