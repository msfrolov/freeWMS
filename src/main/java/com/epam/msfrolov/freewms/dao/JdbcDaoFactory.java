package com.epam.msfrolov.freewms.dao;

import com.epam.msfrolov.freewms.connection.MyConnectionPool;
import com.epam.msfrolov.freewms.model.BaseEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory implements DaoFactory {

    private final Connection connection;

    public JdbcDaoFactory() {
        this.connection = MyConnectionPool.getInstance().getConnection();
    }

    @Override
    public <T extends BaseEntity> Dao<T> createDaoEntity(Class<T> clazz) {
        return new JdbcDao<>(clazz, connection, this);
    }

    @Override
    public void startTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("failed to start transaction", e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("failed to commit transaction", e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("failed to rollback changes", e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("failed to close DaoFactory", e);
        }
    }

}
