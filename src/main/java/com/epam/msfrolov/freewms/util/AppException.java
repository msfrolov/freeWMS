package com.epam.msfrolov.freewms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppException extends RuntimeException{
    private static Logger log = LoggerFactory.getLogger(AppException.class);
    public AppException(String s) {
        super(s);
        log.debug(s);
    }

    public AppException(String s, Throwable throwable) {
        super(s, throwable);
        log.debug(s,throwable);
    }
}
