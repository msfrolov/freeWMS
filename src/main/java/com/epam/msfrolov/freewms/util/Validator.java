package com.epam.msfrolov.freewms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static final String LETTERS_DIGITS_WS = "^[а-яА-ЯёЁa-zA-Z()_ 0-9-]+$";
    public static final String LETTERS_DIGITS_WS_MIN5_MAX32 = "^[а-яА-ЯёЁa-zA-Z()_ 0-9-]{5,32}$";
    public static final String LETTERS_DIGITS = "^[а-яА-ЯёЁa-z_A-Z()0-9-]+$";
    public static final String DIGITS = "^[0-9]+$";
    public static final String DIGITS_MIN1_MAX9 = "^[0-9]{1,9}$";
    public static final String LETTERS = "^[а-яА-ЯёЁa-zA-Z()_]+$";
    public static final String DATE = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";
    public static final String TIME = "^([0-1]\\d|2[0-3])(:[0-5]\\d){2}$";

    public static boolean isValid(String s, String pattern) {
        if (s == null) return false;
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(s);
        return matcher.matches();
    }
}
