package com.petshop.manager.data.exception;

public class ValidationException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public ValidationException(java.lang.String message, java.lang.Throwable cause) {
        super(message, cause);
    }
    public ValidationException(java.lang.String message) {
        super(message);
    }
}