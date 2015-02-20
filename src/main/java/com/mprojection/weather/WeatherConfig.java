package com.mprojection.weather;

import com.mprojection.db.DatabaseConfig;
import com.mprojection.exception.FileException;
import com.mprojection.weather.openweather.OpenWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Stores weather configuration.
 */
public final class WeatherConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
    private static final String CONFIG_FILE = "/weather.properties";
    private static final  Properties PROPERTIES;

    static {
        try (InputStream resource = OpenWeatherService.class.getResourceAsStream(CONFIG_FILE)) {
            PROPERTIES = new Properties();
            PROPERTIES.load(resource);
        } catch (IOException e) {
            LOGGER.error("Cannot load config file: '{}'", CONFIG_FILE);
            throw new FileException("Cannot load config file: '" + CONFIG_FILE + "'", e);
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

}
