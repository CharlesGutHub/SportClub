package models;

public class User {
	private String nom;
	private String prenom;
	private String mail;
	private String role;
	private int etatInscirption;
	public User(String nom, String prenom, String mail, String role, int etatInscirption) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.role = role;
		this.etatInscirption = etatInscirption;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getEtatInscirption() {
		return etatInscirption;
	}
	public void setEtatInscirption(int etatInscirption) {
		this.etatInscirption = etatInscirption;
	}

	
}
