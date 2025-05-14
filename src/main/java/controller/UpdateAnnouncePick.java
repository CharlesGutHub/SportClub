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

@WebServlet("/UpdateAnnouncePick")
public class UpdateAnnouncePick extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection conn = DriverManager.getConnection(
                DatabaseConfig.getDbUrl(),
                DatabaseConfig.getDbUsername(),
                DatabaseConfig.getDbPassword())) {

            AnnonceDAO dao = new AnnonceDAO(conn);
            Annonce a = dao.findById(id);
            request.setAttribute("annonce", a);
            request.getRequestDispatcher("UpdateAnnounce.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur technique : " + e.getMessage());
        }
    }
}
