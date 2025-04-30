package bddInitializer;

import utils.DataBaseCon;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS licences (
                id INT AUTO_INCREMENT PRIMARY KEY,
                code_commune VARCHAR(5),
                libelle VARCHAR(45),
                region VARCHAR(38),
                fed_2022 VARCHAR(3),
                nom_fed VARCHAR(113),
                l_2022 INT,
                l_0_4_2022 INT,
                l_5_9_2022 INT,
                l_10_14_2022 INT,
                l_15_19_2022 INT,
                l_20_29_2022 INT,
                l_30_44_2022 INT,
                l_45_59_2022 INT,
                l_60_74_2022 INT,
                l_75_2022 INT,
                l_f_2022 INT,
                l_0_4_f_2022 INT,
                l_5_9_f_2022 INT,
                l_10_14_f_2022 INT,
                l_15_19_f_2022 INT,
                l_20_29_f_2022 INT,
                l_30_44_f_2022 INT,
                l_45_59_f_2022 INT,
                l_60_74_f_2022 INT,
                l_75_f_2022 INT,
                l_h_2022 INT,
                l_0_4_h_2022 INT,
                l_5_9_h_2022 INT,
                l_10_14_h_2022 INT,
                l_15_19_h_2022 INT,
                l_20_29_h_2022 INT,
                l_30_44_h_2022 INT,
                l_45_59_h_2022 INT,
                l_60_74_h_2022 INT,
                l_75_h_2022 INT,
                l_qp_2022 INT,
                l_qp_f_2022 INT,
                l_qp_h_2022 INT
            );
            """;

        try (Connection conn = DataBaseCon.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("✅ Table 'licences' créée avec succès.");
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de la table : " + e.getMessage(), e);
        }
    }
}
