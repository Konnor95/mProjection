package com.mprojection.serializer;

/**
 * {@code SerializerException} is thrown by {@code StreamSerializer}
 * when it cannot serialize or deserialize object or list of objects.
 *
 * @see ObjectSerializer
 */
public class SerializerException extends RuntimeException {

    /**
     * Creates a new {@code SerializerException} object with a specified message and cause.
     *
     * @param message message of the exception
     * @param cause   cause of the exception
     */
    public SerializerException(String message, Throwable cause) {
        super(message, cause);
    }

}
