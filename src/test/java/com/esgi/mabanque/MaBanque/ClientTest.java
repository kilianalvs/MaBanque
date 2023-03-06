package com.esgi.mabanque.MaBanque;

import exception.GlobalException;
    import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

    public class ClientTest {

        @Test
        public void testVerifierSiCompteValide() throws GlobalException, GlobalException {
            CompteBancaire compte1 = new CompteBancaire();
            CompteBancaire compte2 = new CompteBancaire();

            List<CompteBancaire> comptes = new ArrayList<>();
            comptes.add(compte1);
            comptes.add(compte2);

            Client client = new Client("john.doe@example.com", "John", "Doe", LocalDate.of(1980, 1, 1), comptes, false);

            assertTrue(client.verifierSiCompteValide(1));
            assertFalse(client.verifierSiCompteValide(2));
        }
    }


