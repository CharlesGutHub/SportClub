package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;

import dao.InscriptionDAO;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,    // 1 MB
	maxFileSize = 1024 * 1024 * 5,      // 5 MB
	maxRequestSize = 1024 * 1024 * 10)  // 10 MB
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String confirmMdp = request.getParameter("confirm_mdp");
        String role = request.getParameter("role");

        //Getting the pdf file
        Part justificatifPart = request.getPart("justificatif");
        InputStream justificatifStream = justificatifPart.getInputStream();
        
        //Checking if the password are the same
        if (!mdp.equals(confirmMdp)) {
            response.getWriter().write("Les mots de passe ne correspondent pas.");
            return;
        }
        
        InscriptionDAO dao = new InscriptionDAO();
        boolean success = dao.signIn(nom, prenom, email, mdp, role, justificatifStream);
        
        if (success) {
            request.setAttribute("message", "Inscription réussie !");
        } else {
            request.setAttribute("message", "Erreur : cet email est déjà utilisé.");
        }
        
        
		doGet(request, response);
		
		// Après inscription réussie
		response.sendRedirect("login.jsp");

	}

}
