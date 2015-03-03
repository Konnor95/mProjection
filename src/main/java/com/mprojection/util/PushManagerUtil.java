package com.mprojection.util;

import com.mprojection.db.DatabaseConfig;
import com.relayrides.pushy.apns.ApnsEnvironment;
import com.relayrides.pushy.apns.FailedConnectionListener;
import com.relayrides.pushy.apns.PushManager;
import com.relayrides.pushy.apns.PushManagerConfiguration;
import com.relayrides.pushy.apns.util.*;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.net.ssl.SSLHandshakeException;
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
                SSLContextUtil.createDefaultSSLContext(resource, "vova2704"),
                null, // Optional: custom event loop group
                null, // Optional: custom ExecutorService for calling listeners
                null, // Optional: custom BlockingQueue implementation
                new PushManagerConfiguration(),
                "MainPushManager");
        pushManager.registerFailedConnectionListener(new MyFailedConnectionListener());
        pushManager.start();

    }


    public void send(String token, String jsonString) {
        final byte[] tokenBytes;
        try {
            tokenBytes = TokenUtil.tokenStringToByteArray(
                    "<927d54e5 88a56875 bed2f8a7 490278c0 c28c573e 8c6db3a8 ee2d8351 5bf31048>");
            final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
            payloadBuilder.setAlertBody("Ring ring, Neo.");
            payloadBuilder.setSoundFileName("ring-ring.aiff");
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
    private class MyFailedConnectionListener implements FailedConnectionListener<SimpleApnsPushNotification> {

        @Override
        public void handleFailedConnection(
                final PushManager<? extends SimpleApnsPushNotification> pushManager,
                final Throwable cause) {

            if (cause instanceof SSLHandshakeException) {
                try {
                    pushManager.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

// ...


}
