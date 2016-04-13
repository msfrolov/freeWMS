package com.epam.msfrolov.freewms.connection;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String s) {
        super(s);
    }

    public ConnectionException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
