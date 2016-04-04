package com.epam.msfrolov.freewms.dao;

import com.epam.msfrolov.freewms.util.AppException;
import com.epam.msfrolov.freewms.util.FileManager;

public interface DaoFactory {
    static <T extends DaoFactory> T newInstance() {
        String daoFactoryName = FileManager.getProperties("properties/dao.properties").getProperty("daoFactoryName");
        try {
            //noinspection unchecked
            return (T) Class.forName(daoFactoryName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new AppException("failed to initialize DAO factory", e);
        }
    }


    <T> Dao createDao(Class<T> userClass);
}
