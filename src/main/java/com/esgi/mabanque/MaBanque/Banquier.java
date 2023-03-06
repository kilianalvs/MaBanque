package com.esgi.mabanque.MaBanque;


import java.time.LocalDate;
import java.util.List;

public class Banquier extends User {

    private LocalDate dateArrivee;
    private List<Client> clients;

    public Banquier(String email,
                    String prenom,
                    String nom,
                    LocalDate dateDeNaissance,
                    LocalDate dateArrivee,
                    List<Client> clients) {
        super(email, prenom, nom, dateDeNaissance);
        this.dateArrivee = dateArrivee;
        this.clients = clients;
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

    public LocalDate getDateArrivee() {
        return this.dateArrivee;
    }

    public void setDateArrivee(LocalDate dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
