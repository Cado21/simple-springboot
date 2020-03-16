package com.fresh.restapi.advices;


import com.fresh.restapi.dtos.responses.ErrorResponseDTO;
import com.fresh.restapi.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> missingRequiredValueError(HttpMessageNotReadableException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Required Body is Missing!", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidJwtTokenException.class)
    public ResponseEntity<ErrorResponseDTO> invalidJwtTokenError(InvalidJwtTokenException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<ErrorResponseDTO> forbiddenError(ForbiddenException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(value = JwtExpiredException.class)
    public ResponseEntity<ErrorResponseDTO> expiredTokenError(JwtExpiredException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundError(NotFoundException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> badRequestError(BadRequestException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(value = UnAuthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> unAuthorizedError(UnAuthorizedException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

}
