package model;

public class Utilisateur {
    private int id;
    private String nom;
    private String email;
    private String motDePasse;
    private boolean valide;
    private boolean elu;
    private boolean acteurSportif;
    private String cheminPieceJustificative;
    private String role;

    // Constructeur complet (lecture depuis la base)
    public Utilisateur(int id, String nom, String email, String motDePasse, boolean valide, boolean elu, boolean acteurSportif, String cheminPieceJustificative, String role) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.valide = valide;
        this.elu = elu;
        this.acteurSportif = acteurSportif;
        this.cheminPieceJustificative = cheminPieceJustificative;
        this.role = role;
    }

    // Constructeur pour ajout AVANT l’insertion en base (sans id)
    public Utilisateur(String nom, String email, String motDePasse, boolean valide, boolean elu, boolean acteurSportif, String cheminPieceJustificative, String role) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.valide = valide;
        this.elu = elu;
        this.acteurSportif = acteurSportif;
        this.cheminPieceJustificative = cheminPieceJustificative;
        this.role = role;
    }

    // Getters & setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }
    public boolean isValide() { return valide; }
    public boolean isElu() { return elu; }
    public boolean isActeurSportif() { return acteurSportif; }
    public String getCheminPieceJustificative() { return cheminPieceJustificative; }
    public String getRole() { return role; }
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setEmail(String email) { this.email = email; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public void setValide(boolean valide) { this.valide = valide; }
    public void setElu(boolean elu) { this.elu = elu; }
    public void setActeurSportif(boolean acteurSportif) { this.acteurSportif = acteurSportif; }
    public void setCheminPieceJustificative(String cheminPieceJustificative) { this.cheminPieceJustificative = cheminPieceJustificative; }
    public void setRole(String role) { this.role = role; }
}
