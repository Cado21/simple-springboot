package com.fresh.restapi.exceptions;

import org.springframework.http.HttpStatus;

public class JwtExpiredException extends BaseException {

    private static final long serialVersionUID = -4356032368812658643L;

    public JwtExpiredException() {
        super("Jwt Token Expired!", HttpStatus.UNAUTHORIZED);
    }

    public JwtExpiredException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

}
