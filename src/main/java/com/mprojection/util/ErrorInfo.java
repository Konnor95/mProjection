package com.mprojection.util;

public class ErrorInfo {

    public final String url;
    public final String exceptionMessage;

    public ErrorInfo(String url, Exception exceptionMessage) {
        this.url = url;
        this.exceptionMessage = exceptionMessage.getLocalizedMessage();
    }

}
