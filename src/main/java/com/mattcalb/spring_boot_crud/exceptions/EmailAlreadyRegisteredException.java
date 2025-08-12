package com.mattcalb.spring_boot_crud.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }

    public EmailAlreadyRegisteredException() {
        super("Email already registered.");
    }
}
