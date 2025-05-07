package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.*;
import utils.DataBaseCon;

public class RechercheDAO {
	
	public static ArrayList<Licence> rechercheZoneGeo(String zoneGeo,String zoneGeoType) {
		
		ArrayList<Licence> listLicence = new ArrayList<>();
		String sql;

		if(zoneGeoType.equals("region")) 
		{
			sql = "SELECT * FROM Licences WHERE region = ?";
		}
		else
		{
			sql = "SELECT * FROM Licences WHERE departement = ?";
		}
		
		try (Connection con = DataBaseCon.getConnection();
	             PreparedStatement stmt = con.prepareStatement(sql)) {

	            stmt.setString(1, zoneGeo);

	            ResultSet rs = stmt.executeQuery();

	            while(rs.next())
	            {
	            	listLicence.add(new Licence(rs.getString(2), rs.getString(3), rs.getString(7), rs.getString(6),rs.getString(9), rs.getInt(10), rs.getInt(20)));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		return listLicence;
	}

}
