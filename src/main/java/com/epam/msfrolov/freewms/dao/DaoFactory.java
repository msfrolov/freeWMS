package com.epam.msfrolov.freewms.dao;

import com.epam.msfrolov.freewms.model.BaseEntity;

import java.sql.Connection;

public interface DaoFactory extends AutoCloseable {
    static DaoFactory newInstance(Connection connection) {
        return new JdbcDaoFactory(connection);
    }

    <T extends BaseEntity> Dao<T> createDaoEntity(Class<T> clazz);

    void startTransaction() throws DaoException;

    void commit() throws DaoException;

    void rollback() throws DaoException;

    @Override
    void close() throws Exception;
}
