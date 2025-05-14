package servlets;

import dao.AnnonceDAO;
import models.DatabaseConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/DeleteAnnounce")
public class DeleteAnnounce extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection conn = DriverManager.getConnection(
                DatabaseConfig.getDbUrl(),
                DatabaseConfig.getDbUsername(),
                DatabaseConfig.getDbPassword())) {

            AnnonceDAO dao = new AnnonceDAO(conn);
            dao.delete(id);
            response.sendRedirect("ShowAdminAnnounces");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur de suppression : " + e.getMessage());
        }
    }
}
