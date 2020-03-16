package com.fresh.restapi.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ForbiddenException extends BaseException {

    private static final long serialVersionUID = -5080607688466016294L;

    public ForbiddenException() {
        super("Forbidden", HttpStatus.FORBIDDEN);
    }

    public ForbiddenException(String message) {
        super(message , HttpStatus.FORBIDDEN);
    }


}
