package com.epam.msfrolov.freewms.dao;

public class DaoException extends RuntimeException {
    public DaoException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DaoException(String s) {
        super(s);
    }
}
