package com.fresh.restapi.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -6904886707770764747L;

    protected String code;
    protected HttpStatus httpStatus;

    public BaseException(String message, HttpStatus httpStatus) {
        super(message);
        this.setCode(httpStatus.toString());
        this.setHttpStatus(httpStatus);
    }

}
