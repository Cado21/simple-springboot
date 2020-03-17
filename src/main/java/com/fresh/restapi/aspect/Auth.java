package com.fresh.restapi.aspect;

import com.fresh.restapi.utils.JwtTokenUtil;
import com.fresh.restapi.constants.Security;
import com.fresh.restapi.exceptions.ForbiddenException;
import com.fresh.restapi.models.permission.PermissionEntity;
import com.fresh.restapi.models.role.RolePermissionEntity;
import com.fresh.restapi.repositories.RolePermissionRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Aspect
@Component
public class Auth {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Before("@annotation(com.fresh.restapi.aspect.CheckPermission)")
    public void checkPermission() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String httpMethod = request.getMethod();
        String uri = request.getRequestURI().replaceAll("/[0-9]+" , "/");
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith(Security.TOKEN_PREFIX)) {
            String jwtToken = jwtTokenUtil.removeAuthorizationPrefix(header);
            List<String> roles = jwtTokenUtil.getPayloadFromToken(jwtToken, "roles");


            boolean authorizedPath = false;
            for ( String eachRole : roles ) {
                List<RolePermissionEntity> rolePermissionEntities = rolePermissionRepository.findAllByRole_Name(eachRole);
                for (RolePermissionEntity rolePermissionEntity : rolePermissionEntities) {
                    PermissionEntity permission = rolePermissionEntity.getPermission();

                    String validPermissionString = permission.getHttpMethod().toUpperCase();
                    String validPermissionUri = permission.getPathUri().replaceAll("/\\{[a-z|A-Z]*}" , "/");

                    boolean isValidHttpMethod = validPermissionString.equals(httpMethod.toUpperCase());
                    boolean isPermittedUri = validPermissionUri.equals(uri);
                    if (isValidHttpMethod && isPermittedUri) {
                        authorizedPath = true;
                        break;
                    }
                }
            }

            if (!authorizedPath) throw new ForbiddenException("You are not permitted to access this endpoint!");
        } else {
            throw new BadCredentialsException("You need to login first!");
        }
    }

    @Before("@annotation(com.fresh.restapi.aspect.NeedToken)")
    public void needToken() {
        System.out.println("JALAN");
    }
}
