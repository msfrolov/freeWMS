package com.epam.msfrolov.freewms.connection;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.SQLException;

public class CP2 {
    org.apache.tomcat.jdbc.pool.ConnectionPool connectionPool;

    public CP2() {
        try {
            PoolConfiguration poolConfiguration = new PoolProperties();
            poolConfiguration.setUsername("sa");
            poolConfiguration.setPassword("");
            poolConfiguration.setName("jdbc/freewms");
            poolConfiguration.setDriverClassName("org.h2.Driver");
            poolConfiguration.setMaxActive(20);
            poolConfiguration.setMaxIdle(60);
            poolConfiguration.setUrl("jdbc:h2:tcp://localhost/db/warehouse");
            poolConfiguration.setFairQueue(true);
            poolConfiguration.setRemoveAbandoned(true);
            poolConfiguration.setRemoveAbandonedTimeout(60);
            poolConfiguration.setMaxWait(10000);
            org.apache.tomcat.jdbc.pool.ConnectionPool connectionPool = new org.apache.tomcat.jdbc.pool.ConnectionPool(poolConfiguration);
        } catch (SQLException e) {
            //TODO something
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        return InstanceHolder.instance;
    }

    public java.sql.Connection getConnection() throws ConnectionException {
        try {
            return connectionPool.getConnection();
        } catch (SQLException e) {
            //TODO something
            e.printStackTrace();
        }
        return null;
    }

    private static class InstanceHolder {
        static final ConnectionPool instance = new ConnectionPool();
    }

}
