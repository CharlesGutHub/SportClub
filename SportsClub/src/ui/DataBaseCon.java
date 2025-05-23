package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseCon {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Assure le chargement du driver MySQL 8+
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sportclub?serverTimezone=UTC&useSSL=false",
                "root",
                "root"
            );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Le driver JDBC MySQL est introuvable. Ajoute mysql-connector-j à ton projet !", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base de données : " + e.getMessage(), e);
        }
    }
    public static void main(String[] args) {
        try (Connection conn = DataBaseCon.getConnection()) {
            System.out.println("Connexion réussie à la base sportclub !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
