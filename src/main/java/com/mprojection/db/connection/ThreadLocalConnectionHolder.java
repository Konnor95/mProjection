package com.mprojection.db.connection;

import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * Provides thread-safe implementation of {@link ConnectionHolder}
 * interface.
 *
 * @see ThreadLocal
 */
@Component
public class ThreadLocalConnectionHolder implements ConnectionHolder {

    private final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    @Override
    public Connection get() {
        return connectionHolder.get();
    }

    @Override
    public void set(Connection connection) {
        connectionHolder.set(connection);
    }

    @Override
    public void remove() {
        connectionHolder.remove();
    }
}
