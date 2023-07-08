package com.deliveroo.exceptions;

public class InvalidCronFieldException extends Throwable {

    public InvalidCronFieldException(String message) {
        super(message);
    }
}
