package com.mprojection.db;

import com.mprojection.exception.FileException;
import com.mprojection.util.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * DatabaseConfig class.
 *
 * @author Dmitry Bekuzarov
 */
public final class DatabaseConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
    private static final Properties PROPERTIES;
    private static final String DATA_SOURCE;
    private static final String HOST;
    private static final String PORT;
    private static final String DATABASE;
    private static final String USER;
    private static final String PASSWORD;
    private static final String TRANSACTION_ISOLATION;
    private static final String PREPARED_STATEMENT_CACHE_SIZE;
    private static final String PARSED_SQL_CACHE_SIZE;

    static {
        String file = "/db" + Environment.get().getPropertiesPostfix();
        try (InputStream resource = DatabaseConfig.class.getResourceAsStream(file)) {
            PROPERTIES = new Properties();
            PROPERTIES.load(resource);
            DATA_SOURCE = get("dataSource");
            HOST = get("host");
            PORT = get("port");
            DATABASE = get("database");
            USER = get("user");
            PASSWORD = get("password");
            TRANSACTION_ISOLATION = get("transactionIsolation");
            PREPARED_STATEMENT_CACHE_SIZE = get("preparedStatementCacheSize");
            PARSED_SQL_CACHE_SIZE = get("parsedSqlCacheSize");
        } catch (IOException e) {
            LOGGER.error("Cannot load config file: '{}'", file);
            throw new FileException("Cannot load config file: '" + file + "'", e);
        }
    }

    public static String getDataSource() {
        return DATA_SOURCE;
    }

    public static String getHost() {
        return HOST;
    }

    public static String getPort() {
        return PORT;
    }

    public static String getDatabase() {
        return DATABASE;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getTransactionIsolation() {
        return TRANSACTION_ISOLATION;
    }

    public static String getPreparedStatementCacheSize() {
        return PREPARED_STATEMENT_CACHE_SIZE;
    }

    public static String getParsedSqlCacheSize() {
        return PARSED_SQL_CACHE_SIZE;
    }

    private static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value.startsWith("$")) {
            return System.getenv(value.substring(1));
        }
        return value;
    }

    private DatabaseConfig() {
    }

}
