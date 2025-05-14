package servlets;

import dao.AnnonceDAO;
import models.DatabaseConfig;
import models.Annonce;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * Servlet qui récupère la liste des annonces (valides) pour l'administration
 * et les transmet à la JSP ViewAdminAnnounces.jsp.
 */
@WebServlet("/ShowAdminAnnounces")
public class ShowAdminAnnounces extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // On va récupérer les annonces valides et les envoyer à la JSP
        try (Connection conn = DriverManager.getConnection(
                DatabaseConfig.getDbUrl(),
                DatabaseConfig.getDbUsername(),
                DatabaseConfig.getDbPassword())) {

            // Instanciation du DAO et récupération des annonces
            AnnonceDAO dao = new AnnonceDAO(conn);
            List<Annonce> annonces = dao.getAnnoncesValides();

            // Mise en attribut pour la JSP
            request.setAttribute("annonces", annonces);

            // Forward vers la page d'administration
            request.getRequestDispatcher("ViewAdminAnnounces.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            // En cas d'erreur, on logue et on renvoie un message d'erreur à la JSP
            e.printStackTrace();
            request.setAttribute("error", "Erreur de chargement des annonces : " + e.getMessage());
            request.getRequestDispatcher("ViewAdminAnnounces.jsp")
                   .forward(request, response);
        }
    }
}
