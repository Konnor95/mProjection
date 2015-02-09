package com.mprojection.exception;


/**
 * {@code FileException} is thrown when the the error occurs while working with files.
 */
public class FileException extends RuntimeException {

    /**
     * Creates a new {@code FileException} object with a specified message.
     *
     * @param message message of the exception
     */
    public FileException(String message) {
        super(message);
    }

    /**
     * Creates a new {@code FileException} object with a specified message and cause.
     *
     * @param message message of the exception
     * @param cause   cause of the exception
     */
    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new {@code FileException} object with a specified cause.
     *
     * @param cause cause of the exception
     */
    public FileException(Throwable cause) {
        super(cause);
    }

}
