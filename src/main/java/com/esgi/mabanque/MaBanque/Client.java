package com.esgi.mabanque.MaBanque;

import exception.GlobalException;

import java.time.LocalDate;
import java.util.List;

public class Client extends User {
    private List<CompteBancaire> compteBancaire;
    private boolean interditBancaire;

    public Client(String email,
                  String prenom,
                  String nom,
                  LocalDate dateDeNaissance,
                  List<CompteBancaire> compteBancaire,
                  boolean interditBancaire) {
        super(email, prenom, nom, dateDeNaissance);
        this.compteBancaire = compteBancaire;
        this.interditBancaire = interditBancaire;
    }

    public boolean verifierSiCompteValide(int compteBancaireId) throws GlobalException {
        if (this.interditBancaire) {
            return false;
        }

        CompteBancaire leCompteBancaire = this.compteBancaire
                .stream()
                .filter(c -> c.getId() == compteBancaireId)
                .findFirst().orElseThrow(() -> new GlobalException("Compte non trouvé"));

        return leCompteBancaire != null && leCompteBancaire.estValide();
    }

    /***********************/
    /* GETTERS AND SETTERS */
    /***********************/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDeNaissance() {
        return this.dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public List<CompteBancaire> getCompteBancaire() {
        return this.compteBancaire;
    }

    public void setCompteBancaire(List<CompteBancaire> compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    public boolean isInterditBancaire() {
        return interditBancaire;
    }

    public void setInterditBancaire(boolean interditBancaire) {
        this.interditBancaire = interditBancaire;
    }
}
