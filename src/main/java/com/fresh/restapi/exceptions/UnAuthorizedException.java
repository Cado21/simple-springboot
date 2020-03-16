package com.fresh.restapi.exceptions;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends BaseException {
    private static final long serialVersionUID = -7460661806835281725L;

    public UnAuthorizedException() {
        super("UnAuthorized", HttpStatus.UNAUTHORIZED);
    }

    public UnAuthorizedException(String message) {
        super(message , HttpStatus.UNAUTHORIZED);
    }

}
