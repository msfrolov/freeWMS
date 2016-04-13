package com.epam.msfrolov.freewms.util;

public class AppException extends RuntimeException {
    public AppException(String s) {
        super(s);
    }

    public AppException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
