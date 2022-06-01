package com.guide.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptionRequest extends RuntimeException {
    public ResourceNotFoundExceptionRequest(String message) {
        super(message);
    }
}
