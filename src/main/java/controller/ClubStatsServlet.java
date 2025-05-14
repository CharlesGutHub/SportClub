package controller;

import dao.ClubDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Club;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@WebServlet("/ClubStatsServlet")
public class ClubStatsServlet extends HttpServlet {

    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sportclub", // <-- change ici
                "root",                                 // <-- change ici
                ""                                   // <-- change ici
            );
        } catch (Exception e) {
            throw new ServletException("Erreur connexion base de données", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ClubDAO clubDAO = new ClubDAO(connection);
            List<Club> clubs = clubDAO.getClubStats();
            request.setAttribute("clubs", clubs);
            request.getRequestDispatcher("/clubStats.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erreur lors du chargement des clubs", e);
        }
    }

    @Override
    public void destroy() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
