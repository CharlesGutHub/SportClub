package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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

    /**
     * Affiche le formulaire de login (et pré-remplit via les cookies si présents).
     * Si l'utilisateur est déjà en session, on le redirige directement vers recherche.jsp.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si l'utilisateur est déjà connecté, on l'envoie vers la page de recherche
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            response.sendRedirect(request.getContextPath() + "/recherche.jsp");
            return;
        }
        // Sinon on affiche le formulaire de login
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    /**
     * Traite la soumission du formulaire de login.
     * En cas de succès, crée la session, stocke 2 cookies (email + rôle) et redirige vers recherche.jsp.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String mdp   = request.getParameter("mdp");
        String role  = request.getParameter("role");

        ConnexionDAO userDAO = new ConnexionDAO();
        int loginStatus = userDAO.checkLogin(email,mdp,role);
         
        switch (loginStatus) {
            case 1:
                // Connexion OK → création de la session
                HttpSession session = request.getSession(true);
                session.setAttribute("email", email);
                session.setAttribute("role", role);

                // Création des cookies pour "email" et "role" (7 jours, HttpOnly)
                Cookie emailCookie = new Cookie("email", email);
                emailCookie.setMaxAge(7 * 24 * 60 * 60);
                emailCookie.setHttpOnly(true);
                response.addCookie(emailCookie);

                Cookie roleCookie = new Cookie("role", role);
                roleCookie.setMaxAge(7 * 24 * 60 * 60);
                roleCookie.setHttpOnly(true);
                response.addCookie(roleCookie);

                // Redirection vers la page de recherche avancée
                response.sendRedirect(request.getContextPath() + "/recherche.jsp");
                break;

            case 2:
                request.setAttribute("error", "Votre inscription est en attente de validation.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                break;

            case -1:
                request.setAttribute("error", "Votre inscription a été refusée.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                break;

            default:
                request.setAttribute("error", "Email, mot de passe ou rôle incorrect.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                break;
        }
    }
}
