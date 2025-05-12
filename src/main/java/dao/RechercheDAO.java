package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.*;
import utils.DataBaseCon;

public class RechercheDAO {
	
	public static ArrayList<Licence> rechercheZoneGeo(String zoneGeo,String zoneGeoType,int offset) {
		
		ArrayList<Licence> listLicence = new ArrayList<>();
		String sql;

		if(zoneGeoType.equals("region")) 
		{
			sql = "SELECT l.*, cp.code_postal, cp.latitude, cp.longitude FROM Licences l LEFT JOIN code_postaux cp ON l.code_commune = cp.code_insee WHERE l.region = ? LIMIT 1000 OFFSET ?;";
		}
		else
		{
			sql = "SELECT l.*, cp.code_postal, cp.latitude, cp.longitude FROM Licences l LEFT JOIN code_postaux cp ON l.code_commune = cp.code_insee WHERE l.departement = ? LIMIT 1000 OFFSET ?;";
		}
		
		try (Connection con = DataBaseCon.getConnection();
	             PreparedStatement stmt = con.prepareStatement(sql)) {

	            stmt.setString(1, zoneGeo);
	            stmt.setInt(2, offset);

	            ResultSet rs = stmt.executeQuery();

	            while(rs.next())
	            {
	            	listLicence.add(new Licence(rs.getString(43), rs.getString(3), rs.getString(7), rs.getString(6),rs.getString(9), rs.getInt(10), rs.getInt(20), rs.getDouble(44), rs.getDouble(45)));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		return listLicence;
	}

}
