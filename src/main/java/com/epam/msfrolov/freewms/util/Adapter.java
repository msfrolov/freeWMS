package com.epam.msfrolov.freewms.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Adapter {

    public boolean checkType(Object o) {
        return (o instanceof String ||
                o instanceof LocalDate ||
                o instanceof LocalDateTime ||
                o instanceof Integer ||
                o instanceof Boolean);
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
        if (fieldClass == LocalDateTime.class) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            return formatter.parse(s);
        } else if (fieldClass == LocalDate.class) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            return formatter.parse(s);
        } else if (fieldClass == String.class) {
            return s;
        } else if (fieldClass == Integer.class) {
            return Integer.valueOf(s);
        } else if (fieldClass == Boolean.class) {
            return Boolean.valueOf(s);
        } else throw new AppException("condition was not provided");
    }
}
