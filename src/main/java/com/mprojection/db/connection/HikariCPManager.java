package com.mprojection.db.connection;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A connection manager, based on HikariCP tool.
 */
@Component
public class HikariCPManager implements ConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(HikariCPManager.class);
    private static final boolean CACHE_PREPARE_STATEMENTS = true;
    private static final int PREPARE_STATEMENTS_CACHE_SIZE = 250;
    private static final int PREPARE_STATEMENTS_CACHE_SQL_LIMIT = 2048;
    private static final boolean USE_SERVER_PREPARE_STATEMENTS = true;

    @Autowired
    private HikariDataSource dataSource;

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Connection wasn't set", e);
            throw new IllegalStateException("Connection wasn't set", e);
        }
    }

    @Override
    public void shutdown() {
        dataSource.close();
    }
}
