package com.epam.msfrolov.freewms.util;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommonTest {



    @Test
    public void testCheckNotNull1111() throws Exception {
        int a = 1000000000;
        int b = 999999999;
        int x = (int) Math.ceil((double)a / b);
        assertEquals(2 , x);

    }

    @Test
    public void testCheckNotNull() throws Exception {

    }

    @Test
    public void testCheckExtendsBaseEntity() throws Exception {

    }

    @Test
    public void testIsEntity() throws Exception {

    }

    @Test
    public void testFirstUpperCase() throws Exception {

    }

    @Test
    public void testCamelCaseToUpperCase() throws Exception {

    }

    @Test
    public void testUpperCaseToCamelCase() throws Exception {

    }
}