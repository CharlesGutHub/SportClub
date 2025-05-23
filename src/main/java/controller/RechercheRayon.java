package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Licence;

import java.io.IOException;
import java.util.ArrayList;

import dao.LicenceDAO;
import org.apache.commons.text.StringEscapeUtils;
/**
 * Servlet implementation class RechercheRayon
 */
@WebServlet("/RechercheRayon")
public class RechercheRayon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercheRayon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commune = StringEscapeUtils.escapeEcmaScript((String) request.getParameter("recherche"));
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));
		String rayonStr = StringEscapeUtils.escapeEcmaScript((request.getParameter("rayon")));
		int rayon = 10; // valeur par défaut
		if (rayonStr != null) {
		    try {
		        rayon = Integer.parseInt(rayonStr);
		    } catch (NumberFormatException e) {
		        rayon = 10; // fallback
		    }
		}
		
		System.out.println("Recherche rayon : latitude=" + latitude + ", longitude=" + longitude + ", rayon=" + rayon);


		
		double deltaLat = rayon / 111.0;
		double deltaLon = rayon / (111.0 * Math.cos(Math.toRadians(latitude)));

		double latMin = latitude - deltaLat;
		double latMax = latitude + deltaLat;
		double lonMin = longitude - deltaLon;
		double lonMax = longitude + deltaLon;

		System.out.println("latMin = " + latMin + ", latMax = " + latMax + ", lonMin = " + lonMin + ", lonMax = " + lonMax);
        ArrayList<Licence> list = LicenceDAO.rechercheRayon(latMin, latMax, lonMin, lonMax);
        

        System.out.println("Rayon :"+rayon);
        
        ArrayList<Licence> clubsProches = new ArrayList<>();
        for(Licence club : list)
        {
        	double distance = haversine(latitude, longitude, club.getLatitude(), club.getLongitude());
            if (distance <= rayon) {
                clubsProches.add(club);
            }
        }
        
        request.setAttribute("listClub", clubsProches);
        request.setAttribute("recherche", commune);
        request.setAttribute("rayon", rayon);
        request.getRequestDispatcher("recherche.jsp").forward(request, response);
	}
	
	public static double haversine(double lat1, double lon1, double lat2, double lon2) {
	    double R = 6371.0; // Rayon de la Terre en km
	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
	             + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	             * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    return R * c;
	}


}
