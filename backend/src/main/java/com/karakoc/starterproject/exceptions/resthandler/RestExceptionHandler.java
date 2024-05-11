package com.karakoc.starterproject.exceptions.resthandler;

import com.karakoc.starterproject.exceptions.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    //for business rules
    @ExceptionHandler({RestException.class})
    public ResponseEntity<Object> handleRestException(RestException generalException) {
        HttpStatus httpStatus = (generalException.getHttpStatus() != null) ? generalException.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(generalException.getHttpStatus())
                .body(generalException.getMessage());
    }

    //for request validations.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }


}
