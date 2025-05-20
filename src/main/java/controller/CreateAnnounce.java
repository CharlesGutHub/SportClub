package controller;

import dao.AnnonceDAO;
import models.Annonce;
import models.DatabaseConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;

@WebServlet("/CreateAnnounce")
public class CreateAnnounce extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération des paramètres
        String message = request.getParameter("message");
        int daysValid = Integer.parseInt(request.getParameter("daysValid"));
        
        // Validation des entrées
        if (message == null || message.trim().isEmpty() || daysValid <= 0) {
            request.setAttribute("error", "Données invalides");
            request.getRequestDispatcher("CreateAnnounce.jsp").forward(request, response);
            return;
        }
        
        // Création de l'objet Annonce
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(daysValid);
        Annonce annonce = new Annonce(message, expirationDate);
        
        // Connexion à la base et insertion
        try (Connection connection = DriverManager.getConnection(
                DatabaseConfig.getDbUrl(), 
                DatabaseConfig.getDbUsername(), 
                DatabaseConfig.getDbPassword())) {
            
            AnnonceDAO dao = new AnnonceDAO(connection);
            dao.create(annonce);
            
            // Redirection vers la page de visualisation
            response.sendRedirect("AnnouncesMenu.jsp");
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur technique lors de la création: " + e.getMessage());
            request.getRequestDispatcher("CreateAnnounces.jsp").forward(request, response);
        }
    }
}