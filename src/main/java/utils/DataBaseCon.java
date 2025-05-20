package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DataBaseCon {
    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        try (InputStream input = DataBaseCon.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new Exception("Fichier database.properties introuvable.");
            }
            props.load(input);
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
