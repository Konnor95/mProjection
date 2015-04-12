package com.mprojection.util;

public class ErrorInfo {

    public final String url;
    public final Throwable exception;

    public ErrorInfo(String url, Throwable ex) {
        this.url = url;
        this.exception = ex;
    }

}
