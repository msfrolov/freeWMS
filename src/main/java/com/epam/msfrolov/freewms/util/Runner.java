package com.epam.msfrolov.freewms.util;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.dao.DaoException;
import com.epam.msfrolov.freewms.dao.DaoFactory;
import com.epam.msfrolov.freewms.model.BaseEntity;
import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.model.UserRole;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Runner {

    public static void main(String[] args) {
//        LocalDate s = LocalDate.parse("2017-12-17");
//        System.out.println(s);
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
//        System.out.println(s.format(formatter));
//        DaoFactory daoFactory = DaoFactory.newInstance();
//        Dao<User> userDao = daoFactory.createDaoEntity(User.class);
//        User user = new User();
//        user.setName("newUser");
//        user.setPassword("18734");
//        UserRole userRole = new UserRole();
//        userRole.setId(4);
//        userRole.setName("USER");
//        user.setRole(userRole);
//        User insert = null;
//        try {
//            insert = userDao.insert(user);
//        } catch (DaoException e) {
//            System.out.println("?????" + insert);
//        }

        Class<BaseEntity> baseEntityClass = BaseEntity.class;

        System.out.println();
         Class Classf = ddd(BaseEntity.class);
//
//        Field requisite = ReflectUtil.getField("requisite", Counterpart.class);
////        System.out.println(requisite.getName());
////        System.out.println(requisite.getType());
////        System.out.println(requisite.getName());
//        Method responsiblePerson = ReflectUtil.getGetter("id", Counterpart.class);
//        System.out.println(responsiblePerson.getReturnType());
//        System.out.println(ReflectUtil.getGenericType(requisite));
//        System.out.println(ReflectUtil.presenceField("requisite", Counterpart.class));
    }


    static <T> T ddd(Class<BaseEntity> clazz){
        Class<? extends User> aClass = new User().getClass();

        System.out.println(aClass == clazz);
        return null;
    }
}
