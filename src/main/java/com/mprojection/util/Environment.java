package com.mprojection.util;

import com.mprojection.db.DatabaseConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public final class Environment {

    private static final String PROPERTIES_EXTENSION = ".properties";
    private static final Environment ENVIRONMENT;
    private final String propertiesPostfix;

    static {
        String postFix = PROPERTIES_EXTENSION;
        try (InputStream resource = DatabaseConfig.class.getResourceAsStream("/env.properties")) {
            Properties properties = new Properties();
            properties.load(resource);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                if (System.getenv(entry.getValue().toString()) != null) {
                    postFix = "-" + entry.getKey().toString() + PROPERTIES_EXTENSION;
                    break;
                }
            }
        } catch (IOException e) {
            postFix = PROPERTIES_EXTENSION;
        }
        ENVIRONMENT = new Environment(postFix);
    }

    public static Environment get() {
        return ENVIRONMENT;
    }

    public String getPropertiesPostfix() {
        return propertiesPostfix;
    }

    private Environment(String propertiesPostfix) {
        this.propertiesPostfix = propertiesPostfix;
    }

}
