package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

import java.io.IOException;

import dao.ConnexionDAO;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String role = request.getParameter("role");

        ConnexionDAO userDAO = new ConnexionDAO();
        User user = userDAO.checkLogin(email, mdp, role);
        
        if (user == null)
        {
        	request.setAttribute("error", "Email, mot de passe ou rôle incorrect.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        switch (user.getEtatInscirption()) {
        case 1:
            // Connexion OK
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("role", role);
            response.sendRedirect("Index.jsp");
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
