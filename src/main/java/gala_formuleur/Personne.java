package gala_formuleur;

import jakarta.persistence.*;

@Entity
@Table(name = "personne")
public class Personne {
    @Id
    private String nom; // Devenu la clé primaire

    private Boolean aPaye;

    public Personne() {}

    public Personne(String nom, Boolean aPaye) {
        this.nom = nom;
        this.aPaye = aPaye;
    }

    // Supprimer tous les getters/setters pour 'id'
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getaPaye() {
        return aPaye;
    }

    public void setaPaye(Boolean aPaye) {
        this.aPaye = aPaye;
    }

    public Boolean getAPaye() {
        return aPaye;
    }
}