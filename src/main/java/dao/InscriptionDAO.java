package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.DataBaseCon;

public class InscriptionDAO {
    
    public boolean signIn(String nom, String prenom, String email, String mdp, String role, InputStream justificatifStream) {
        String sql = "INSERT INTO users (nom, prenom, email, mdp, role, justificatif) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection con = DataBaseCon.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, email);
            pstmt.setString(4, mdp);
            pstmt.setString(5, role);
            pstmt.setBlob(6, justificatifStream);

            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace(); // log ou gestion d’erreur plus propre à terme
        }
        return false;
    }
}
