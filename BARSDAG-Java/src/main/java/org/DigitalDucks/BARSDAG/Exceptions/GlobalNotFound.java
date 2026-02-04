package org.DigitalDucks.BARSDAG.Exceptions;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class GlobalNotFound {
    private String message;
    @JsonIgnore
    private Throwable cause;
    private HttpStatus status;
    private int statusCode;

    public GlobalNotFound(String message, Throwable cause, HttpStatus status) {
        this.message = message;
        this.cause = cause;
        this.status = status;
        this.statusCode = status.value();
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Throwable getCause() {
        return cause;
    }
}
