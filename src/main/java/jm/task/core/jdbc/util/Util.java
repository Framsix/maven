package jm.task.core.jdbc.util;

import java.sql.*;
import java.sql.Driver;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/usersdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
