package com.epam.msfrolov.freewms.service;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.dao.DaoFactory;
import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.model.UserRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService implements AutoCloseable {
    private Dao<User> userDao;
    private DaoFactory daoFactory;

    public UserService() {
        daoFactory = DaoFactory.newInstance();
        userDao = daoFactory.createDaoEntity(User.class);
    }

    public User signIn(User user) {
        Map<String, Object> userFieldMap = new HashMap<>();
        userFieldMap.put("name", user.getName());
        userFieldMap.put("password", user.getPassword());
        List<User> userList = userDao.findByFields(userFieldMap);
        if (userList.isEmpty()) return null;
        else return userList.get(0);
    }

    public User signUp(User user) {
        Map<String, Object> userFieldMap = new HashMap<>();
        userFieldMap.put("name", user.getName());
        List<User> userList = userDao.findByFields(userFieldMap);
        user.setRole(UserRole.GUEST);
        if (userList.isEmpty()) return userDao.insert(user);
        else return null;
    }

    @Override
    public void close() {
        if (daoFactory != null)
            daoFactory.close();
    }
}
