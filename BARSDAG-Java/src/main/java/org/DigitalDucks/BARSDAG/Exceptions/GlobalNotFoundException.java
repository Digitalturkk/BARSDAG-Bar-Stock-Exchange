package org.DigitalDucks.BARSDAG.Exceptions;

public class GlobalNotFoundException extends RuntimeException {
    public GlobalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
