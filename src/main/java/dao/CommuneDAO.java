package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.InfoCommune;

public class CommuneDAO {
    private Connection connection;

    public CommuneDAO(Connection connection) {
        this.connection = connection;
    }

    public List<InfoCommune> getInfosParCommune() throws SQLException {
        List<InfoCommune> result = new ArrayList<>();

        String sql = "SELECT libelle AS commune, " +
                "SUM(l_2022) AS total, " +
                "SUM(l_h_2022) AS totalHomme, " +
                "SUM(l_f_2022) AS totalFemme " +
                "FROM licences GROUP BY commune";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String commune = rs.getString("commune");
                int total = rs.getInt("total");
                int totalH = rs.getInt("totalHomme");
                int totalF = rs.getInt("totalFemme");

                result.add(new InfoCommune(commune, total, totalH, totalF));
            }
        }

        return result;
    }
}