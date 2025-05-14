package controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.InfoCommune;
import dao.CommuneDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

@WebServlet("/ShowCommune")
public class ShowCommune extends HttpServlet {

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

            // Récupérer les informations de la base de données
            String jdbcURL = config.getProperty("db.url");
            String username = config.getProperty("db.username");
            String password = config.getProperty("db.password");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");  // Charger le driver MySQL
                System.out.println("Driver chargé avec succès");
            } catch (ClassNotFoundException e) {
                System.err.println("Erreur lors du chargement du driver MySQL : " + e.getMessage());
                throw new ServletException("Erreur lors du chargement du driver MySQL", e);
            }
            // Connexion à la base de données et récupération des données
            try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            	CommuneDAO dao = new CommuneDAO(connection);
                List<InfoCommune> infos = dao.getInfosParCommune();

                // Passer les données à la vue (JSP)
                request.setAttribute("infosCommune", infos);
                request.getRequestDispatcher("/DisplayCommune.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // Gérer l'exception
            throw new ServletException("Erreur lors du chargement des données de région", e);
        }
    }
}