package com.epam.msfrolov.freewms.connection;

import com.epam.msfrolov.freewms.util.AppException;
import com.epam.msfrolov.freewms.util.FileManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

public class CP {
    private final String PROPERTY_NAME = "properties/connection.properties";
    private final String URL;
    DataSource ds;

    public CP() {
        try {
            URL = FileManager.getProperties(PROPERTY_NAME).getProperty("cp_url");
            InitialContext context = new InitialContext();
            ds = (DataSource) context.lookup(URL);
        } catch (NamingException e) {
            throw new AppException("", e);
        }
    }

    public java.sql.Connection getConnection() throws ConnectionException {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new ConnectionException("SQLException: failed to get a connection", e);
        }
    }
}
