package com.fresh.restapi.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    private static final long serialVersionUID = -3086641718582013616L;

    public BadRequestException() {
        super("Bad Request", HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(message , HttpStatus.BAD_REQUEST);
    }

}
