package models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Annonce {
    private int id;
    private String message;
    private LocalDateTime dateCreation;
    private LocalDateTime dateExpiration;

    // Constructeurs
    public Annonce() {}
    
    public Annonce(String message, LocalDateTime dateExpiration) {
        this.message = message;
        this.dateExpiration = dateExpiration;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
    
    public LocalDateTime getDateExpiration() { return dateExpiration; }
    public void setDateExpiration(LocalDateTime dateExpiration) { this.dateExpiration = dateExpiration; }
    
    // Méthodes pour conversion JSP
    public Date getDateCreationAsDate() {
        return Date.from(dateCreation.atZone(ZoneId.systemDefault()).toInstant());
    }
    
    public Date getDateExpirationAsDate() {
        return Date.from(dateExpiration.atZone(ZoneId.systemDefault()).toInstant());
    }
}