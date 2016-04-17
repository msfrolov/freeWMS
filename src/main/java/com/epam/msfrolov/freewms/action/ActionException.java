package com.epam.msfrolov.freewms.action;

public class ActionException extends RuntimeException {
    public ActionException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ActionException(String s) {
        super(s);
    }
}
