package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Commune;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import dao.CodePostauxDAO;

/**
 * Servlet implementation class RechercheIntelligenteCommune
 */
@WebServlet("/RechercheIntelligenteCommune")
public class RechercheIntelligenteCommune extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercheIntelligenteCommune() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recherche = (String) request.getParameter("recherche");
		ArrayList<Commune> listVille = CodePostauxDAO.rechercheCommune(recherche);
		
		// Conversion en JSON
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    PrintWriter out = response.getWriter();

	    // À adapter selon la structure de Licence
	    // Exemple avec Gson
	    Gson gson = new Gson();
	    String json = gson.toJson(listVille); // besoin de la lib Gson ici

	    out.print(json);
	    out.flush();
	}

	

}
