/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author KORISNIK
 */
public class StavkaRezervacijeTest {

    private StavkaRezervacije stavka;
    private Rezervacija rezervacija;
    private FakultativnaUsluga usluga;

    @BeforeEach
    public void setUp() {
        usluga = new FakultativnaUsluga(1, "Izlet", "Opis izleta", 50.0);
        rezervacija = new Rezervacija();
        rezervacija.setRezervacijaID(1);
        stavka = new StavkaRezervacije(1, 50.0, usluga);
        stavka.setRezervacija(rezervacija);

    }

    @AfterEach
    public void tearDown() {
        stavka = null;
        rezervacija = null;
        usluga = null;
    }

    @Test
    public void testToString() {
        assertTrue(stavka.toString().contains("1"));
        assertTrue(stavka.toString().contains("50.0"));
        assertTrue(stavka.toString().contains("Izlet"));
    }

    @Test
    public void testKonstruktor_ValidneVrednosti() {

        StavkaRezervacije s = new StavkaRezervacije(1, 50.0, usluga);

        assertEquals(1, s.getRb());
        assertEquals(50.0, s.getCena());
        assertEquals(usluga, s.getUsluga());
    }

    @Test
    public void testKonstruktor_NeispravanRb() {

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new StavkaRezervacije(0, 50.0, usluga));
        assertEquals("Redni broj stavke mora biti veći od nule", e.getMessage());
    }

    @Test
    public void testSetRezervacija_Validno() {
        Rezervacija r2 = new Rezervacija();
        r2.setRezervacijaID(2);
        stavka.setRezervacija(r2);
        assertEquals(r2, stavka.getRezervacija());
    }

    @Test
    public void testSetRezervacija_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> stavka.setRezervacija(null));
        assertEquals("Rezervacija ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetRb_Validno() {
        stavka.setRb(3);
        assertEquals(3, stavka.getRb());
    }

    @Test
    public void testSetRb_Neispravan() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> stavka.setRb(0));
        assertEquals("Redni broj stavke mora biti veći od nule", e.getMessage());
    }

    @Test
    public void testSetCena_Validna() {
        stavka.setCena(120.5);
        assertEquals(120.5, stavka.getCena());
    }

    @Test
    public void testSetCena_Neispravna() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> stavka.setCena(0));
        assertEquals("Cena mora biti veća od nule", e.getMessage());
    }

    @Test
    public void testSetUsluga_Validna() {
        FakultativnaUsluga nova = new FakultativnaUsluga(2, "Vožnja brodom", "Tura po reci", 40.0);
        stavka.setUsluga(nova);
        assertEquals(nova, stavka.getUsluga());
    }

    @Test
    public void testSetUsluga_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> stavka.setUsluga(null));
        assertEquals("Usluga ne sme biti null", e.getMessage());
    }

    @Test
    public void testVratiNazivTabele() {
        assertEquals("stavkaRezervacije", stavka.vratiNazivTabele());
    }

    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("rb,rezervacija,cena,usluga", stavka.vratiKoloneZaUbacivanje());
    }

    @Test
    public void testVratiVrednostiZaUbacivanje() {
        String expected = stavka.getRb() + "," + stavka.getRezervacija().getRezervacijaID() + ","
                + stavka.getCena() + "," + stavka.getUsluga().getUslugaID();
        assertEquals(expected, stavka.vratiVrednostiZaUbacivanje());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        String expected = "rb=" + stavka.getRb() + " AND stavkarezervacije.rezervacija="
                + stavka.getRezervacija().getRezervacijaID();
        assertEquals(expected, stavka.vratiPrimarniKljuc());
    }

    @Test
    public void testVratiVrednostZaIzmenu() {
        String expected = "rb=" + stavka.getRb() + ", rezervacija="
                + stavka.getRezervacija().getRezervacijaID() + ", cena="
                + stavka.getCena() + ", usluga=" + stavka.getUsluga().getUslugaID();
        assertEquals(expected, stavka.vratiVrednostZaIzmenu());
    }

    // ---------- TEST ZA VRATILISTU (MOCKOVANJE RESULTSETA) ----------
    @Test
    public void testVratiListu() throws Exception {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("rb")).thenReturn(stavka.getRb());
        when(rs.getDouble("stavkaRezervacije.cena")).thenReturn(stavka.getCena());

        when(rs.getString("fakultativnausluga.naziv")).thenReturn(stavka.getUsluga().getNaziv());
        when(rs.getString("fakultativnausluga.opis")).thenReturn(stavka.getUsluga().getOpis());
        when(rs.getDouble("fakultativnausluga.cena")).thenReturn(stavka.getUsluga().getCena());
        when(rs.getInt("fakultativnausluga.uslugaID")).thenReturn(stavka.getUsluga().getUslugaID());

        List<ApstraktniDomenskiObjekat> lista = stavka.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(1, lista.size());

        StavkaRezervacije s = (StavkaRezervacije) lista.get(0);
        assertEquals(stavka.getRb(), s.getRb());
        assertEquals(stavka.getCena(), s.getCena());
        assertEquals(stavka.getUsluga().getNaziv(), s.getUsluga().getNaziv());
    }
}
