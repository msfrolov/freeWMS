package com.epam.msfrolov.freewms.util;

import com.epam.msfrolov.freewms.connection.ConnectionPool;
import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.dao.DaoException;
import com.epam.msfrolov.freewms.dao.DaoFactory;
import com.epam.msfrolov.freewms.model.Gender;
import com.epam.msfrolov.freewms.model.Individual;
import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.model.UserRole;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {

        System.out.println(Runner.class.getPackage().getName());
        System.out.println(Runner.class.getClassLoader());
        System.out.println(Runner.class.getCanonicalName());
    }
}
