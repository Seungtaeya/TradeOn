package com.ecommerce.tradeon.Exceptions;

public class CustomRegisterException extends RuntimeException{

    public CustomRegisterException() {
    }

    public CustomRegisterException(String message) {
        super(message);
    }
}
