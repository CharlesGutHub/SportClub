package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseCon {
    private static final String url = ConfigLoader.getProperty("db.url");
    private static final String user = ConfigLoader.getProperty("db.user");
    private static final String password = ConfigLoader.getProperty("db.password");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Charger driver MySQL
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver MySQL introuvable : " + ex.getMessage(), ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
