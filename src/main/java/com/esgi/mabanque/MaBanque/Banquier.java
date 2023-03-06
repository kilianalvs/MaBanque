package com.esgi.mabanque.MaBanque;

import com.esgi.mabanque.MaBanque.exception.GlobalException;
import exception.GlobalException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Banquier extends User {
    public static int DELAI_AVANT_BANQUIER_CONTACTE_CLIENT = 90;

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

    public List<Client> clientsAContacter() throws GlobalException {
        if (LocalDate.now().minusDays(DELAI_AVANT_BANQUIER_CONTACTE_CLIENT).isBefore(this.dateArrivee)) {
            throw new GlobalException("Veuillez valider votre période d'essai avant de contacter des clients");
        }

        List<Client> clientAContacter = new ArrayList<>(this.clients.size());

        for (Client client: this.clients) {
            for (CompteBancaire compteBancaire: client.getCompteBancaire()) {
                if (!compteBancaire.estValide()) {
                    clientAContacter.add(client);
                    break;
                }
            }
        }

        return clientAContacter;
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
