package com.fresh.restapi.dtos;

import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class AuthenticationCustom extends AbstractAuthenticationToken {
    private static final long serialVersionUID = -1908755178660923790L;

    @Setter
    private String username;

    @Setter
    private String password;

    public AuthenticationCustom(String username, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
    }
    public AuthenticationCustom(String username , String password ) {
        super(null);
        this.username = username;
        this.password = password;
    }
    @Override
    public Object getCredentials() {
        return this.password == null ? "" : this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.username == null ? "" : this.username;
    }
}
