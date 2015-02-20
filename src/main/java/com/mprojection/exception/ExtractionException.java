package com.mprojection.exception;

/**
 * {@code ExtractionException} is thrown then the {@code Extractor}
 * cannot extract the object.
 *
 * @author Dmitry Bekuzarov
 * @see com.mprojection.db.Extractor
 */
public class ExtractionException extends RuntimeException {

    /**
     * Creates a new {@code ExtractionException} object with a specified message and cause.
     *
     * @param message message of the exception
     * @param cause   cause of the exception
     */
    public ExtractionException(String message, Throwable cause) {
        super(message, cause);
    }

}
