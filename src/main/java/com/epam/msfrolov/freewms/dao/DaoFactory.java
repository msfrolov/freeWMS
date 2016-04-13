package com.epam.msfrolov.freewms.dao;

import com.epam.msfrolov.freewms.model.BaseEntity;
import com.epam.msfrolov.freewms.util.FileManager;

import java.util.Properties;

public interface DaoFactory extends AutoCloseable {
    static DaoFactory newInstance() {
        Properties properties = FileManager.getProperties("properties/dao.properties");
        String daoFactory = properties.getProperty("daoFactory");
        if ("JdbcDaoFactory".equalsIgnoreCase(daoFactory)) {
            return new JdbcDaoFactory();
        } else throw new DaoException("Do not set dao factory");
    }

    <T extends BaseEntity> Dao<T> createDaoEntity(Class<T> clazz);

    void startTransaction();

    void commit();

    void rollback();

    @Override
    void close();
}
