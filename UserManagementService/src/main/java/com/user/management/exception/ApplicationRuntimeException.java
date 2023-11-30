package com.user.management.exception;

import lombok.Getter;

/**
 * Custom application runtime exception
 */
@Getter
public class ApplicationRuntimeException extends RuntimeException{
    private int statusCode;

    public ApplicationRuntimeException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ApplicationRuntimeException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}
