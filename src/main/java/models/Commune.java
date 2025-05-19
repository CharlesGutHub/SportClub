package models;

public class Commune {
	private String nom;
	private String codePostale;
	private double latitude;
	private double longitude;
	
	
	public Commune(String nom, String codePostale, double latitude, double longitude) {
		this.nom = nom;
		this.codePostale = codePostale;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getCodePostale() {
		return codePostale;
	}


	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	

}
