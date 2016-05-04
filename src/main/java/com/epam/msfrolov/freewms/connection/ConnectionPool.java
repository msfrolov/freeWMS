package com.epam.msfrolov.freewms.connection;

import java.sql.Connection;

public interface ConnectionPool extends AutoCloseable {
    Connection getConnection() throws ConnectionPoolException;

    @Override
    void close();

    int getNumberConnections();

    int getNumberFreeConnections();

    int getNumberBusyConnections();

}
