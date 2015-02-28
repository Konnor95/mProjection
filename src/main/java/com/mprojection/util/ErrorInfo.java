package com.mprojection.util;

public class ErrorInfo {

    public final String url;
    public final String exceptionClass;
    public final String exceptionMessage;

    public ErrorInfo(String url, Throwable ex) {
        this.url = url;
        this.exceptionClass = ex.getClass().getName();
        this.exceptionMessage = ex.getMessage();
    }

}
