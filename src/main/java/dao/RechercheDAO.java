package dao;

import java.util.ArrayList;

import models.*;

public class RechercheDAO {
	
	public ArrayList<Licence> rechercheZoneGeo(String zoneGeo,String zoneGeoType) {
		
		ArrayList<Licence> listLicence = new ArrayList<>();
		String sql;

		if(zoneGeoType.equals("region")) 
		{
			sql = "SELECT * FROM Licence WHERE region = ?";
		}
		else
		{
			sql = "SELECT * FROM Licence WHERE departement = ?";
		}
		
		return listLicence;
	}

}
