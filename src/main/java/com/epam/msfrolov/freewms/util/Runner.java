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

//        ConnectionPool pool = new ConnectionPool();
//
//        Dao<Individual> individualDao = DaoFactory.newInstance().createDaoEntity(Individual.class);
//        Individual byId = individualDao.findById(1);
//        List<Individual> all = individualDao.findAll();
//        List<Individual> all1 = individualDao.findAll(2, 1);
//        Map<String, Object> hashMap = new HashMap<>();
//        hashMap.put("id", 1);
//        List<Individual> byFields = individualDao.findByFields(hashMap);
//
//        System.out.println("byId " + byId);
//        System.out.println("all" + all);
//        System.out.println("all1" + all1);
//        System.out.println("byFields" + byFields);
//
//
//        Dao<User> userDao = DaoFactory.newInstance().createDaoEntity(User.class);
//        User user = new User();
//        user.setName("newUser");
//        user.setPassword("18734");
//        user.setIndividual(byId);
//        UserRole userRole = new UserRole();
//        userRole.setId(4);
//        userRole.setName("USER");
//        user.setRole(userRole);
//        User insert = null;
//        try {
//            insert = userDao.insert(user);
//
//            Individual individual = new Individual();
//            individual.setName("Анна");
//            Dao<Gender> genderDao = DaoFactory.newInstance().createDaoEntity(Gender.class);
//            HashMap<String, Object> objectObjectHashMap = new HashMap<>();
//            objectObjectHashMap.put("name","FEMALE");
//            List<Gender> byFields1 = genderDao.findByFields(objectObjectHashMap);
//            individual.setGender(byFields1.get(0));
//            Dao<Individual> individualDao1 = DaoFactory.newInstance().createDaoEntity(Individual.class);
//            individual = individualDao.insert(individual);
//            System.out.println("!!individual " + individual);
//            user.setName("Abra Кадабра");
//            user.setPassword("123456789");
//            user.setIndividual(individual);
//            userDao.update(user);
//            userDao.delete(user);
//        } catch (DaoException e) {
//            e.printStackTrace();
//            System.out.println("-----------------------------?????" + insert);
//        }

    }
}
