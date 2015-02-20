package com.mprojection.db;

import com.mprojection.exception.FileException;
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

    private static final String DATABASE_CONFIG_FILE = "/db.properties";
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
    private static final Properties PROPERTIES;
    private static final String SERVER;
    private static final String PORT;
    private static final String DATABASE;
    private static final String RDBMS;
    private static final String USER;
    private static final String PASSWORD;
    private static final String TRANSACTION_ISOLATION;
    private static final String ENCODING;
    private static final String AUTO_RECONNECT;
    private static final String CONNECTION_URL;

    static {
        try (InputStream resource = DatabaseConfig.class.getResourceAsStream(DATABASE_CONFIG_FILE)) {
            PROPERTIES = new Properties();
            PROPERTIES.load(resource);
            SERVER = get("server");
            PORT = get("port");
            DATABASE = get("database");
            RDBMS = get("rdbms");
            USER = get("user");
            PASSWORD = get("password");
            TRANSACTION_ISOLATION = get("transactionIsolation");
            ENCODING = get("encoding");
            AUTO_RECONNECT = get("autoReconnect");
            CONNECTION_URL = defineConnectionUrl();
        } catch (IOException e) {
            LOGGER.error("Cannot load config file: '{}'", DATABASE_CONFIG_FILE);
            throw new FileException("Cannot load config file: '" + DATABASE_CONFIG_FILE + "'", e);
        }
    }

    public static String getServer() {
        return SERVER;
    }

    public static String getPort() {
        return PORT;
    }

    public static String getDatabase() {
        return DATABASE;
    }

    public static String getRdbms() {
        return RDBMS;
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

    public static String getConnectionUrl() {
        return CONNECTION_URL;
    }

    private static String defineConnectionUrl() {
        return "jdbc:" + RDBMS + "://" + SERVER + ":" + PORT + "/"
                + DATABASE + "?characterEncoding=" + ENCODING + "&autoReconnect=" + AUTO_RECONNECT;

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
