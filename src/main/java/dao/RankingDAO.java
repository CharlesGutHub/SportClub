package dao;

import models.RankingResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RankingDAO {

    private final Connection connection;

    public RankingDAO(Connection connection) {
        this.connection = connection;
    }

    // Classement des clubs d'une commune par nombre de licenciés, en distinguant chaque club par fédération
    public List<RankingResult> getClassementParClubsDansCommune(String communeRecherchee) throws SQLException {
        List<RankingResult> resultats = new ArrayList<>();

        String sql = """
            SELECT 
                c.libelle,
                c.nom_fed AS federation,
                SUM(COALESCE(l.l_2022, 0)) AS total_licencies,
                SUM(COALESCE(l.l_h_2022, 0)) AS total_hommes,
                SUM(COALESCE(l.l_f_2022, 0)) AS total_femmes
            FROM clubs c
            JOIN licences l ON c.code_commune = l.code_commune AND c.nom_fed = l.nom_fed
            WHERE LOWER(c.libelle) = LOWER(?)
            GROUP BY c.libelle, c.nom_fed
            ORDER BY total_licencies DESC
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, communeRecherchee);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nomClub = rs.getString("libelle");
                    String federation = rs.getString("federation");
                    int totalLicencies = rs.getInt("total_licencies");
                    int totalHommes = rs.getInt("total_hommes");
                    int totalFemmes = rs.getInt("total_femmes");

                    resultats.add(new RankingResult(nomClub, federation, totalLicencies, totalHommes, totalFemmes));
                }
            }
        }

        return resultats;
    }
}
