package com.sanjeet.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resource, String input,String value) {
        super("requested " +resource + " with " + input + " " + value + " not found");
    }
}
