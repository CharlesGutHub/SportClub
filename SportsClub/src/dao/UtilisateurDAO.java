package dao;
import ui.Password;
import java.sql.*;
import java.util.*;
import model.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UtilisateurDAO {

    private Connection connection;

    public UtilisateurDAO() {
        try {
            // Connexion à la base MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sportclub", "root", "root"
            );
        } catch (Exception e) {
        	System.err.println("❌ Erreur de connexion à la base de données !");
            e.printStackTrace();
        }
    }

    public void ajouter(Utilisateur u) {
        try {
            String motDePasseHache = Password.hash(u.getMotDePasse());
            String sql = "INSERT INTO utilisateur (nom, email, mot_de_passe, valide, elu, acteur_sportif, piece_justificative,role) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, motDePasseHache); // ON ENREGISTRE LE HASH !!!
            stmt.setBoolean(4, u.isValide());
            stmt.setBoolean(5, u.isElu());
            stmt.setBoolean(6, u.isActeurSportif());
            stmt.setString(7, u.getCheminPieceJustificative());
            stmt.setString(8, u.getRole());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                u.setId(rs.getInt(1));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void mettreAJour(Utilisateur u) {
        try {
            String sql = "UPDATE utilisateur SET nom = ?, email = ?, mot_de_passe = ?, elu = ?, acteur_sportif = WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, Password.hash(u.getMotDePasse()));
            stmt.setBoolean(4, u.isElu());
            stmt.setBoolean(5, u.isActeurSportif());
            stmt.setString(6, u.getCheminPieceJustificative());
            stmt.setInt(7, u.getId());
            
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void supprimer(int id) {
        try {
            String sql = "DELETE FROM utilisateur WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void validerUtilisateur(int id) {
        try {
            String sql = "UPDATE utilisateur SET valide = 1 WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void mettreAJourPieceJustificative(int id, String chemin) {
        try {
            String sql = "UPDATE utilisateur SET piece_justificative = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, chemin);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    // ✅ Méthode lister() bien placée dans la classe
    public List<Utilisateur> lister() {
        List<Utilisateur> liste = new ArrayList<>();
        String sql = "SELECT id, nom, email, mot_de_passe, valide, elu, acteur_sportif, piece_justificative, role FROM utilisateur";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String email = rs.getString("email");
                String motDePasse = rs.getString("mot_de_passe");
                boolean valide = rs.getBoolean("valide");
                boolean elu = rs.getBoolean("elu");
                boolean acteurSportif = rs.getBoolean("acteur_sportif");
                String cheminPiece = rs.getString("piece_justificative");
                String role = rs.getString("role");
                Utilisateur u = new Utilisateur(id, nom, email, motDePasse, valide, elu, acteurSportif, cheminPiece, role);
                liste.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }



    public Utilisateur trouverParEmail(String email) {
        String sql = "SELECT * FROM utilisateur WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String motDePasse = rs.getString("mot_de_passe");
                boolean valide = rs.getBoolean("valide");
                boolean elu = rs.getBoolean("elu");
                boolean acteurSportif = rs.getBoolean("acteur_sportif");
                String cheminPiece = rs.getString("piece_justificative");
                String role = rs.getString("role");  // AJOUTE CETTE LIGNE

                Utilisateur u = new Utilisateur( nom, email, motDePasse,valide, elu, acteurSportif,cheminPiece,role);
                u.setId(id); // ✔️ très bien
                return u;            // 👈 tu dois retourner l'objet ici
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Aucun utilisateur trouvé
    }



}
