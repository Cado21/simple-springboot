package com.fresh.restapi.configs;

import com.fresh.restapi.constants.Security;
import com.fresh.restapi.dtos.AuthenticationCustom;
import com.fresh.restapi.exceptions.JwtExpiredException;
import com.fresh.restapi.exceptions.UnAuthorizedException;
import com.fresh.restapi.models.user.UserEntity;
import com.fresh.restapi.repositories.UserRepository;
import com.fresh.restapi.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;

    private UserRepository userRepo;

    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil, UserRepository userRepo) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepo = userRepo;
    }

    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader(Security.HEADER_STRING);
        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith(Security.TOKEN_PREFIX)) {
            int TOKEN_PREFIX_LENGTH = Security.TOKEN_PREFIX.length();
            jwtToken = requestTokenHeader.substring(TOKEN_PREFIX_LENGTH);

            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                throw new UnAuthorizedException("Cannot get token");
            } catch (ExpiredJwtException e) {
                throw new JwtExpiredException("Jwt Token expired!");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Get User Detail by Username
            Optional<UserEntity> optionalUser = userRepo.findByUsername(username);
            UserEntity user = optionalUser.orElse(null);
            if (user != null)
                // if token is valid configure Spring Security to manually set
                if (jwtTokenUtil.validateToken(jwtToken, user)) {
                    List<String> roles = jwtTokenUtil.getPayloadFromToken(jwtToken, "roles");

                    AuthenticationCustom auth = new AuthenticationCustom(user.getUsername(), roles.get(0));
                    auth.setAuthenticated(true);

                    // After setting the Authentication in the context, we specify
                    // that the current user is authenticated. So it passes the
                    // Spring Security Configurations successfully.

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
        }
        chain.doFilter(request, response);
    }
}
