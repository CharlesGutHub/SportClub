package dataImporter;

import utils.ConfigLoader;
import utils.DataBaseCon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CSVImporter {
	
	public static void modifGeo() {
	    String csvPath = ConfigLoader.getProperty("csvpostalcode.path");
	    String separator = ConfigLoader.getProperty("csvpostalcode.separator");

	    if (separator == null || separator.isEmpty()) {
	        separator = ";"; // Valeur par défaut
	    }

	    String sql = "UPDATE code_postaux "
	               + "SET longitude = ?, latitude = ? "
	               + "WHERE code_insee = ? AND code_postal = ?";

	    try (BufferedReader br = new BufferedReader(new FileReader(csvPath));
	         Connection con = DataBaseCon.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        String line;
	        br.readLine(); // Ignorer l'en-tête

	        while ((line = br.readLine()) != null) {
	            String[] data = line.split(separator, -1);
	            for (int i = 0; i < data.length; i++) {
	                data[i] = data[i].replace("\"", "").trim();
	            }

	            String geoPoint = safeGet(data, 9); // colonne geo_point_2d

	            if (geoPoint.contains(",")) {
	                String[] coords = geoPoint.split(",");
	                double latitude = Double.parseDouble(coords[0].trim());
	                double longitude = Double.parseDouble(coords[1].trim());

	                pstmt.setDouble(1, longitude);
	                pstmt.setDouble(2, latitude);
	                pstmt.setString(3, safeGet(data, 0)); // code_insee
	                pstmt.setString(4, safeGet(data, 1)); // code_postal

	                pstmt.executeUpdate();
	            }
	        }

	        System.out.println("Import terminé avec succès de la liste de codes postaux !");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	
	
	public static void importCodePostaux()
	{
		String csvPath = ConfigLoader.getProperty("csvpostalcode.path");
		String separator = ConfigLoader.getProperty("csvpostalcode.separator");
		
		if (separator == null || separator.isEmpty()) {
            separator = ";"; // Par défaut
        }
		
		String sql = "INSERT IGNORE INTO code_postaux (code_insee,code_postal) VALUES (?,?)";
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvPath));
	             Connection con = DataBaseCon.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql)) {

	            String line;
	            br.readLine(); // Ignorer l'en-tête

	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(separator, -1);
	                for (int i = 0; i < data.length; i++) {
	                    data[i] = data[i].replace("\"", "").trim();
	                }

	                pstmt.setString(1, safeGet(data, 0)); // code_fed
	                pstmt.setString(2, safeGet(data, 2)); // code_postal
	                pstmt.executeUpdate();
	            }

	            System.out.println("Import terminé avec succès de la liste de code postaux!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	public static void importListeFed()
	{
		String csvPath = ConfigLoader.getProperty("csvlistfeddata.path");
		String separator = ConfigLoader.getProperty("csvlistfeddata.separator");
		
		if (separator == null || separator.isEmpty()) {
            separator = ";"; // Par défaut
        }
		
		String sql = "INSERT INTO list_fed (code_fed,nom_fed) VALUES (?,?)";
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvPath));
	             Connection con = DataBaseCon.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql)) {

	            String line;
	            br.readLine(); // Ignorer l'en-tête

	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(separator, -1);
	                for (int i = 0; i < data.length; i++) {
	                    data[i] = data[i].replace("\"", "").trim();
	                }

	                pstmt.setString(1, safeGet(data, 1)); // code_fed
	                pstmt.setString(2, safeGet(data, 0)); // nom_fed
	                pstmt.executeUpdate();
	            }

	            System.out.println("Import terminé avec succès de la liste de fed!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
    public static void importDataLicence() {
        String csvPath = ConfigLoader.getProperty("csvlicensedata.path");
        String separator = ConfigLoader.getProperty("csvlicensedata.separator");

        if (separator == null || separator.isEmpty()) {
            separator = ";"; // Par défaut
        }

        String sql = """
            INSERT INTO licences (
                code_commune, libelle, code_qpv, nom_qpv, departement, region, fed_2022, nom_fed,
                l_2022, l_0_4_2022, l_5_9_2022, l_10_14_2022, l_15_19_2022, l_20_29_2022,
                l_30_44_2022, l_45_59_2022, l_60_74_2022, l_75_2022,
                l_f_2022, l_0_4_f_2022, l_5_9_f_2022, l_10_14_f_2022, l_15_19_f_2022, l_20_29_f_2022,
                l_30_44_f_2022, l_45_59_f_2022, l_60_74_f_2022, l_75_f_2022,
                l_h_2022, l_0_4_h_2022, l_5_9_h_2022, l_10_14_h_2022, l_15_19_h_2022, l_20_29_h_2022,
                l_30_44_h_2022, l_45_59_h_2022, l_60_74_h_2022, l_75_h_2022,
                l_qp_2022, l_qp_f_2022, l_qp_h_2022
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath));
             Connection con = DataBaseCon.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            String line;
            br.readLine(); // Ignorer l'en-tête

            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator, -1);
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "").trim();
                }

                pstmt.setString(1, safeGet(data, 0)); // code_commune
                pstmt.setString(2, safeGet(data, 1)); // libelle
                pstmt.setString(3,safeGet(data,2));	  //Code QPV
                pstmt.setString(4,safeGet(data,3));	  //Nom QPV
                pstmt.setString(5, safeGet(data,4));  // departement
                
                
                pstmt.setString(6, safeGet(data, 5)); // region
                pstmt.setString(7, safeGet(data, 7)); // fed_2022
                pstmt.setString(8, safeGet(data, 8)); // nom_fed

                pstmt.setInt(9, safeParseInt(data, 46)); // total licences
                pstmt.setInt(10, safeParseInt(data, 9) + safeParseInt(data, 24));  // 1-4 ans
                pstmt.setInt(11, safeParseInt(data, 10) + safeParseInt(data, 25)); // 5-9 ans
                pstmt.setInt(12, safeParseInt(data, 11) + safeParseInt(data, 26)); // 10-14 ans
                pstmt.setInt(13, safeParseInt(data, 12) +safeParseInt(data, 27)); //  15-19 ans
                pstmt.setInt(14, safeParseInt(data, 13) + safeParseInt(data, 14) + safeParseInt(data, 28) + safeParseInt(data, 29)); //  20-29 ans
                pstmt.setInt(15, safeParseInt(data, 15) + safeParseInt(data, 16) + safeParseInt(data, 30) + safeParseInt(data, 31)); // 30-44 ans
                pstmt.setInt(16, safeParseInt(data, 17) + safeParseInt(data, 18) + safeParseInt(data, 32) + safeParseInt(data, 33)); // 45-59 ans
                pstmt.setInt(17, safeParseInt(data, 19) + safeParseInt(data, 20) + safeParseInt(data, 34) + safeParseInt(data, 35)); //60-74 ans
                pstmt.setInt(18, safeParseInt(data, 21) + safeParseInt(data, 22) + safeParseInt(data, 36) + safeParseInt(data, 37)); // 75+ ans

                pstmt.setInt(19, sumFemales(data)); // total licenciées femme
                pstmt.setInt(20, safeParseInt(data, 9));  // F 1-4 ans
                pstmt.setInt(21, safeParseInt(data, 10)); // F 5-9 ans
                pstmt.setInt(22, safeParseInt(data, 11)); // F 10-14 ans
                pstmt.setInt(23, safeParseInt(data, 12)); // F 15-19 ans
                pstmt.setInt(24, safeParseInt(data, 13) + safeParseInt(data, 14)); // F 20-29 ans
                pstmt.setInt(25, safeParseInt(data, 15) + safeParseInt(data, 16)); // F 30-44 ans
                pstmt.setInt(26, safeParseInt(data, 17) + safeParseInt(data, 18)); // F 45-59 ans
                pstmt.setInt(27, safeParseInt(data, 19) + safeParseInt(data, 20)); // F 60-74 ans
                pstmt.setInt(28, safeParseInt(data, 21) + safeParseInt(data, 22)); // F 75+

                pstmt.setInt(29, safeParseInt(data, 48)); // H total
                pstmt.setInt(30, safeParseInt(data, 24)); // H 1-4 ans
                pstmt.setInt(31, safeParseInt(data, 25)); // H 5-9 ans
                pstmt.setInt(32, safeParseInt(data, 26)); // H 10-14 ans
                pstmt.setInt(33, safeParseInt(data, 27)); // H 15-19 ans
                pstmt.setInt(34, safeParseInt(data, 28) + safeParseInt(data, 29)); // H 20-29 ans
                pstmt.setInt(35, safeParseInt(data, 30) + safeParseInt(data, 31)); // H 30-44 ans
                pstmt.setInt(36, safeParseInt(data, 32) + safeParseInt(data, 33)); // H 45-59 ans
                pstmt.setInt(37, safeParseInt(data, 34) + safeParseInt(data, 35)); // H 60-74 ans
                pstmt.setInt(38, safeParseInt(data, 36) + safeParseInt(data, 37)); // H 75+

                pstmt.setInt(39, safeParseInt(data, 50)); // QP total
                pstmt.setInt(40, safeParseInt(data, 51)); // QP femme
                pstmt.setInt(41, safeParseInt(data, 52)); // QP homme

                pstmt.executeUpdate();
            }

            System.out.println("Import terminé avec succès de la table licences !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void importDataClub() {
        String csvPath = ConfigLoader.getProperty("csvclubsdata.path");
        String separator = ConfigLoader.getProperty("csvclubsdata.separator");

        if (separator == null || separator.isEmpty()) {
            separator = ";"; // Par défaut
        }

        String sql = """
        		INSERT INTO clubs (
        		    code_commune, libelle, region, fed_2022, nom_fed, 
        		    total_clubs_2022, clubs_sportifs_2022, etablissement_prof_2022
        		) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        		""";

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath));
             Connection con = DataBaseCon.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            String line;
            br.readLine(); // Ignorer l'en-tête

            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator, -1);
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "").trim();
                }

                pstmt.setString(1, safeGet(data, 0)); // code_commune
                pstmt.setString(2, safeGet(data, 1)); // libelle
                pstmt.setString(3, safeGet(data, 5)); // region
                pstmt.setString(4, safeGet(data, 7)); // fed_2022
                pstmt.setString(5, safeGet(data, 8)); // nom_fed
                
                pstmt.setInt(6, safeParseInt(data, 11)); // total club et EPA
                pstmt.setInt(7, safeParseInt(data, 9));  // club
                pstmt.setInt(8, safeParseInt(data, 10)); // EPA

                

                pstmt.executeUpdate();
            }

            System.out.println("Import terminé avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String safeGet(String[] data, int index) {
        if (data.length > index && !data[index].isEmpty()) {
            String value = data[index].trim();
            if (index == 0) { // Correction spécifique pour code_commune uniquement
                if (value.startsWith("NR")) {
                    return "NR";
                }
                // Sécuriser aussi : si jamais une valeur normale dépasse 5 caractères
                if (value.length() > 5) {
                    return value.substring(0, 5); // On garde que les 5 premiers caractères
                }
            }
            
            
            return value;
        }
        return null;
    }

    private static int safeParseInt(String[] data, int index) {
        try {
            return (data.length > index && !data[index].isEmpty()) ? Integer.parseInt(data[index]) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static int sumFemales(String[] data) {
        int sum = 0;
        for (int i = 9; i <= 23; i++) {
            sum += safeParseInt(data, i);
        }
        return sum;
    }
}
