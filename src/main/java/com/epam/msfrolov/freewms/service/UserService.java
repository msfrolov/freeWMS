package com.epam.msfrolov.freewms.service;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.dao.DaoException;
import com.epam.msfrolov.freewms.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService extends Service {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private Dao<User> userDao;
    private Dao<UserRole> roleDao;
    private Dao<Gender> genderDao;
    private Dao<Individual> individualDao;

    public UserService() {
        log.debug("UserService() constructor: DaoFactory.newInstance()");
        userDao = daoFactory.createDaoEntity(User.class);
        log.debug("UserService() constructor: daoFactory.createDaoEntity(User.class);");
        roleDao = daoFactory.createDaoEntity(UserRole.class);
        log.debug("UserService() constructor: daoFactory.createDaoEntity(UserRole.class);");
        genderDao = daoFactory.createDaoEntity(Gender.class);
        log.debug("UserService() constructor: daoFactory.createDaoEntity(Gender.class);");
        individualDao = daoFactory.createDaoEntity(Individual.class);
        log.debug("UserService() constructor: daoFactory.createDaoEntity(Individual.class);");

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

    public boolean updateUser(User user) {
        try {
            return userDao.update(user);
        } catch (DaoException e) {
            return false;
        }
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public UserRole findUserRoleById(int id) {
        return roleDao.findById(id);
    }

    public List<UserRole> getAllRoles() {
        return roleDao.findAll();
    }

    public List<Gender> getGender() {
        return genderDao.findAll();
    }

    public Individual insertIndivid(Individual individ) {
        return individualDao.insert(individ);
    }

    public Gender findGender(int i) {
        return genderDao.findById(i);
    }

    public boolean updateUserAndIndivid(User user, HttpServletRequest req, Map<String, String> violation) {
        daoFactory.startTransaction();
        try {
            boolean updateIndivid = individualDao.update(user.getIndividual());
            if (!updateIndivid) throw new ServiceException("individual is not updated");
            boolean updateUser = userDao.update(user);
            if (!updateUser) throw new ServiceException("user is not updated");
            User userSes = (User) req.getSession(false).getAttribute("user");
            if (userSes.equals(user))
                req.getSession(false).setAttribute("user", user);
            daoFactory.commit();
            return true;
        } catch (Exception e) {
            daoFactory.rollback();
            violation.put("transaction", "failed to hold a transaction");
            log.debug("failed to hold a transaction", e);
            return false;
        }
    }

    @Override
    public void close() {
        super.close();
    }

    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    public List<User> getUsersForPage(int pageNumber, int pageSize) {
        int start = pageSize * (pageNumber - 1) + 1;
        log.debug("pageNumber  {}", pageNumber);
        log.debug("pageSize  {}", pageSize);
        log.debug("start  {}", start);
        List<User> users = userDao.findAll(start, pageSize);
        log.debug("number of users in the list: {}", users.size());
        return users;
    }
}
