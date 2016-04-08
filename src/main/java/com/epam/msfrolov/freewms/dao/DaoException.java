package com.epam.msfrolov.freewms.dao;

public class DaoException extends Exception {
    public DaoException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DaoException(String s) {
        super(s);
    }
}
