package org.DigitalDucks.BARSDAG.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalNotFoundHandler {

    @ExceptionHandler(GlobalNotFoundException.class)
    public ResponseEntity<Object> handleGlobalNotFoundException(GlobalNotFoundException exception) {
        GlobalNotFound globalNotFound = new GlobalNotFound(
                exception.getMessage(),
                exception.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(globalNotFound, HttpStatus.NOT_FOUND);
    }
}
