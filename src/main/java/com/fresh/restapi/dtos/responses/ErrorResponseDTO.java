package com.fresh.restapi.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO implements Serializable {
    private static final long serialVersionUID = 123879987123L;

    private int status;
    private HttpStatus code;
    private Object message;
    private ZonedDateTime timestamp;

    public ErrorResponseDTO(Object message, HttpStatus code) {
        super();
        this.message = message;
        this.code = code;
        this.timestamp = ZonedDateTime.now();
        this.status = code.value();
    }

}
