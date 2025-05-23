package ui;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.sql.*;

public class LoggerService {
    private static final String LOG_FILE = "logs.txt";  // Le fichier de sortie

    // Log dans un fichier local
    public static void log(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now().toString();
            writer.println("[" + timestamp + "] " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Log en base de données (avec IP locale et IP publique)
    public static void logToDatabase(String ipLocale, String ipPublique, String email, String action) {
        try (Connection conn = DataBaseCon.getConnection()) {
            String sql = "INSERT INTO logs (ip, ip_publique, email, action) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ipLocale);
            stmt.setString(2, ipPublique);
            stmt.setString(3, email);
            stmt.setString(4, action);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Récupère l'adresse IP publique (via un service externe)
    public static String getPublicIP() {
        try {
            java.net.URL url = new java.net.URL("https://api.ipify.org");
            java.io.BufferedReader in = new java.io.BufferedReader(
                new java.io.InputStreamReader(url.openStream())
            );
            String publicIP = in.readLine();
            in.close();
            return publicIP;
        } catch (Exception e) {
            return "inconnue";
        }
    }
    public static void logAdminAction(String adminEmail, String action, String cible, String details) {
        try (Connection conn = DataBaseCon.getConnection()) {
            String sql = "INSERT INTO audit_admin (admin_email, action, cible, details) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, adminEmail);
            stmt.setString(2, action);
            stmt.setString(3, cible);
            stmt.setString(4, details);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

