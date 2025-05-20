package controller;

import dao.RankingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.RankingResult;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

@WebServlet("/RankingByCommune")
public class RankingByCommune extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commune = request.getParameter("commune");

        if (commune == null || commune.isBlank()) {
            request.setAttribute("error", "Veuillez entrer une commune.");
            request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
            return;
        }

        Properties config = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new ServletException("Le fichier 'database.properties' est introuvable.");
            }
            config.load(input);
        }

        String jdbcURL = config.getProperty("db.url");
        String username = config.getProperty("db.username");
        String password = config.getProperty("db.password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Driver MySQL non trouvé", e);
        }

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            RankingDAO dao = new RankingDAO(connection);
            List<RankingResult> classement = dao.getClassementParClubsDansCommune(commune);

            if (classement.isEmpty()) {
                request.setAttribute("error", "Aucun club trouvé pour cette commune.");
            } else {
                request.setAttribute("classement", classement);
            }

            request.setAttribute("commune", commune);
            request.getRequestDispatcher("/RankingForNameResult.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erreur de connexion ou d'exécution", e);
        }
    }
}
