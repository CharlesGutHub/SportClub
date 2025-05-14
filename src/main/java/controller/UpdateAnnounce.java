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
import java.time.format.DateTimeFormatter;

@WebServlet("/UpdateAnnounce")
public class UpdateAnnounce extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String message = request.getParameter("message");
        String dateExpStr = request.getParameter("dateExpiration");
        LocalDateTime dateExp = LocalDateTime.parse(dateExpStr, 
                             DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        Annonce a = new Annonce();
        a.setId(id);
        a.setMessage(message);
        a.setDateExpiration(dateExp);

        try (Connection conn = DriverManager.getConnection(
                DatabaseConfig.getDbUrl(),
                DatabaseConfig.getDbUsername(),
                DatabaseConfig.getDbPassword())) {

            AnnonceDAO dao = new AnnonceDAO(conn);
            dao.update(a);
            response.sendRedirect("AdminAnnouncementMenu.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur lors de la mise à jour : " + e.getMessage());
        }
    }
}
