package com.fresh.restapi.services.auth;

import com.fresh.restapi.configs.JwtTokenUtil;
import com.fresh.restapi.dtos.AuthenticationCustom;
import com.fresh.restapi.dtos.requests.user.UserTokenPayloadDTO;
import com.fresh.restapi.dtos.responses.user.UserLoginResponseDTO;
import com.fresh.restapi.exceptions.UnAuthorizedException;
import com.fresh.restapi.models.user.UserEntity;
import com.fresh.restapi.models.user.UserRoleEntity;
import com.fresh.restapi.repositories.UserRepository;
import com.fresh.restapi.repositories.UserRoleRepository;
import com.fresh.restapi.services.user.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtAuthenticationService implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String username = (String) authentication.getPrincipal();
            Optional<UserEntity> optionalUser = userRepository.findByUsername(username);


            UserEntity user = optionalUser.orElseThrow(() ->
                    new UnAuthorizedException("Invalid username or password")
            );

            boolean validPasword = passwordEncoder.matches(
                    (String) authentication.getCredentials(),
                    user.getPassword()
            );

            if (!validPasword) throw new UnAuthorizedException("Invalid username or password");
            List<String> roles = new ArrayList<>();
            List<UserRoleEntity> userRoles = user.getUserRoles();

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (UserRoleEntity role : userRoles) {
                String roleName = role.getRole().getName();
                roles.add(roleName);
                grantedAuthorities.add( new SimpleGrantedAuthority("ROLE_" + roleName.toUpperCase()));
            }
            UserLoginResponseDTO response = new UserLoginResponseDTO();
            UserTokenPayloadDTO userPayload = new UserTokenPayloadDTO();

            AuthenticationCustom auth = new AuthenticationCustom(
                    (String) authentication.getPrincipal(),
                    grantedAuthorities
            );

            userPayload.setUsername(user.getUsername());
            userPayload.setRoles(roles);

            String token = jwtTokenUtil.generateToken((String) auth.getPrincipal(), userPayload);
            response.setToken(token);

            auth.setDetails(response);
            return auth;

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
