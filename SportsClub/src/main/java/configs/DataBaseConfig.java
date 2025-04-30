package configs;



import java.io.InputStream;
import java.util.Properties;

public class DataBaseConfig {
    private static final Properties props = new Properties();
    
    static {
        loadConfig();
    }
    
    private static void loadConfig() {
        try (InputStream input = DataBaseConfig.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            
            if (input == null) {
                throw new RuntimeException("Fichier database.properties introuvable");
            }
            
            props.load(input);
            
            // Chargement du driver JDBC
            Class.forName(props.getProperty("db.driver"));
            
        } catch (Exception e) {
            throw new RuntimeException("Erreur de configuration", e);
        }
    }
    
    public static String getUrl() {
        return props.getProperty("db.url");
    }
    
    public static String getUsername() {
        return props.getProperty("db.username");
    }
    
    public static String getPassword() {
        return props.getProperty("db.password");
    }
}