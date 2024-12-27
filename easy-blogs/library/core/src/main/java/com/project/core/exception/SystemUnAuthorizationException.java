package com.project.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class SystemUnAuthorizationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1372210541405289235L;

    public SystemUnAuthorizationException(String message) {
        super(message);
    }
}