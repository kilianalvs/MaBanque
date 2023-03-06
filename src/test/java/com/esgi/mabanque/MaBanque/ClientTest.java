package com.esgi.mabanque.MaBanque;

import com.esgi.mabanque.MaBanque.exception.GlobalException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    private Client myClient = new Client("Dupont@email.com", "Jean", "Dupont",
            LocalDate.of(2001, 8, 1),
            new ArrayList<CompteBancaire>(), false);

    @Test
    public void testClient() {

        Client client = this.myClient;

        assertEquals(client.getCompteBancaire(), new ArrayList<CompteBancaire>());
        assertEquals(client.getEmail(), "Dupont@email.com");
        assertEquals(client.getNom(), "Dupont");
        assertEquals(client.getPrenom(), "Jean");
        assertEquals(client.getDateDeNaissance(), LocalDate.of(2001, 8, 1));
        assertFalse(client.isInterditBancaire());
    }

    @Test
    public void testVerifierSiInterditBanquaireFalse() {
        Client client = this.myClient;
        CompteBancaire compte = new CompteBancaire();
        compte.setId(1);
        client.getCompteBancaire().add(compte);
        client.setInterditBancaire(false);

        try {
            assertTrue(client.verifierSiCompteValide(1));
        }catch (GlobalException e){
            fail();
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
            assertFalse(client.verifierSiCompteValide(1));
        }catch (GlobalException e){
            fail();
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
            assertTrue(client.verifierSiCompteValide(1));
        }catch (GlobalException e){
            fail();
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

            fail();
        }catch (GlobalException e){
            flag = true;
            assertTrue(flag);
            assertEquals(e.getMessage(), "Compte non trouvé");
        }

    }

}
