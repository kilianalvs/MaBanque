import exception.GlobalException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClientTest {
    private Client myClient = new Client("Dupont@email.com", "Jean", "Dupont",
            LocalDate.of(2001, 8, 1),
            new ArrayList<CompteBancaire>(), false);

    @Test
    public void testClient() {

        Client client = this.myClient;

        Assert.assertEquals(client.getCompteBancaire(), new ArrayList<CompteBancaire>());
        Assert.assertEquals(client.getEmail(), "Dupont@email.com");
        Assert.assertEquals(client.getNom(), "Dupont");
        Assert.assertEquals(client.getPrenom(), "Jean");
        Assert.assertEquals(client.getDateDeNaissance(), LocalDate.of(2001, 8, 1));
        Assert.assertFalse(client.isInterditBancaire());
    }

    @Test
    public void testVerifierSiInterditBanquaireFalse() {
        Client client = this.myClient;
        CompteBancaire compte = new CompteBancaire();
        compte.setId(1);
        client.getCompteBancaire().add(compte);
        client.setInterditBancaire(false);

        try {
            Assert.assertTrue(client.verifierSiCompteValide(1));
        }catch (GlobalException e){
            Assert.fail();
        }
    }

    @Test
    public void testVerifierSiInterditBanquaireTrue() {
        Client client = this.myClient;
        CompteBancaire compte = new CompteBancaire();
        compte.setId(1);
        client.getCompteBancaire().add(compte);
        client.setInterditBancaire(true);

        try {
            Assert.assertFalse(client.verifierSiCompteValide(1));
        }catch (GlobalException e){
            Assert.fail();
        }
    }

    @Test
    public void testVerifierSiCompteValide() {
        Client client = this.myClient;
        CompteBancaire compte = new CompteBancaire();
        compte.setId(1);
        client.getCompteBancaire().add(compte);
        client.setInterditBancaire(false);

        try {
            Assert.assertTrue(client.verifierSiCompteValide(1));
        }catch (GlobalException e){
            Assert.fail();
        }
    }

    @Test
    public void testVerifierSiCompteInvalide() {
        Client client = this.myClient;
        CompteBancaire compte = new CompteBancaire();
        compte.setId(1);
        client.getCompteBancaire().add(compte);
        client.setInterditBancaire(false);
        boolean flag = false;
        try {
            client.verifierSiCompteValide(2);

            Assert.fail();
        }catch (GlobalException e){
            flag = true;
            Assert.assertTrue(flag);
            Assert.assertEquals(e.getMessage(), "Compte non trouvé");
        }

    }

}
