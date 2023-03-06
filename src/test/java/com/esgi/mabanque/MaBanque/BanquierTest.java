package com.esgi.mabanque.MaBanque;

import com.esgi.mabanque.MaBanque.exception.CreditException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CompteBancaireTest {

    private CompteBancaire compteBancaire;

    @BeforeEach
    public void setUp() {
        this.compteBancaire = new CompteBancaire();
    }

    @Test
    public void testCreditCompteBancaire() throws CreditException {
        int montantACrediter = 500;
        CompteRenduOperation compteRenduOperation = this.compteBancaire.credit(montantACrediter);

        int expectedNouveauSolde = 500;
        int expectedMontantCredite = 500;
        int expectedMontantNonCredite = 0;

        Assertions.assertEquals(expectedNouveauSolde, compteRenduOperation.getNouveauSolde());
        Assertions.assertEquals(expectedMontantCredite, compteRenduOperation.getMontantCredite());
        Assertions.assertEquals(expectedMontantNonCredite, compteRenduOperation.getMontantNonCredite());
        Assertions.assertNotNull(compteRenduOperation.getDateHeureOperation());
    }

    @Test
    public void testDebitCompteBancaire() throws CreditException {
        int montantACrediter = 500;
        this.compteBancaire.credit(montantACrediter);

        int montantADebiter = 300;
        CompteRenduOperation compteRenduOperation = this.compteBancaire.debit(montantADebiter);

        int expectedNouveauSolde = 200;
        int expectedMontantDebite = 300;
        int expectedMontantNonDebite = 0;

        Assertions.assertEquals(expectedNouveauSolde, compteRenduOperation.getNouveauSolde());
        Assertions.assertEquals(expectedMontantDebite, compteRenduOperation.getMontantDebite());
        Assertions.assertEquals(expectedMontantNonDebite, compteRenduOperation.getMontantNonDebite());
        Assertions.assertNotNull(compteRenduOperation.getDateHeureOperation());
    }

    @Test
    public void testCreditCompteBancaireAvecMontantNegatif() {
        int montantACrediter = -500;

        CreditException exception = Assertions.assertThrows(CreditException.class, () -> {
            this.compteBancaire.credit(montantACrediter);
        });

        String expectedMessage = "Mauvais montant";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCreditCompteBancaireMontantMaxAtteint() throws CreditException {
        int montantACrediter = 1000;
        this.compteBancaire.credit(montantACrediter);

        int montantACrediter2 = 500;
        CompteRenduOperation compteRenduOperation = this.compteBancaire.credit(montantACrediter2);

        int expectedNouveauSolde = 1000;
        int expectedMontantCredite = 0;
        int expectedMontantNonCredite = 500;

        Assertions.assertEquals(expectedNouveauSolde, compteRenduOperation.getNouveauSolde());
        Assertions.assertEquals(expectedMontantCredite, compteRenduOperation.getMontantCredite());
        Assertions.assertEquals(expectedMontantNonCredite, compteRenduOperation.getMontantNonCredite());
    }
}