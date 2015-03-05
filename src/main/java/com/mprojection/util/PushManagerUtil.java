package com.mprojection.util;

import com.mprojection.db.DatabaseConfig;
import com.relayrides.pushy.apns.ApnsEnvironment;
import com.relayrides.pushy.apns.PushManager;
import com.relayrides.pushy.apns.PushManagerConfiguration;
import com.relayrides.pushy.apns.util.*;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

@Component
public class PushManagerUtil {

    final PushManager<SimpleApnsPushNotification> pushManager;

    public PushManagerUtil() throws GeneralSecurityException, IOException {
        InputStream resource = DatabaseConfig.class.getResourceAsStream("/mProjection.p12");
        pushManager = new PushManager<>(
                ApnsEnvironment.getSandboxEnvironment(),
                SSLContextUtil.createDefaultSSLContext(resource, "270495"),
                null, // Optional: custom event loop group
                null, // Optional: custom ExecutorService for calling listeners
                null, // Optional: custom BlockingQueue implementation
                new PushManagerConfiguration(),
                "PushManager");
        pushManager.start();

    }


    public void send(String token, String body, String jsonString) {
        final byte[] tokenBytes;
        try {
            tokenBytes = TokenUtil.tokenStringToByteArray(token);
            final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
            payloadBuilder.addCustomProperty("data", jsonString);
            payloadBuilder.setAlertBody(body);
            payloadBuilder.setSoundFileName("ring-ring.aiff");
            payloadBuilder.setBadgeNumber(1);
            final String payload = payloadBuilder.buildWithDefaultMaximumLength();
            pushManager.getQueue().put(new SimpleApnsPushNotification(tokenBytes, payload));
        } catch (MalformedTokenStringException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() throws InterruptedException {
        pushManager.shutdown();
    }

}
