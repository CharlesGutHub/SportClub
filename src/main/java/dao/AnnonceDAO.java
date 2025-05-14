package dao;

import models.Annonce;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO pour l'entité Annonce.
 * Gère les opérations CRUD sur la table "annonce".
 */
public class AnnonceDAO {
    private final Connection connection;

    /**
     * Constructeur : on injecte la connexion JDBC.
     */
    public AnnonceDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Crée une nouvelle annonce en base et met à jour son ID et sa date de création.
     */
    public void create(Annonce annonce) throws SQLException {
        String sql = "INSERT INTO annonce (message, date_expiration) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, annonce.getMessage());
            stmt.setTimestamp(2, Timestamp.valueOf(annonce.getDateExpiration()));
            stmt.executeUpdate();

            // Récupération de l'ID généré
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    annonce.setId(rs.getInt(1));
                }
            }

            // Récupération de la date de création depuis la base
            String selectDate = "SELECT date_creation FROM annonce WHERE id = ?";
            try (PreparedStatement stmt2 = connection.prepareStatement(selectDate)) {
                stmt2.setInt(1, annonce.getId());
                try (ResultSet rs2 = stmt2.executeQuery()) {
                    if (rs2.next()) {
                        annonce.setDateCreation(rs2.getTimestamp("date_creation").toLocalDateTime());
                    }
                }
            }
        }
    }

    /**
     * Récupère la liste des annonces dont la date d'expiration est supérieure à l'instant présent.
     */
    public List<Annonce> getAnnoncesValides() throws SQLException {
        List<Annonce> annonces = new ArrayList<>();
        String sql = "SELECT * FROM annonce WHERE date_expiration > CURRENT_TIMESTAMP ORDER BY date_creation DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Annonce annonce = new Annonce();
                annonce.setId(rs.getInt("id"));
                annonce.setMessage(rs.getString("message"));
                annonce.setDateCreation(rs.getTimestamp("date_creation").toLocalDateTime());
                annonce.setDateExpiration(rs.getTimestamp("date_expiration").toLocalDateTime());
                annonces.add(annonce);
            }
        }
        return annonces;
    }

    /**
     * Recherche d'une annonce par son identifiant.
     */
    public Annonce findById(int id) throws SQLException {
        String sql = "SELECT * FROM annonce WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Annonce annonce = new Annonce();
                    annonce.setId(id);
                    annonce.setMessage(rs.getString("message"));
                    annonce.setDateCreation(rs.getTimestamp("date_creation").toLocalDateTime());
                    annonce.setDateExpiration(rs.getTimestamp("date_expiration").toLocalDateTime());
                    return annonce;
                }
            }
        }
        return null;
    }

    /**
     * Met à jour une annonce existante en base.
     */
    public void update(Annonce annonce) throws SQLException {
        String sql = "UPDATE annonce SET message = ?, date_expiration = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, annonce.getMessage());
            ps.setTimestamp(2, Timestamp.valueOf(annonce.getDateExpiration()));
            ps.setInt(3, annonce.getId());
            ps.executeUpdate();
        }
    }

    /**
     * Supprime une annonce par son identifiant.
     */
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM annonce WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
