package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.Commune;
import utils.DataBaseCon;
import org.apache.commons.text.StringEscapeUtils;

public class CodePostauxDAO {
	
	public static ArrayList<Commune> rechercheCommune(String recherche)
	{
		ArrayList<Commune> listLicence = new ArrayList<>();
		String sql;
		
		sql = "SELECT nom_commune ,code_postal,latitude, longitude FROM code_postaux WHERE nom_commune LIKE ? ORDER BY nom_commune LIMIT 5;";
		
		try (Connection con = DataBaseCon.getConnection();
	             PreparedStatement stmt = con.prepareStatement(sql)) {

	            stmt.setString(1, recherche+"%");
	            
	            ResultSet rs = stmt.executeQuery();

	            while(rs.next())
	            {
	            	listLicence.add(new Commune(StringEscapeUtils.escapeHtml4(rs.getString(1)),StringEscapeUtils.escapeHtml4(rs.getString(2)),rs.getDouble(3),rs.getDouble(4)));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		
		return listLicence;
	}

}
