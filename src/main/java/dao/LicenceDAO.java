package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.Licence;
import utils.DataBaseCon;
import org.apache.commons.text.StringEscapeUtils;


public class LicenceDAO {
	
	public static ArrayList<Licence> rechercheZoneGeo(String zoneGeo,String zoneGeoType,String sport) {
		
		ArrayList<Licence> listLicence = new ArrayList<>();
		String sql;

		if(zoneGeoType.equals("region")) 
		{
			sql = "SELECT l.*, cp.code_postal, cp.latitude, cp.longitude FROM Licences l LEFT JOIN code_postaux cp ON l.code_commune = cp.code_insee WHERE l.region = ? AND l.nom_fed LIKE ?;";
		}
		else
		{
			sql = "SELECT l.*, cp.code_postal, cp.latitude, cp.longitude FROM Licences l LEFT JOIN code_postaux cp ON l.code_commune = cp.code_insee WHERE l.departement = ? AND l.nom_fed LIKE ?;";
		}
		
		try (Connection con = DataBaseCon.getConnection();
	             PreparedStatement stmt = con.prepareStatement(sql)) {

	            stmt.setString(1, zoneGeo);
	            stmt.setString(2, "%" + sport.trim() + "%");

	            ResultSet rs = stmt.executeQuery();

	            while(rs.next())
	            {
	            	listLicence.add(new Licence(StringEscapeUtils.escapeHtml4(rs.getString(43)), StringEscapeUtils.escapeHtml4(rs.getString(3)), StringEscapeUtils.escapeHtml4(rs.getString(7)), StringEscapeUtils.escapeHtml4(rs.getString(6)),StringEscapeUtils.escapeHtml4(rs.getString(9)), rs.getInt(10), rs.getInt(20), rs.getDouble(44), rs.getDouble(45)));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		return listLicence;
	}
	
	public static ArrayList<Licence> rechercheRayon(double latMin,double latMax,double longMin, double longMax)
	{
		ArrayList<Licence> listLicence = new ArrayList<>();
		String sql = 
			    "SELECT l.*, cp.code_postal, cp.latitude, cp.longitude " +
			    "FROM Licences l " +
			    "LEFT JOIN code_postaux cp ON l.code_commune = cp.code_insee " +
			    "WHERE cp.latitude BETWEEN ? AND ? " +
			    "AND cp.longitude BETWEEN ? AND ?";
		try (Connection con = DataBaseCon.getConnection();
	             PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setDouble(1, latMin);
				stmt.setDouble(2, latMax);
				stmt.setDouble(3, longMin);
				stmt.setDouble(4, longMax);
	            ResultSet rs = stmt.executeQuery();

	            while(rs.next())
	            {
	            	listLicence.add(new Licence(StringEscapeUtils.escapeHtml4(rs.getString(43)), StringEscapeUtils.escapeHtml4(rs.getString(3)), StringEscapeUtils.escapeHtml4(rs.getString(7)), StringEscapeUtils.escapeHtml4(rs.getString(6)),StringEscapeUtils.escapeHtml4(rs.getString(9)), rs.getInt(10), rs.getInt(20), rs.getDouble(44), rs.getDouble(45)));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		return listLicence;
	}

}
	
