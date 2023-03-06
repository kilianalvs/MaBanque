import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

        Assert.assertEquals(banquier.getEmail(), "Dupont@email.com");
        Assert.assertEquals(banquier.getNom(), "Dupont");
        Assert.assertEquals(banquier.getPrenom(), "Jean");
        Assert.assertEquals(banquier.getDateDeNaissance(), LocalDate.of(2001, 8, 1));
        Assert.assertEquals(banquier.getDateArrivee(), this.dateArrivee);
        Assert.assertEquals(banquier.getClients(), new ArrayList<Client>());
    }
}
