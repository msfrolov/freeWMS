package com.epam.msfrolov.freewms.connection;

import com.epam.msfrolov.freewms.util.AppException;
import com.epam.msfrolov.freewms.util.FileManager;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private final String PROPERTY_NAME = "properties/connection.properties";
    private final String URL;
    private final String USER;
    private final String PASSWORD;

    public ConnectionPool() {
        try {
            URL = FileManager.getProperties("properties/connection.properties").getProperty("url");
            USER = FileManager.getProperties("properties/connection.properties").getProperty("user");
            PASSWORD = FileManager.getProperties("properties/connection.properties").getProperty("PASSWORD");
            String driverName = FileManager.getProperties("properties/connection.properties").getProperty("driverName");
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            throw new AppException("ClassNotFoundException: failed to initialize the driver class", e);
        }
    }

    public java.sql.Connection getConnection() throws ConnectionException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new ConnectionException("SQLException: failed to get a connection", e);
        }
    }
}
