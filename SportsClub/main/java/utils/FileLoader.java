package utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileLoader {

    /**
     * Charge un fichier texte (ex: CSV) depuis le classpath ou webapp/
     * @param filePath Chemin relatif dans le projet (ex: "data/lic-data-2022.csv")
     * @return BufferedReader prêt à lire le fichier
     * @throws RuntimeException si le fichier est introuvable
     */
    public static BufferedReader loadFile(String filePath) {
        try {
            // Cherche le fichier dans le dossier "webapp/"
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

            if (inputStream == null) {
                throw new RuntimeException("Fichier non trouvé dans les ressources : " + filePath);
            }

            return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chargement du fichier : " + filePath, e);
        }
    }
}
