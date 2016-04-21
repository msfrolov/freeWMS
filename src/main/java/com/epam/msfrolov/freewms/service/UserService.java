package com.epam.msfrolov.freewms.service;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.dao.DaoException;
import com.epam.msfrolov.freewms.dao.DaoFactory;
import com.epam.msfrolov.freewms.model.Gender;
import com.epam.msfrolov.freewms.model.Individual;
import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private DaoFactory daoFactory;
    private Dao<User> userDao;
    private Dao<UserRole> roleDao;
    private Dao<Gender> genderDao;
    private Dao<Individual> individualDao;

    public UserService() {
        log.debug("UserService() constructor");
        daoFactory = DaoFactory.newInstance();
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

    @Override
    public void close() {
        if (daoFactory != null)
            daoFactory.close();
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
}
