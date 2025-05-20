package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import dao.ConnexionDAO;

@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogIn() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String role = request.getParameter("role");

        ConnexionDAO userDAO = new ConnexionDAO();
        int loginStatus = userDAO.checkLogin(email, mdp, role);

        switch (loginStatus) {
            case 1:
                // Connexion OK
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("role", role);

                if ("elu".equalsIgnoreCase(role)) {
                    response.sendRedirect("MainMenu.jsp");
                } else {
                    response.sendRedirect("AnnouncesMenu.jsp");
                }
                break;

            case 2:
                request.setAttribute("error", "Votre inscription est en attente de validation.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;

            case -1:
                request.setAttribute("error", "Votre inscription a été refusée.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;

            default:
                request.setAttribute("error", "Email, mot de passe ou rôle incorrect.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
