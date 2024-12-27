package com.project.core.exception;

import java.io.Serial;

public class SystemRuntimeException extends RuntimeException {


    @Serial
    private static final long serialVersionUID = 1535482892314372535L;

    public SystemRuntimeException(String message) {
        super(message);
    }

    public SystemRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemRuntimeException(Throwable cause) {
        super(cause);
    }
}
