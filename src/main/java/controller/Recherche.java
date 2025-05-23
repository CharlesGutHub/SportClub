package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Licence;
import org.apache.commons.text.StringEscapeUtils;
import java.io.IOException;
import java.util.ArrayList;

import dao.LicenceDAO;

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
		
		String zoneGeoType = StringEscapeUtils.escapeHtml4((String) request.getParameter("zoneGeoType"));
		String zoneGeo = StringEscapeUtils.escapeHtml4((String) request.getParameter("zoneGeo"));
		String sport = StringEscapeUtils.escapeHtml4(request.getParameter("sport"));
		
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
		
		System.out.println(sport);
		ArrayList<Licence> list = LicenceDAO.rechercheZoneGeo(zoneGeo, zoneGeoType,sport);		
		System.out.println(list.size());
		request.setAttribute("zoneGeoType", StringEscapeUtils.escapeHtml4(zoneGeoType));
		request.setAttribute("zoneGeo", StringEscapeUtils.escapeHtml4(zoneGeo));
		request.setAttribute("sport", StringEscapeUtils.escapeHtml4(sport));
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
