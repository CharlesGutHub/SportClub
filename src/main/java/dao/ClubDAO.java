package dao;

import java.sql.*;            // Import des interfaces JDBC : Connection, PreparedStatement, ResultSet, SQLException
import java.util.*;          // Import des classes utilitaires : List, ArrayList
import models.Club;           // Import du modèle Club

public class ClubDAO {

    // Attribut de connexion à la base de données, injecté via le constructeur
    private Connection connection;

    // Constructeur : reçoit et stocke la connexion JDBC
    public ClubDAO(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour récupérer les statistiques des clubs
    // Retourne une liste de Club et peut lever SQLException
    public List<Club> getClubStats() throws SQLException {
        // Création de la liste qui contiendra les résultats
        List<Club> clubs = new ArrayList<>();

        // Requête SQL multi-lignes (Java Text Block) pour agréger les données
        String sql = """
            SELECT 
                c.code_commune,                -- Code INSEE de la commune
                c.libelle AS commune,          -- Nom de la commune
                SUM(COALESCE(l.l_2022, 0)) AS total_licencies,      -- Total des licenciés en 2022
                SUM(COALESCE(l.l_h_2022, 0)) AS total_hommes,        -- Total des hommes en 2022
                SUM(COALESCE(l.l_f_2022, 0)) AS total_femmes         -- Total des femmes en 2022
            FROM clubs c
            JOIN licences l 
                ON c.code_commune = l.code_commune  -- Jointure sur le code INSEE
               AND c.fed_2022 = l.fed_2022          -- Filtre sur la même fédération 2022
            GROUP BY c.code_commune, c.libelle     -- Regroupement par commune
        """;

        // Exécution de la requête dans un bloc try-with-resources pour fermer automatiquement
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            // Parcours du ResultSet ligne par ligne
            while (rs.next()) {
                // Création et ajout de l'objet Club à la liste, en mappant chaque colonne
                clubs.add(new Club(
                    rs.getString("commune"),       // Récupère le nom de la commune
                    rs.getInt("total_licencies"),  // Récupère le total des licenciés
                    rs.getInt("total_hommes"),     // Récupère le total des hommes
                    rs.getInt("total_femmes")      // Récupère le total des femmes
                ));
            }
        } // stmt et rs sont fermés automatiquement ici

        // Retourne la liste complète des clubs
        return clubs;
    }
}
