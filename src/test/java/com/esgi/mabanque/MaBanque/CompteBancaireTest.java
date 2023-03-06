import exception.CreditException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class CompteBancaireTest {

    private CompteBancaire myCompte = new CompteBancaire();

    @Test
    public void testCompteBancaire() {

        CompteBancaire compte = this.myCompte;
        Assert.assertEquals(compte.getMontant(), 0);
        Assert.assertNull(compte.getDateHeureDerniereMAJ());

        compte.setId(1);
        Assert.assertEquals(compte.getId(), 1);

        compte.setMontant(100);
        Assert.assertEquals(compte.getMontant(), 100);

        LocalDateTime dateHeureDerniereMAJ = LocalDateTime.now();
        compte.setDateHeureDerniereMAJ(dateHeureDerniereMAJ);
        Assert.assertEquals(compte.getDateHeureDerniereMAJ(), dateHeureDerniereMAJ);
    }

    @Test
    public void testCompteEstValideAtMIN() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(0);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        Assert.assertTrue(compte.estValide());
    }

    @Test
    public void testCompteEstValideAtMAX() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(1000);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        Assert.assertTrue(compte.estValide());
    }

    @Test
    public void testCompteEstValideInRange() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        Assert.assertTrue(compte.estValide());
    }

    @Test
    public void testCompteEstInvalideInfMIN() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(-1);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        Assert.assertFalse(compte.estValide());
    }

    @Test
    public void testCompteEstInvalideSupMAX() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(1001);
        compte.setDateHeureDerniereMAJ(LocalDateTime.now());

        Assert.assertFalse(compte.estValide());
    }

    @Test
    public void testCompteCredit() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        try {
            CompteRenduOperation compteRendu = compte.credit(100);
            Assert.assertEquals(compteRendu.getMontantCredite(), 100);
            Assert.assertEquals(compteRendu.getMontantDebite(), 0);
            Assert.assertEquals(compteRendu.getNouveauSolde(), 600);
        }catch (CreditException e) {
            Assert.fail();
        }

    }

    @Test
    public void testCompteCreditInvalide() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        try {
            CompteRenduOperation compteRendu = compte.credit(0);

            Assert.fail();
        }catch (CreditException e) {
            Assert.assertEquals(e.getMessage(), "Mauvais montant");
        }

    }

    @Test
    public void testCompteCreditInvalideCompte() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(-1);
        try {
            CompteRenduOperation compteRendu = compte.credit(500);

            Assert.fail();
        }catch (CreditException e) {
            Assert.assertEquals(e.getMessage(), "Le compte bancaire n'est pas dans un état correct");
        }

    }

    @Test
    public void testCompteDebit() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        try {
            CompteRenduOperation compteRendu = compte.debit(100);
            Assert.assertEquals(compteRendu.getMontantCredite(), 0);
            Assert.assertEquals(compteRendu.getMontantDebite(), 100);
            Assert.assertEquals(compteRendu.getNouveauSolde(), 400);
        }catch (CreditException e) {
            Assert.fail();
        }

    }

    @Test
    public void testCompteDebitInvalide() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(500);
        try {
            CompteRenduOperation compteRendu = compte.debit(0);

            Assert.fail();
        }catch (CreditException e) {
            Assert.assertEquals(e.getMessage(), "Mauvais montant");
        }

    }

    @Test
    public void testCompteDebitInvalideCompte() {
        CompteBancaire compte = this.myCompte;
        compte.setMontant(-1);
        try {
            CompteRenduOperation compteRendu = compte.debit(500);

            Assert.fail();
        }catch (CreditException e) {
            Assert.assertEquals(e.getMessage(), "Le compte bancaire n'est pas dans un état correct");
        }

    }



}
