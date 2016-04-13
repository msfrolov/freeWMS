package com.epam.msfrolov.freewms.dao;

import com.epam.msfrolov.freewms.model.BaseEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory implements DaoFactory {

    private final Connection connection;

    public JdbcDaoFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T extends BaseEntity> Dao<T> createDaoEntity(Class<T> clazz) {
        return new JdbcEntityDao<>(clazz, connection);
    }

    @Override
    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("failed to start transaction", e);
        }
    }

    @Override
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("failed to commit transaction", e);
        }
    }

    @Override
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("failed to rollback changes", e);
        }
    }

    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("failed to close connection", e);
        }
    }

}
