package com.epam.msfrolov.freewms.dao;

import java.util.List;
import java.util.Map;

public interface Dao<T> {

    //=)) // TODO: 08.04.2016 test
    static <T> Dao<T> createDaoEntity(Class<T> clazz) {
        return DaoFactory.newInstance().createDaoEntity(clazz);
    }

    T insert(T t) throws DaoException;

    T findById(int id) throws DaoException;

    List<T> findAll() throws DaoException;

    List<T> findAll(int start, int pageSize) throws DaoException; //pagination

    List<T> findByFields(Map<String, Object> map) throws DaoException;

    T update(T t);

    boolean delete(T t);
}
