package dao;

import models.DiagramsClub;
import java.sql.*;
import java.util.*;

public class DiagramsDAO {
    private final Connection connection;

    public DiagramsDAO(Connection connection) {
        this.connection = connection;
    }

    public List<DiagramsClub> getStatsParClub() throws SQLException {
        List<DiagramsClub> stats = new ArrayList<>();

        String sql = """
            SELECT c.libelle AS nomClub,
                   SUM(COALESCE(l.l_2022, 0)) AS total_licencies,
                   SUM(COALESCE(l.l_h_2022, 0)) AS total_hommes,
                   SUM(COALESCE(l.l_f_2022, 0)) AS total_femmes,
                   SUM(COALESCE(l.l_0_4_2022, 0) + COALESCE(l.l_5_9_2022, 0) +
                       COALESCE(l.l_10_14_2022, 0) + COALESCE(l.l_15_19_2022, 0)) AS total_jeunes
            FROM clubs c
            JOIN licences l ON c.code_commune = l.code_commune AND c.fed_2022 = l.fed_2022
            GROUP BY c.libelle
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                stats.add(new DiagramsClub(
                    rs.getString("nomClub"),
                    rs.getInt("total_licencies"),
                    rs.getInt("total_hommes"),
                    rs.getInt("total_femmes"),
                    rs.getInt("total_jeunes")
                ));
            }
        }

        return stats;
    }
    public List<DiagramsClub> getStatsParClubDansCommune(String commune) throws SQLException {
        List<DiagramsClub> stats = new ArrayList<>();

        String sql = """
            SELECT c.libelle AS nomClub,
                   SUM(COALESCE(l.l_2022, 0)) AS total_licencies,
                   SUM(COALESCE(l.l_h_2022, 0)) AS total_hommes,
                   SUM(COALESCE(l.l_f_2022, 0)) AS total_femmes,
                   SUM(COALESCE(l.l_0_4_2022, 0) + COALESCE(l.l_5_9_2022, 0) +
                       COALESCE(l.l_10_14_2022, 0) + COALESCE(l.l_15_19_2022, 0)) AS total_jeunes
            FROM clubs c
            JOIN licences l ON c.code_commune = l.code_commune AND c.fed_2022 = l.fed_2022
            WHERE LOWER(c.libelle) = LOWER(?)
            GROUP BY c.libelle
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, commune);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    stats.add(new DiagramsClub(
                        rs.getString("nomClub"),
                        rs.getInt("total_licencies"),
                        rs.getInt("total_hommes"),
                        rs.getInt("total_femmes"),
                        rs.getInt("total_jeunes")
                    ));
                }
            }
        }

        return stats;
    }
}
