package com.mprojection.db.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.SQLException;

import static com.mprojection.db.DatabaseConfig.*;

/**
 * A connection manager, based on HikariCP tool.
 */
@Component
public class HikariCPManager implements ConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(HikariCPManager.class);
    private HikariDataSource dataSource;

    /**
     * Instantiates a new manager.
     */
    public HikariCPManager() {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(getDataSource());
        config.setInitializationFailFast(true);
        String s = getHost();
        config.addDataSourceProperty("host", getHost());
        config.addDataSourceProperty("port", getPort());
        config.addDataSourceProperty("database", getDatabase());
        config.addDataSourceProperty("user", getUser());
        config.addDataSourceProperty("password", getPassword());
        config.addDataSourceProperty("preparedStatementCacheSize", getPreparedStatementCacheSize());
        config.addDataSourceProperty("parsedSqlCacheSize", getParsedSqlCacheSize());
        config.setTransactionIsolation("TRANSACTION_" + getTransactionIsolation());
        dataSource = new HikariDataSource(config);
    }

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
    @PreDestroy
    public void shutdown() {
        dataSource.close();
    }
}
