package com.mprojection.exception;

public class DAOException extends RuntimeException {

    /**
     * Creates a new {@code DAOException} object with a specified message and cause.
     *
     * @param message message of the exception
     * @param cause   cause of the exception
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
