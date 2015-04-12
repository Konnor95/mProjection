package com.mprojection.util;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

public class ErrorInfo {

    public final String url;
    public final String exceptionClass;
    public final String exceptionMessage;

    public ErrorInfo(String url, Throwable ex) {
        this.url = url;
        this.exceptionClass = ex.getClass().getName();
        this.exceptionMessage = getStackTrace(ex);
    }

}
