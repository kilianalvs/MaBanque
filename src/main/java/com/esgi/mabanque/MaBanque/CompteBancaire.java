package com.esgi.mabanque.MaBanque;


import com.esgi.mabanque.MaBanque.exception.CreditException;

import java.time.LocalDateTime;

public class CompteBancaire {
    public static int MAX = 1000;
    public static int MIN = 0;

    private int id;
    private int montant;
    private LocalDateTime dateHeureDerniereMAJ;

    public CompteBancaire() {
        this.montant = 0;
        this.dateHeureDerniereMAJ = null;
    }

    public CompteRenduOperation credit(int montantACrediter) throws CreditException {
        if (montantACrediter <= 0) {
            throw new CreditException("Mauvais montant");
        } else if (!this.estValide()) {
            throw new CreditException("Le compte bancaire n'est pas dans un état correct");
        }

        int nouveauSolde = Math.min(this.montant + montantACrediter, MAX);
        int montantCredite = montantACrediter + this.montant <= MAX ? montantACrediter : nouveauSolde - this.montant;

        this.save(nouveauSolde);

        CompteRenduOperation.CompteRenduOperationBuilder builder = new CompteRenduOperation.CompteRenduOperationBuilder(nouveauSolde);
        builder.montantCredite(montantCredite);
        builder.montantNonCredite(montantACrediter - montantCredite);

        return builder.build();
    }

    public CompteRenduOperation debit(int montantADebiter) throws CreditException {
        if (montantADebiter <= 0) {
            throw new CreditException("Mauvais montant");
        } else if (!this.estValide()) {
            throw new CreditException("Le compte bancaire n'est pas dans un état correct");
        }

        int nouveauSolde = Math.max(this.montant - montantADebiter, MIN);
        int montantDebite = this.montant - montantADebiter >= MIN ? montantADebiter : this.montant;

        this.save(nouveauSolde);

        CompteRenduOperation.CompteRenduOperationBuilder builder = new CompteRenduOperation.CompteRenduOperationBuilder(nouveauSolde);
        builder.montantDebite(montantDebite);
        builder.montantNonDebite(montantADebiter - montantDebite);

        return builder.build();
    }

    protected boolean estValide() {
        return this.montant >= MIN && this.montant <= MAX;
    }

    public void save(int montantAEnregistrer) {
        // SAVE montantAEnregistrer et dateHeureDerniereMAJ en Base de Données
    }

    /***********************/
    /* GETTERS AND SETTERS */

    /***********************/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMontant() {
        return this.montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public LocalDateTime getDateHeureDerniereMAJ() {
        return this.dateHeureDerniereMAJ;
    }

    public void setDateHeureDerniereMAJ(LocalDateTime dateHeureDerniereMAJ) {
        this.dateHeureDerniereMAJ = dateHeureDerniereMAJ;
    }
}
