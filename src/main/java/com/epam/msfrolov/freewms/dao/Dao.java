package com.epam.msfrolov.freewms.dao;

import com.epam.msfrolov.freewms.model.BaseEntity;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface Dao<T extends BaseEntity> {

    static <T extends BaseEntity> Dao<T> createDaoEntity(Class<T> clazz, Connection connection) {
        return DaoFactory.newInstance(connection).createDaoEntity(clazz);
    }

    T insert(T t);

    T findById(int id);

    List<T> findAll();

    List<T> findAll(int start, int pageSize); //pagination

    List<T> findByFields(Map<String, Object> map);

    boolean update(T t);

    boolean delete(T t);
}
