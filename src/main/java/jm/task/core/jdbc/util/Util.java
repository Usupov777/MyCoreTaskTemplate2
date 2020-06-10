package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }
}
