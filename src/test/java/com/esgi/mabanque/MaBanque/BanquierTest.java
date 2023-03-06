package com.esgi.mabanque.MaBanque;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BanquierTest {

    private LocalDate dateArrivee = LocalDate.now();
    private Banquier myBanquier = new Banquier("Dupont@email.com", "Jean", "Dupont",
            LocalDate.of(2001, 8, 1),
            this.dateArrivee,
            new ArrayList<Client>()
    );

    @Test
    public void testBanquier() {

        Banquier banquier = this.myBanquier;

        assertEquals(banquier.getEmail(), "Dupont@email.com");
        assertEquals(banquier.getNom(), "Dupont");
        assertEquals(banquier.getPrenom(), "Jean");
        assertEquals(banquier.getDateDeNaissance(), LocalDate.of(2001, 8, 1));
        assertEquals(banquier.getDateArrivee(), this.dateArrivee);
        assertEquals(banquier.getClients(), new ArrayList<Client>());
    }
}
