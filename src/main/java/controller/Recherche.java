package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import dao.RechercheDAO;
import models.Licence;
/**
 * Servlet implementation class Recherche
 */
@WebServlet("/Recherche")
public class Recherche extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recherche() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String zoneGeoType = (String) request.getParameter("zoneGeoType");
		String zoneGeo = (String) request.getParameter("zoneGeo");
		String pageParam = request.getParameter("page");
		String sport = request.getParameter("sport");
		
		if (zoneGeoType.equals("departement"))
		{
			String[] zone = zoneGeo.split("–");

		   
		    if (zone.length == 2) {
		        zoneGeo = zone[0].trim(); 
		        System.out.println("Numéro de département : " + zoneGeo);
		    } else {
		        
		        System.out.println("Format invalide pour zoneGeo : " + zoneGeo);
		    }
		}
		
		int page = 1; // valeur par défaut

		if (pageParam != null && !pageParam.isEmpty()) {
		    try {
		        page = Integer.parseInt(pageParam);
		    } catch (NumberFormatException e) {
		        page = 1; 
		    }
		}
		int offset = (page - 1) * 1000;
		System.out.println(sport);
		ArrayList<Licence> list = RechercheDAO.rechercheZoneGeo(zoneGeo, zoneGeoType,offset,sport);		
		System.out.println(list.size());
		request.setAttribute("zoneGeoType", zoneGeoType);
		request.setAttribute("zoneGeo", zoneGeo);
		request.setAttribute("page", page);
		request.setAttribute("sport", sport);
		request.setAttribute("listClub", list);
		request.getRequestDispatcher("recherche.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
