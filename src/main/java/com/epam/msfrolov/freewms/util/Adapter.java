package com.epam.msfrolov.freewms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Adapter {

    private static final Logger log = LoggerFactory.getLogger(Adapter.class);

    public boolean checkType(Object o) {
            return (o instanceof String ||
                    o instanceof LocalDate ||
                    o instanceof LocalDateTime ||
                    o instanceof Integer ||
                    o instanceof Boolean);
    }

    public boolean checkClass(Class o) {
        return (o == String.class ||
                o == LocalDate.class ||
                o == LocalDateTime.class ||
                o == Integer.class ||
                o == Boolean.class);
    }

    public String serialize(Object o) {
        if (o instanceof LocalDateTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            return ((LocalDateTime) o).format(formatter);
        } else if (o instanceof LocalDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            return ((LocalDate) o).format(formatter);
        } else if (o instanceof String) {
            return (String) o;
        } else if (o instanceof Integer || o instanceof Boolean) {
            return String.valueOf(o);
        } else throw new AppException("condition was not provided");
    }

    public Object deserialize(String s, Class fieldClass) {
        log.debug("deserialize: {} - {}", s, fieldClass);
        if (s == null)
            return null;
        else if (fieldClass == LocalDateTime.class) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            return LocalDateTime.parse(s, formatter);
        } else if (fieldClass == LocalDate.class) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            return LocalDate.parse(s, formatter);
        } else if (fieldClass == String.class) {
            return s;
        } else if (fieldClass == Integer.class) {
            return Integer.valueOf(s);
        } else if (fieldClass == Boolean.class) {
            return Boolean.valueOf(s);
        } else throw new AppException("condition was not provided");

    }
}
