package dao;

import java.sql.*;

import utils.DataBaseCon;

public class ConnexionDAO {
	
	/**
     * Renvoie :
     *  1  -> Connexion réussie
     *  0  -> Mauvais email/mot de passe/role
     *  2  -> Inscription en attente (etat_inscription = 0)
     * -1  -> Inscription refusée (etat_inscription = -1)
     */
	public int checkLogin(String email, String mdp, String role) {
        String sql = "SELECT etat_inscription FROM users WHERE email = ? AND mdp = ? AND role = ?";

        try (Connection con = DataBaseCon.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, mdp);
            stmt.setString(3, role);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int etat = rs.getInt("etat_inscription");
                if (etat == 1) return 1;
                if (etat == 0) return 2;
                if (etat == -1) return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}

