package com.mprojection.exception;

public class LogicalException extends RuntimeException {

    /**
     * Creates a new {@code LogicalException} object with a specified message.
     *
     * @param message message of the exception
     */
    public LogicalException(String message) {
        super(message);
    }
}