package com.fresh.restapi.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    private static final long serialVersionUID = 1742934592921258106L;

    public NotFoundException() {
        super("Not Found", HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message , HttpStatus.NOT_FOUND);
    }

}
