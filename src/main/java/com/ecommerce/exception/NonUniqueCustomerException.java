package com.ecommerce.exception;

public class NonUniqueCustomerException extends RuntimeException{
    public NonUniqueCustomerException(String message){
        super(message);
    }
}
