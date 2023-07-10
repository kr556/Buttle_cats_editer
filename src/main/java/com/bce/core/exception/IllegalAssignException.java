package com.bce.core.exception;

public class IllegalAssignException extends IllegalArgumentException {
    public IllegalAssignException(String message) {
        super(message);
    }

    public IllegalAssignException() {
        super();
    }
}
