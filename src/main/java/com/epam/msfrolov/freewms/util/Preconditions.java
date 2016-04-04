package com.epam.msfrolov.freewms.util;

public class Preconditions {
    public static void checkNotNull(Object o) {
        if (o == null) throw new AppException("null validation fails");
    }
}
