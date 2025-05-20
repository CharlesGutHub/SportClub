package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.InfoDepartement;

public class DepartementDAO {
    private Connection connection;

    public DepartementDAO(Connection connection) {
        this.connection = connection;
    }

    public List<InfoDepartement> getInfosParDepartement() throws SQLException {
        List<InfoDepartement> result = new ArrayList<>();

        String sql = "SELECT departement AS departement, " +
                "SUM(l_2022) AS total, " +
                "SUM(l_h_2022) AS totalHomme, " +  // Virgule ajoutée ici
                "SUM(l_f_2022) AS totalFemme " +   // Virgule optionnelle ici (dernière clause)
                "FROM licences GROUP BY departement";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String departement = rs.getString("departement");
                int total = rs.getInt("total");
                int totalH = rs.getInt("totalHomme");
                int totalF = rs.getInt("totalFemme");

                result.add(new InfoDepartement(departement, total, totalH, totalF));
            }
        }

        return result;
    }
}