package com.epam.msfrolov.freewms.util;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.dao.DaoFactory;
import com.epam.msfrolov.freewms.model.User;

public class Runner {

    public static void main(String[] args) {
        DaoFactory daoFactory = DaoFactory.newInstance();
        //Dao<User> userDao = daoFactory.createDao(User.class);
        System.out.println(daoFactory);
//        ConnectionPool pool = new ConnectionPool();
//        try (Connection connection = pool.getConnection()){
//
//            Statement statement = connection.createStatement();
//            statement.execute("SELECT * FROM USER_ROLE");
//            ResultSet resultSet = statement.getResultSet();
//            resultSet.next();
//            String anInt = resultSet.getString(2);
//            System.out.println(anInt);
//        } catch (ConnectionException e) {
//            //TODO something
//            e.printStackTrace();
//        } catch (SQLException e) {
//            //TODO something
//            e.printStackTrace();
//        }
    }
}
