package com.ecommerce.Ecommerce.customeException;

public class CustomException extends IllegalArgumentException {
    String message;

    public CustomException(String message){
        super(message);
    }

}
