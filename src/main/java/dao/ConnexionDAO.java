package dao;

import java.sql.*;
import utils.DataBaseCon;

public class ConnexionDAO {

    /**
     * Renvoie :
     *   1  -> Connexion réussie (état_inscription = 1)
     *   0  -> Mauvais email/mot de passe/role (pas de ligne)
     *   2  -> Inscription en attente (etat_inscription = 0)
     *  -1  -> Inscription refusée (etat_inscription = -1)
     */
    public int checkLogin(String email, String mdp, String role) {
        String sql = "SELECT etat_inscription FROM users WHERE email = ? AND mdp = ? AND role = ?";
        try (Connection con = DataBaseCon.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, mdp);
            stmt.setString(3, role);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("etat_inscription");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // pas de ligne trouvée
        return 0;
    }
}
