package com.epam.msfrolov.freewms.service;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.dao.DaoException;
import com.epam.msfrolov.freewms.dao.DaoFactory;
import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private Dao<User> userDao;
    private DaoFactory daoFactory;
    private UserRole defaultRole = UserRole.USER;

    public UserService() {
        log.debug("UserService() constructor");
        daoFactory = DaoFactory.newInstance();
        log.debug("UserService() constructor: DaoFactory.newInstance()");
        userDao = daoFactory.createDaoEntity(User.class);
        log.debug("UserService() constructor: daoFactory.createDaoEntity(User.class);");
    }

    public User signIn(User user) {
        Map<String, Object> userFieldMap = new HashMap<>();
        userFieldMap.put("name", user.getName());
        userFieldMap.put("password", user.getPassword());
        List<User> userList = userDao.findByFields(userFieldMap);
        log.debug("signIn - findByFields: {}", userList);
        if (userList.isEmpty()) return null;
        else return userList.get(0);
    }

    public User signUp(User user) {
        try {
            return userDao.insert(user);
        } catch (DaoException e) {
            return null;
        }
    }

    @Override
    public void close() {
        if (daoFactory != null)
            daoFactory.close();
    }
}
