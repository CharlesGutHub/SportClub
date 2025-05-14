package controller;

import dao.AnnonceDAO;
import models.DatabaseConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/view-announces")
public class ViewAnnounces extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DriverManager.getConnection(
                DatabaseConfig.getDbUrl(),
                DatabaseConfig.getDbUsername(),
                DatabaseConfig.getDbPassword())) {
            
            AnnonceDAO dao = new AnnonceDAO(connection);
            request.setAttribute("annonces", dao.getAnnoncesValides());
            request.getRequestDispatcher("Billboard.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur de chargement: " + e.getMessage());
            request.getRequestDispatcher("Billboard.jsp").forward(request, response);
        }
    }
}