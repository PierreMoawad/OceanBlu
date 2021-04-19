package com.pierre.oceanblu.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {

        super("The page you're requesting doesn't exist");
    }
}
