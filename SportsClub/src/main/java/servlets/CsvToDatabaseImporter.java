package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class CsvToDatabaseImporter {

    public static void main(String[] args) {
        Properties config = loadConfig();

        String cheminFichier = config.getProperty("csv.path");
        String jdbcURL = config.getProperty("db.url");
        String username = config.getProperty("db.username");
        String password = config.getProperty("db.password");

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = buildInsertQuery();

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {

                br.readLine(); // Ignorer l'en-tête
                int batchSize = 0;
                String line;

                while ((line = br.readLine()) != null) {
                    String[] data = cleanAndSplitLine(line);
                    if (data != null) {
                        setStatementParameters(statement, data);
                        statement.addBatch();

                        if (++batchSize % 1000 == 0) {
                            statement.executeBatch();
                        }
                    }
                }

                statement.executeBatch(); // Final commit
                System.out.println("Import terminé avec succès ! Lignes traitées : " + batchSize);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String buildInsertQuery() {
        return "INSERT INTO population (" +
                "code_comm, commune, code_qpv, nom_qpv, departement_region, region, statut_geo, code_federation, federation, " +
                "f_1_4_ans, f_5_9_ans, f_10_14_ans, f_15_19_ans, f_20_24_ans, " +
                "f_25_29_ans, f_30_34_ans, f_35_39_ans, f_40_44_ans, f_45_49_ans, f_50_54_ans, f_55_59_ans, f_60_64_ans, f_65_69_ans, f_70_74_ans, f_75_79_ans, f_80_99_ans, f_nr, " +
                "h_1_4_ans, h_5_9_ans, h_10_14_ans, h_15_19_ans, h_20_24_ans, " +
                "h_25_29_ans, h_30_34_ans, h_35_39_ans, h_40_44_ans, h_45_49_ans, " +
                "h_50_54_ans, h_55_59_ans, h_60_64_ans, h_65_69_ans, h_70_74_ans, " +
                "h_75_79_ans, h_80_99_ans, h_nr, Total) " +
                "VALUES (" + "?,".repeat(45) + "?" + ")";
    }

    private static String[] cleanAndSplitLine(String line) {
        if (line == null || line.trim().isEmpty()) return null;

        String[] data = line.split(";", -1); // Inclure champs vides

        if (data.length != 46) {
            System.err.println("Erreur : ligne mal formatée, contient " + data.length + " colonnes au lieu de 46 : " + line);
            return null;
        }

        // Supprimer les guillemets de toutes les colonnes sauf la 4e (index 3)
        for (int i = 0; i < data.length; i++) {
            if (i != 3) {
                data[i] = data[i].replace("\"", "");
            }
        }

        return data;
    }

    private static void setStatementParameters(PreparedStatement statement, String[] data) throws SQLException {
        if (data == null || data.length != 46) {
            throw new SQLException("Mauvais format de données : attendues 46 colonnes.");
        }

        // Champs texte : colonnes 0 à 8
        for (int i = 0; i < 9; i++) {
            statement.setString(i + 1, cleanField(data, i));
        }

        // Champs numériques : colonnes 9 à 45
        for (int i = 9; i < 46; i++) {
            statement.setInt(i + 1, parseInt(cleanField(data, i)));
        }
    }

    private static String cleanField(String[] data, int index) {
        if (index >= data.length) return "";
        return data[index].trim();
    }

    private static int parseInt(String value) {
        try {
            return Integer.parseInt(value.replaceAll("[^0-9-]", ""));
        } catch (NumberFormatException e) {
            System.err.println("Valeur non numérique détectée : '" + value + "', remplacée par 0");
            return 0;
        }
    }

    private static Properties loadConfig() {
        Properties prop = new Properties();
        try (InputStream input = CsvToDatabaseImporter.class.getClassLoader()
                .getResourceAsStream("database.properties")) {

            if (input == null) {
                throw new RuntimeException("Fichier database.properties non trouvé");
            }
            prop.load(input);
            return prop;

        } catch (IOException e) {
            throw new RuntimeException("Erreur de lecture du fichier de configuration", e);
        }
    }
}
