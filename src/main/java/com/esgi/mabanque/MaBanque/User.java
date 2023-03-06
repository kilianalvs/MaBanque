package com.esgi.mabanque.MaBanque;

import java.time.LocalDate;

abstract class User {
    protected String email;
    protected String prenom;
    protected String nom;
    protected LocalDate dateDeNaissance;

    public User(String email, String prenom, String nom, LocalDate dateDeNaissance) {
        this.email = email;
        this.prenom = prenom;
        this.nom = nom;
        this.dateDeNaissance = dateDeNaissance;
    }
}
