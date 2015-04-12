package com.mprojection.util;

import java.util.List;

public class ErrorInfo {

    public final String url;
    public final Throwable exception;
    public List<Throwable> causes;

    public ErrorInfo(String url, Throwable ex) {
        this.url = url;
        this.exception = ex;
        setCause(ex);
    }

    private void setCause(Throwable ex) {
        if (ex.getCause() == null) {
            return;
        }
        causes.add(ex.getCause());
        setCause(ex.getCause());
    }

}
