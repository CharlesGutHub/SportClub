package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import dao.DiagramsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DiagramsClub;
import utils.DataBaseCon;

@WebServlet("/DiagramsClubs")
public class DiagramsClubs extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commune = request.getParameter("commune");
        if (commune == null || commune.trim().isEmpty()) {
            request.setAttribute("error", "Veuillez entrer une commune.");
            request.getRequestDispatcher("DiagramsForm.jsp").forward(request, response);
            return;
        }

        try {
            Connection conn = DataBaseCon.getConnection();
            DiagramsDAO dao = new DiagramsDAO(conn);
            List<DiagramsClub> stats = dao.getStatsParClubDansCommune(commune.trim());

            if (stats.isEmpty()) {
                request.setAttribute("error", "Aucun club trouvé pour cette commune.");
                request.getRequestDispatcher("DiagramsForm.jsp").forward(request, response);
                return;
            }

            request.setAttribute("stats", stats);
            request.setAttribute("commune", commune);
            request.getRequestDispatcher("DiagramsClubs.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des données", e);
        }
    }
}
