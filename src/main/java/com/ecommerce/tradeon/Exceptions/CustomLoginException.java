package com.ecommerce.tradeon.Exceptions;

public class CustomLoginException extends RuntimeException {

    public CustomLoginException() {
    }

    public CustomLoginException(String message) {
        super(message);
    }
}
