package com.ecommerce.tradeon.Exceptions;


public class CustomeMemberException extends RuntimeException{

    public CustomeMemberException() {
        super();
    }

    public CustomeMemberException(String message) {
        super(message);
    }
}
