package com.epam.msfrolov.freewms.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    @Test
    public void testValid() throws Exception {
        assertTrue(Validator.isValid("23:59:59", Validator.TIME));
        assertTrue(Validator.isValid("00:00:00", Validator.TIME));
        assertTrue(Validator.isValid("1990-12-21", Validator.DATE));
        assertTrue(Validator.isValid("Maxim17Frolov", Validator.LETTERS_DIGITS));
        assertTrue(Validator.isValid("Просто текст 8", Validator.LETTERS_DIGITS_WS));
        assertTrue(Validator.isValid("Maxim Frolov", Validator.LETTERS_DIGITS_WS_MIN5_MAX32));
        assertTrue(Validator.isValid("Максим", Validator.LETTERS_DIGITS_WS_MIN5_MAX32));
        assertTrue(Validator.isValid("123456789012345678901234567890123", Validator.DIGITS));
        /////
        assertTrue(!Validator.isValid("23:59:60", Validator.TIME));
        assertTrue(!Validator.isValid("00/00/00", Validator.TIME));
        assertTrue(!Validator.isValid("1990/12/21", Validator.DATE));
        assertTrue(!Validator.isValid("1990-12-211", Validator.DATE));
        assertTrue(!Validator.isValid("Maxim Frolov", Validator.LETTERS_DIGITS));
        assertTrue(!Validator.isValid(null, Validator.DIGITS));
        assertTrue(!Validator.isValid("123ю", Validator.DIGITS));
        assertTrue(!Validator.isValid("12 3", Validator.DIGITS));
        assertTrue(!Validator.isValid(" 123", Validator.DIGITS));
        assertTrue(!Validator.isValid("Макс1", Validator.LETTERS));
        assertTrue(!Validator.isValid("7Макс", Validator.LETTERS));
        assertTrue(!Validator.isValid("Ма5кс", Validator.LETTERS));
        assertTrue(!Validator.isValid("Maxim Frolov", Validator.LETTERS));
        assertTrue(!Validator.isValid("Макс", Validator.LETTERS_DIGITS_WS_MIN5_MAX32));
        assertTrue(!Validator.isValid("123456789012345678901234567890123", Validator.LETTERS_DIGITS_WS_MIN5_MAX32));
    }
}