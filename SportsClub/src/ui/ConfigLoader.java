package ui;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties = new Properties();

    static {
        try {
            InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties");
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println(" Le fichier config.properties n'a pas été trouvé !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}