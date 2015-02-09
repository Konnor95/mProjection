package com.mprojection.db.manager;

import java.sql.Connection;

/**
 * Manages a connection data source.
 *
 * @see java.sql.Connection
 */
public interface ConnectionManager {

    /**
     * Obtains a connection from pool
     *
     * @return obtained connection
     */
    Connection getConnection();

    /**
     * Closes data source connection.
     */
    void shutdown();

}
