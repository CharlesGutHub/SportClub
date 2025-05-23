package dao;

import java.sql.*;

import utils.DataBaseCon;
import org.apache.commons.text.StringEscapeUtils;

import models.User;
public class ConnexionDAO {
	
	/**
     * Renvoie :
     *  1  -> Connexion réussie
     *  0  -> Mauvais email/mot de passe/role
     *  2  -> Inscription en attente (etat_inscription = 0)
     * -1  -> Inscription refusée (etat_inscription = -1)
     */
	public User checkLogin(String email, String mdp, String role) {
	    String sql = "SELECT * FROM users WHERE email = ? AND mdp = ? AND role = ?";
	    User user = null;

	    try (Connection con = DataBaseCon.getConnection();
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, email);
	        stmt.setString(2, mdp);
	        stmt.setString(3, role);

	        ResultSet rs = stmt.executeQuery();

	        // IMPORTANT : appeler rs.next() pour accéder à la ligne de résultat
	        if (rs.next()) {
	            System.out.println("Utilisateur trouvé dans la base");

	            // Debug : afficher les valeurs lues
	            System.out.println("Nom: " + rs.getString(2));
	            System.out.println("Prenom: " + rs.getString(3));
	            System.out.println("Email: " + rs.getString(4));
	            System.out.println("Role: " + rs.getString(6));
	            System.out.println("Id: " + rs.getInt(8));

	            user = new User(
	                StringEscapeUtils.escapeHtml4(rs.getString(2)),  // nom
	                StringEscapeUtils.escapeHtml4(rs.getString(3)),  // prénom
	                StringEscapeUtils.escapeHtml4(rs.getString(4)),  // email
	                StringEscapeUtils.escapeHtml4(rs.getString(6)),  // rôle
	                rs.getInt(8)                                     // id
	            );
	        } else {
	            System.out.println("Aucun utilisateur correspondant trouvé.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return user;
	}

}

