package com.epam.msfrolov.freewms.connection;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(String s) {
        super(s);
    }

    public ConnectionPoolException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
