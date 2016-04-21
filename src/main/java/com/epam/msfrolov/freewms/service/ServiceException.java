package com.epam.msfrolov.freewms.service;

public class ServiceException extends RuntimeException {
    public ServiceException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ServiceException(String s) {
        super(s);
    }
}
