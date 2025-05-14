package servlets;

import dao.DepartementDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.InfoDepartement;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

@WebServlet("/ShowDepartement")
public class ShowDepartement extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Properties config = new Properties();

            // Charger le fichier de configuration database.properties depuis le classpath
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
                if (input == null) {
                    throw new ServletException("Le fichier de configuration 'database.properties' est introuvable.");
                }
                config.load(input);
            }

            // Lire les infos de connexion
            String jdbcURL = config.getProperty("db.url");
            String username = config.getProperty("db.username");
            String password = config.getProperty("db.password");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver MySQL chargé avec succès");
            } catch (ClassNotFoundException e) {
                System.err.println("Erreur lors du chargement du driver MySQL : " + e.getMessage());
                throw new ServletException("Driver MySQL non trouvé", e);
            }

            // Connexion + DAO + récupération des données
            try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
                DepartementDAO dao = new DepartementDAO(connection);
                List<InfoDepartement> infos = dao.getInfosParDepartement();

                request.setAttribute("infosDepartement", infos);
                request.getRequestDispatcher("/DisplayDepartement.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException("Erreur lors du chargement des données du département", e);
        }
    }
}