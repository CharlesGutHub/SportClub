package bddInitializer;

import ui.DataBaseCon;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS licences ( id INT AUTO_INCREMENT PRIMARY KEY,\r\n"
        		+ "    code_commune VARCHAR(5),\r\n"
        		+ "    libelle VARCHAR(45),\r\n"
        		+ "    region VARCHAR(38),\r\n"
        		+ "    fed_2022 VARCHAR(3),\r\n"
        		+ "    nom_fed VARCHAR(113),\r\n"
        		+ "    l_2022 INT,\r\n"
        		+ "    l_0_4_2022 INT,\r\n"
        		+ "    l_5_9_2022 INT,\r\n"
        		+ "    l_10_14_2022 INT,\r\n"
        		+ "    l_15_19_2022 INT,\r\n"
        		+ "    l_20_29_2022 INT,\r\n"
        		+ "    l_30_44_2022 INT,\r\n"
        		+ "    l_45_59_2022 INT,\r\n"
        		+ "    l_60_74_2022 INT,\r\n"
        		+ "    l_75_2022 INT,\r\n"
        		+ "    l_f_2022 INT,\r\n"
        		+ "    l_0_4_f_2022 INT,\r\n"
        		+ "    l_5_9_f_2022 INT,\r\n"
        		+ "    l_10_14_f_2022 INT,\r\n"
        		+ "    l_15_19_f_2022 INT,\r\n"
        		+ "    l_20_29_f_2022 INT,\r\n"
        		+ "    l_30_44_f_2022 INT,\r\n"
        		+ "    l_45_59_f_2022 INT,\r\n"
        		+ "    l_60_74_f_2022 INT,\r\n"
        		+ "    l_75_f_2022 INT,\r\n"
        		+ "    l_h_2022 INT,\r\n"
        		+ "    l_0_4_h_2022 INT,\r\n"
        		+ "    l_5_9_h_2022 INT,\r\n"
        		+ "    l_10_14_h_2022 INT,\r\n"
        		+ "    l_15_19_h_2022 INT,\r\n"
        		+ "    l_20_29_h_2022 INT,\r\n"
        		+ "    l_30_44_h_2022 INT,\r\n"
        		+ "    l_45_59_h_2022 INT,\r\n"
        		+ "    l_60_74_h_2022 INT,\r\n"
        		+ "    l_75_h_2022 INT,\r\n"
        		+ "    l_qp_2022 INT,\r\n"
        		+ "    l_qp_f_2022 INT,\r\n"
        		+ "    l_qp_h_2022 INT\r\n"
        		+ ");"; // ton vrai SQL ici

        try (Connection conn = DataBaseCon.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table 'licences' créée avec succès.");
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de la table licences", e);
        }
    }

    public static void createTableClub() {
        // code similaire pour clubs
    }

    public static void createTableUser() {
        // code similaire pour users
    }
}
