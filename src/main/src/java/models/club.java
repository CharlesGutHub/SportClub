package models;

public class club {
	
	private String codeCommune;
	private String commune;
	private String Region;
	private String codeFederation;
	private String nomFederation;
	
	private int totalClub2022;
	private int clubsSportif2022;
	private int establissementProf2022;
	
	
	
	
	public club(String codeCommune, String commune, String region, String codeFederation, String nomFederation,
			int totalClub2022, int clubsSportif2022, int establissementProf2022) {
		this.codeCommune = codeCommune;
		this.commune = commune;
		Region = region;
		this.codeFederation = codeFederation;
		this.nomFederation = nomFederation;
		this.totalClub2022 = totalClub2022;
		this.clubsSportif2022 = clubsSportif2022;
		this.establissementProf2022 = establissementProf2022;
	}
	
	
	public String getCodeCommune() {
		return codeCommune;
	}
	public String getCommune() {
		return commune;
	}
	public String getRegion() {
		return Region;
	}
	public String getCodeFederation() {
		return codeFederation;
	}
	public String getNomFederation() {
		return nomFederation;
	}
	public int getTotalClub2022() {
		return totalClub2022;
	}
	public int getClubsSportif2022() {
		return clubsSportif2022;
	}
	public int getEstablissementProf2022() {
		return establissementProf2022;
	}

}
