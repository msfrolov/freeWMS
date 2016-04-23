package com.epam.msfrolov.freewms.service;

import com.epam.msfrolov.freewms.dao.DaoFactory;

public class Service implements AutoCloseable {
    protected DaoFactory daoFactory;

    public Service() {
        this.daoFactory = DaoFactory.newInstance();
    }

    @Override
    public void close() {
        if (daoFactory != null)
            daoFactory.close();
    }
}
