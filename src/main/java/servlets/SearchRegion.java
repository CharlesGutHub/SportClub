package servlets;

import dao.RegionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.InfoRegion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

@WebServlet("/SearchRegion")
public class SearchRegion extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");

        try {
            Properties config = new Properties();
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
                if (input == null) {
                    throw new ServletException("Fichier de configuration manquant.");
                }
                config.load(input);
            }

            String jdbcURL = config.getProperty("db.url");
            String username = config.getProperty("db.username");
            String password = config.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
                RegionDAO dao = new RegionDAO(connection);
                List<InfoRegion> results = dao.searchRegionByName(query);

                request.setAttribute("infosRegion", results);
                request.getRequestDispatcher("/DisplayRegion.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException("Erreur lors de la recherche de régions", e);
        }
    }
}