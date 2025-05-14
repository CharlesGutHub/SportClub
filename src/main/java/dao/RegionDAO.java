package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.InfoRegion;

public class RegionDAO {
    private Connection connection;

    public RegionDAO(Connection connection) {
        this.connection = connection;
    }

    public List<InfoRegion> getInfosParRegion() throws SQLException {
        List<InfoRegion> result = new ArrayList<>();

        String sql = "SELECT region, " +
                "SUM(Total) AS total, " +
                "SUM(h_1_4_ans + h_5_9_ans + h_10_14_ans + h_15_19_ans + h_20_24_ans + h_25_29_ans + h_30_34_ans + " +
                "h_35_39_ans + h_40_44_ans + h_45_49_ans + h_50_54_ans + h_55_59_ans + h_60_64_ans + h_65_69_ans + " +
                "h_70_74_ans + h_75_79_ans + h_80_99_ans + h_nr) AS totalHomme, " +
                "SUM(f_1_4_ans + f_5_9_ans + f_10_14_ans + f_15_19_ans + f_20_24_ans + f_25_29_ans + f_30_34_ans + " +
                "f_35_39_ans + f_40_44_ans + f_45_49_ans + f_50_54_ans + f_55_59_ans + f_60_64_ans + f_65_69_ans + " +
                "f_70_74_ans + f_75_79_ans + f_80_99_ans + f_nr) AS totalFemme " +
                "FROM population " +
                "GROUP BY region";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String region = rs.getString("region");
                int total = rs.getInt("total");
                int totalH = rs.getInt("totalHomme");
                int totalF = rs.getInt("totalFemme");

                result.add(new InfoRegion(region, total, totalH, totalF));
            }
        }

        return result;
    }

    // Nouvelle méthode pour la recherche
    public List<InfoRegion> searchRegionByName(String keyword) throws SQLException {
        List<InfoRegion> result = new ArrayList<>();

        String sql = "SELECT region, " +
                "SUM(Total) AS total, " +
                "SUM(h_1_4_ans + h_5_9_ans + h_10_14_ans + h_15_19_ans + h_20_24_ans + h_25_29_ans + h_30_34_ans + " +
                "h_35_39_ans + h_40_44_ans + h_45_49_ans + h_50_54_ans + h_55_59_ans + h_60_64_ans + h_65_69_ans + " +
                "h_70_74_ans + h_75_79_ans + h_80_99_ans + h_nr) AS totalHomme, " +
                "SUM(f_1_4_ans + f_5_9_ans + f_10_14_ans + f_15_19_ans + f_20_24_ans + f_25_29_ans + f_30_34_ans + " +
                "f_35_39_ans + f_40_44_ans + f_45_49_ans + f_50_54_ans + f_55_59_ans + f_60_64_ans + f_65_69_ans + " +
                "f_70_74_ans + f_75_79_ans + f_80_99_ans + f_nr) AS totalFemme " +
                "FROM population " +
                "WHERE region LIKE ? " +
                "GROUP BY region";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String region = rs.getString("region");
                    int total = rs.getInt("total");
                    int totalH = rs.getInt("totalHomme");
                    int totalF = rs.getInt("totalFemme");

                    result.add(new InfoRegion(region, total, totalH, totalF));
                }
            }
        }

        return result;
    }
}