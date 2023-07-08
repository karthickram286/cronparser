package com.deliveroo.exceptions;

public class InvalidCronStringException extends Throwable {

    public InvalidCronStringException(String message) {
        super(message);
    }
}
