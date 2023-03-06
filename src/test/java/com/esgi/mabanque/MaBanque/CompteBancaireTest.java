package com.esgi.mabanque.MaBanque;

import exception.CreditException;
import org.junit.Test;
import org.junit.jupiter.api.Test;


import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.Assert.assertEquals;

public class CompteBancaireTest {

    @Test
    public void testCredit() throws CreditException {
        CompteBancaire compteBancaire = mock(CompteBancaire.class);
        doNothing().when(compteBancaire).save(anyInt());

        CompteRenduOperation compteRenduOperation = compteBancaire.credit(500);

        assertEquals(500, compteRenduOperation.getNouveauSolde());
        assertEquals(500, compteRenduOperation.getMontantCredite());
        assertEquals(0, compteRenduOperation.getMontantNonCredite());

        verify(compteBancaire).save(500);
    }

    @Test
    public void testSave() {
        CompteBancaire compteBancaire = mock(CompteBancaire.class);
        doNothing().when(compteBancaire).save(anyInt());


    }
    @Test
    public void testDebit() throws CreditException {
        CompteBancaire compteBancaire = mock(CompteBancaire.class);
        doNothing().when(compteBancaire).save(anyInt());

        CompteRenduOperation compteRenduOperation = compteBancaire.debit(500);

        assertEquals(0, compteRenduOperation.getNouveauSolde());
        assertEquals(0, compteRenduOperation.getMontantDebite());
        assertEquals(500, compteRenduOperation.getMontantNonDebite());

        verify(compteBancaire).save(0);
    }

}
