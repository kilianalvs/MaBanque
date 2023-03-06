package com.esgi.mabanque.MaBanque;

import com.esgi.mabanque.MaBanque.exception.CreditException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CompteBancaireTest {

    private CompteBancaire myCompte = new CompteBancaire();


    private CompteBancaire getMockCompteBancaire(){
        CompteBancaire compteBancaire = new CompteBancaire();
        compteBancaire.setMontant(500);
        compteBancaire.setId(123);
        compteBancaire.setDateHeureDerniereMAJ(LocalDateTime.now());
        return compteBancaire;
    }

    @Test
    void testCompteBancaire() {

        CompteBancaire compte = this.myCompte;
        assertEquals(compte.getMontant(), 0);
        assertNull(compte.getDateHeureDerniereMAJ());

        compte.setId(1);
        assertEquals(compte.getId(), 1);

        compte.setMontant(100);
        assertEquals(compte.getMontant(), 100);

        LocalDateTime dateHeureDerniereMAJ = LocalDateTime.now();
        compte.setDateHeureDerniereMAJ(dateHeureDerniereMAJ);
        assertEquals(compte.getDateHeureDerniereMAJ(), dateHeureDerniereMAJ);
    }

    @Test
    void testCompteEstValideAtMIN() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(0);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        assertTrue(compte.estValide());
    }

    @Test
    void testCompteEstValideAtMAX() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(1000);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        assertTrue(compte.estValide());
    }

    @Test
    void testCompteEstValideInRange() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        assertTrue(compte.estValide());
    }

    @Test
    void testCompteEstInvalideInfMIN() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(-1);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        assertFalse(compte.estValide());
    }

    @Test
    void testCompteEstInvalideSupMAX() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(1001);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        assertFalse(compte.estValide());
    }

    @Test
    void testCompteCredit() throws CreditException {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        CompteRenduOperation compteRendu = compte.credit(100);
        assertEquals(compteRendu.getMontantCredite(), 100);
        assertEquals(compteRendu.getMontantDebite(), 0);
        assertEquals(compteRendu.getNouveauSolde(), 600);

    }

    @Test
    void testCompteCreditInvalide() throws CreditException {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);

        assertThrows(CreditException.class, ()-> compte.credit(0));

    }

    @Test
    void testCompteCreditInvalideCompte() throws CreditException {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(-1);

        assertThrows(CreditException.class, ()-> compte.credit(500));

    }

    @Test
    void testCompteDebit() throws CreditException {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        CompteRenduOperation compteRendu = compte.debit(100);
        assertEquals(compteRendu.getMontantCredite(), 0);
        assertEquals(compteRendu.getMontantDebite(), 100);
        assertEquals(compteRendu.getNouveauSolde(), 400);

    }

    @Test
    void testCompteDebitInvalide() throws CreditException {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);

        assertThrows(CreditException.class, ()-> compte.debit(0));

    }

    @Test
    void testCompteDebitInvalideCompte() throws CreditException {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(-1);

        assertThrows(CreditException.class, ()-> compte.debit(500));

    }

    @Test
    void debit_compte_with_solde_1000_should_return_exception() throws CreditException {
        CompteBancaire compteBancaire = getMockCompteBancaire();
        compteBancaire.setMontant(1000);
        compteBancaire.credit(100);
        assertEquals(1000, compteBancaire.getMontant());
    }




}
