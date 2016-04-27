package com.epam.msfrolov.freewms.filter;

public class FilterException extends RuntimeException {
    public FilterException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FilterException(String s) {
        super(s);
    }
}
