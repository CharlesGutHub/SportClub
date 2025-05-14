package models;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader()
                .getResourceAsStream("database.properties")) {

            if (input == null) {
                throw new RuntimeException("Fichier database.properties introuvable");
            }
            properties.load(input);

            // Chargement explicite du driver JDBC
            Class.forName(properties.getProperty("db.driver"));

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erreur de configuration de la base de données", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            properties.getProperty("db.url"),
            properties.getProperty("db.username"),
            properties.getProperty("db.password")
        );
    }

    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDbUsername() {
        return properties.getProperty("db.username");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }
}
