package com.esgi.mabanque.MaBanque;

import com.esgi.mabanque.MaBanque.exception.CreditException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CompteBancaireTest {

    private CompteBancaire myCompte = new CompteBancaire();

    @Test
    public void testCompteBancaire() {

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
    public void testCompteEstValideAtMIN() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(0);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        assertTrue(compte.estValide());
    }

    @Test
    public void testCompteEstValideAtMAX() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(1000);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        assertTrue(compte.estValide());
    }

    @Test
    public void testCompteEstValideInRange() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        assertTrue(compte.estValide());
    }

    @Test
    public void testCompteEstInvalideInfMIN() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(-1);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        assertFalse(compte.estValide());
    }

    @Test
    public void testCompteEstInvalideSupMAX() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(1001);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        assertFalse(compte.estValide());
    }

    @Test
    public void testCompteCredit() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        CompteRenduOperation compteRendu = compte.credit(100);
        assertEquals(compteRendu.getMontantCredite(), 100);
        assertEquals(compteRendu.getMontantDebite(), 0);
        assertEquals(compteRendu.getNouveauSolde(), 600);

    }

    @Test
    public void testCompteCreditInvalide() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        CompteRenduOperation compteRendu = compte.credit(0);

        fail();

    }

    @Test
    public void testCompteCreditInvalideCompte() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(-1);
        CompteRenduOperation compteRendu = compte.credit(500);

        fail();

    }

    @Test
    public void testCompteDebit() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        CompteRenduOperation compteRendu = compte.debit(100);
        assertEquals(compteRendu.getMontantCredite(), 0);
        assertEquals(compteRendu.getMontantDebite(), 100);
        assertEquals(compteRendu.getNouveauSolde(), 400);

    }

    @Test
    public void testCompteDebitInvalide() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        CompteRenduOperation compteRendu = compte.debit(0);

        fail();

    }

    @Test
    public void testCompteDebitInvalideCompte() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(-1);
        CompteRenduOperation compteRendu = compte.debit(500);

        fail();

    }



}
