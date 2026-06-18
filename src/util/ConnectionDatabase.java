package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    private static final String USER_NAME ="postgres";
    private static final String PASSWORD ="12345";
    private static final String URL ="jdbc:postgresql://localhost:5432/session17_jdbc";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
           e.printStackTrace();
        }

        return null;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {}
    }
}
