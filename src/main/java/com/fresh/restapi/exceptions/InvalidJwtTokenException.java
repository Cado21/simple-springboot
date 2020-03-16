package com.fresh.restapi.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidJwtTokenException extends BaseException {
    private static final long serialVersionUID = 9003064701372526790L;

    public InvalidJwtTokenException() {
        super("Invalid Jwt Token!", HttpStatus.BAD_REQUEST);
    }

    public InvalidJwtTokenException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
