package com.mprojection.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public final class URLHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(URLHelper.class);

    public static InputStream getInputStream(String url) {
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            return connection.getInputStream();
        } catch (Exception e) {
            LOGGER.warn("Cannot get stream from URL: '{}'", url, e);
            return null;
        }
    }
}
