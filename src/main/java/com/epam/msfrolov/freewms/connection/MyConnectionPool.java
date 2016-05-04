package com.epam.msfrolov.freewms.connection;


import com.epam.msfrolov.freewms.util.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyConnectionPool implements ConnectionPool {
    private static final Logger log = LoggerFactory.getLogger(MyConnectionPool.class);
    private static final int DEFAULT_CONNECTIONS_NUMBER = 10;
    private static final Long DEFAULT_WAIT_TIME_IN_SECONDS = 0L;
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final Boolean FAIR_SYNC;
    private final Integer CONNECTIONS_NUMBER;
    private final Long WAIT_TIME_IN_SECONDS;
    private AtomicInteger numberOfConnections;
    private AtomicInteger numberOfFree;
    private AtomicInteger numberOfBusy;
    private BlockingQueue<PooledConnection> freeConnections;
    private BlockingQueue<PooledConnection> busyConnections;


    private MyConnectionPool() {

        Properties pr = FileManager.getProperties("properties/connection.properties");
        URL = getParameter(pr, "url");
        USER = getParameter(pr, "user");
        PASSWORD = getParameter(pr, "password");
        FAIR_SYNC = getParameter(pr, "fair_sync");
        log.debug(FAIR_SYNC.toString());
        CONNECTIONS_NUMBER = getParameter(pr, "number_connections");
        WAIT_TIME_IN_SECONDS = getParameter(pr, "wait_time_in_seconds");
        freeConnections = new ArrayBlockingQueue<>(CONNECTIONS_NUMBER, FAIR_SYNC);
        busyConnections = new ArrayBlockingQueue<>(CONNECTIONS_NUMBER, FAIR_SYNC);
        numberOfConnections = new AtomicInteger();
        numberOfFree = new AtomicInteger();
        numberOfBusy = new AtomicInteger();
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Class h2 init");
        }
        for (int i = 0; i < CONNECTIONS_NUMBER; i++) {
            try {
                addConnection();
                numberOfConnections.incrementAndGet();
                numberOfFree.incrementAndGet();
            } catch (ConnectionPoolException e) {
                throw new ConnectionPoolException("failed to add new connection in pool", e);
            }
        }
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        return InstanceHolder.pool;
    }

    @Override
    public int getNumberConnections() {
        return numberOfConnections.get();
    }

    @Override
    public int getNumberFreeConnections() {
        return numberOfFree.get();

    }

    @Override
    public int getNumberBusyConnections() {
        return numberOfBusy.get();
    }

    private void addConnection() throws ConnectionPoolException {
        Connection connection = createConnection();
        freeConnections.offer(new PooledConnection(connection));
    }

    @Override
    public Connection getConnection() throws ConnectionPoolException {
        try {
            PooledConnection connection = freeConnections.poll(WAIT_TIME_IN_SECONDS, TimeUnit.SECONDS);
            try {
                if (connection == null || connection.isClosed()) throw new SQLException();
            } catch (SQLException e) {
                connection = new PooledConnection(createConnection());
            }
            numberOfFree.decrementAndGet();
            busyConnections.offer(connection);
            numberOfBusy.incrementAndGet();
            return connection;
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("waiting interrupted", e);
        }
    }

    private Connection createConnection() throws ConnectionPoolException {
        try {
            Connection connection;
            if (USER == null)
                connection = DriverManager.getConnection(URL);
            else
                log.info(URL);
            log.info(USER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new ConnectionPoolException("Connection pool:  cannot create connection", e);
        }

    }

    @SuppressWarnings("unchecked")
    private <T> T getParameter(Properties pr, String key) {
        switch (key) {
            //driver settings
            case "url":
            case "user":
            case "password":
                return (T) pr.getProperty(key);
            case "fair_sync":
                String fairSyncProperty = pr.getProperty("fair_sync");
                return (T) (Boolean) (fairSyncProperty != null || Boolean.parseBoolean(fairSyncProperty)); //default value FAIR_SYNC: false;
            case "number_connections":
                Integer numberConValue;
                try {
                    numberConValue = Integer.valueOf(pr.getProperty("number_connections"));
                    if (numberConValue <= 0)
                        numberConValue = DEFAULT_CONNECTIONS_NUMBER; //can not be set a zero or negative value; set default value
                } catch (Exception e) {
                    numberConValue = DEFAULT_CONNECTIONS_NUMBER; //set default value number_connections;
                }
                return (T) numberConValue;
            case "wait_time_in_seconds":
                Long waitTimeValue = -1L;
                try {
                    waitTimeValue = Long.valueOf(pr.getProperty("wait_time_in_seconds"));
                } catch (Exception e) {
                    throw new ConnectionPoolException("failed to get the parameter: wait time in seconds", e);
                }
                if (waitTimeValue < 0)
                    waitTimeValue = DEFAULT_WAIT_TIME_IN_SECONDS;
                return (T) waitTimeValue;
            default:
                throw new ConnectionPoolException("transferred parameter is not envisaged!");
        }
    }

    @Override
    public void close() {
        freeConnections.forEach(c -> {
            try {
                if (c != null)
                    if (!c.isClosed())
                        c.disconnect();
            } catch (SQLException e) {
                throw new ConnectionPoolException("failed to disconnect connection", e);
            }
        });
        busyConnections.forEach(c -> {
            try {
                if (c != null)
                    if (!c.isClosed())
                        c.disconnect();
            } catch (SQLException e) {
                throw new ConnectionPoolException("failed to disconnect connection ", e);
            }
        });
    }

    private static class InstanceHolder {
        static final MyConnectionPool pool = new MyConnectionPool();
    }

    private class PooledConnection implements Connection {
        Connection connection;

        public PooledConnection(Connection connection) {
            this.connection = connection;
        }

        void disconnect() throws SQLException {
            if (!this.isClosed()) {
                if (!connection.getAutoCommit())
                    connection.setAutoCommit(true);
                connection.close();
            }
        }

        @Override
        public void close() throws SQLException {
            this.setAutoCommit(true);
            busyConnections.remove(this);
            numberOfBusy.decrementAndGet();
            freeConnections.offer(this);
            numberOfFree.incrementAndGet();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String s) throws SQLException {
            return connection.prepareStatement(s);
        }

        @Override
        public CallableStatement prepareCall(String s) throws SQLException {
            return connection.prepareCall(s);
        }

        @Override
        public String nativeSQL(String s) throws SQLException {
            return connection.nativeSQL(s);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public void setAutoCommit(boolean b) throws SQLException {
            connection.setAutoCommit(b);
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public void setReadOnly(boolean b) throws SQLException {
            connection.setReadOnly(b);
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public void setCatalog(String s) throws SQLException {
            connection.setCatalog(s);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public void setTransactionIsolation(int i) throws SQLException {
            connection.setTransactionIsolation(i);
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public Statement createStatement(int i, int i1) throws SQLException {
            return connection.createStatement(i, i1);
        }

        @Override
        public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
            return connection.prepareStatement(s, i, i1);
        }

        @Override
        public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
            return connection.prepareCall(s, i, i1);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public void setHoldability(int i) throws SQLException {
            connection.setHoldability(i);
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String s) throws SQLException {
            return connection.setSavepoint(s);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int i, int i1, int i2) throws SQLException {
            return connection.createStatement(i, i1, i2);
        }

        @Override
        public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
            return connection.prepareStatement(s, i, i1, i2);
        }

        @Override
        public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
            return connection.prepareCall(s, i, i1, i2);
        }

        @Override
        public PreparedStatement prepareStatement(String s, int i) throws SQLException {
            return connection.prepareStatement(s, i);
        }

        @Override
        public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
            return connection.prepareStatement(s, ints);
        }

        @Override
        public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
            return connection.prepareStatement(s, strings);
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public boolean isValid(int i) throws SQLException {
            return connection.isValid(i);
        }

        @Override
        public void setClientInfo(String s, String s1) throws SQLClientInfoException {
            connection.setClientInfo(s, s1);
        }

        @Override
        public String getClientInfo(String s) throws SQLException {
            return connection.getClientInfo(s);
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        @Override
        public Array createArrayOf(String s, Object[] objects) throws SQLException {
            return connection.createArrayOf(s, objects);
        }

        @Override
        public Struct createStruct(String s, Object[] objects) throws SQLException {
            return connection.createStruct(s, objects);
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void setSchema(String s) throws SQLException {
            connection.setSchema(s);
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int i) throws SQLException {
            connection.setNetworkTimeout(executor, i);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> aClass) throws SQLException {
            return connection.unwrap(aClass);
        }

        @Override
        public boolean isWrapperFor(Class<?> aClass) throws SQLException {
            return connection.isWrapperFor(aClass);
        }
    }
}
