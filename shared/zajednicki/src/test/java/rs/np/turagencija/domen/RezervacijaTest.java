/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.*;
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
public class RezervacijaTest {

    private Rezervacija rezervacija;
    private Rezervacija rez;
    private Zaposleni zaposleni;
    private Klijent klijent;
    private Aranzman aranzman;
    private TipAranzmana tip;
    private Grad grad;
    private FakultativnaUsluga usluga;
    private StavkaRezervacije stavka;
    private List<StavkaRezervacije> stavke;

    @BeforeEach
    public void setUp() {
        rez = new Rezervacija();
        zaposleni = new Zaposleni(1, "Petar", "Petrović", "pp", "123");
        klijent = new Klijent(1, "Milan", "Milić", "milan@example.com", 641234567);
        tip = new TipAranzmana(1, "Letovanje");
        grad = new Grad(1, "Atina", "Grčka", "Prestonica Grčke");
        aranzman = new Aranzman(1, "Grčka", new Date(System.currentTimeMillis() + 86400000), 7, 499.99, tip, grad);
        usluga = new FakultativnaUsluga(1, "Izlet", "Svi izleti", 50.0);
        stavka = new StavkaRezervacije(1, 50.0, usluga);
        stavke = new ArrayList<>();
        stavke.add(stavka);

        rezervacija = new Rezervacija(1, zaposleni, klijent, aranzman, stavke, new Date(), 1000.0);
    }

    @AfterEach
    public void tearDown() {
        rezervacija = null;
        zaposleni = null;
        klijent = null;
        aranzman = null;
        tip = null;
        grad = null;
        stavke = null;

    }

    /*
    @Override
    public ApstraktniDomenskiObjekat getInstance() {
        Zaposleni z = new Zaposleni(1, "Petar", "Peric", "pera", "123");
        Klijent k = new Klijent(1, "Milan", "Milic", "milan@example.com", 641234560);
        TipAranzmana tip = new TipAranzmana(1, "Letovanje");
        Aranzman a = new Aranzman(1, "Grcka 2025", new Date(), 7, 499.99, tip);
        FakultativnaUsluga fu = new FakultativnaUsluga(1, "Izlet", "opis", 10);
        StavkaRezervacije s1 = new StavkaRezervacije(1, 10, fu);

        Rezervacija rez = new Rezervacija(1, z, k, a, new ArrayList<>(), new Date(), 1000.0);
        s1.setRezervacija(rez);
        List<StavkaRezervacije> stavke = new ArrayList<>();
        stavke.add(s1);
        rez.setStavke(stavke);
        return rez;
    }
     */
    @Test
    public void testToString() {
        assertTrue(rezervacija.toString().contains("rezervacijaID=1"));
        assertTrue(rezervacija.toString().contains("Izlet"));
    }

    @Test
    public void testEquals() {
        Zaposleni z = new Zaposleni(1, "Petar", "Peric", "pera", "123");
        Klijent k1 = new Klijent(1, "Milan", "Milic", "milan@example.com", 641234560);
        Klijent k2 = new Klijent(1, "Nenad", "Nenadic", "nenad@example.com", 641234562);
        TipAranzmana tip2 = new TipAranzmana(1, "Letovanje");
        Aranzman a1 = new Aranzman(1, "Grcka", new Date(), 7, 499.99, tip2);
        Aranzman a2 = new Aranzman(2, "Italija", new Date(), 7, 499.99, tip2);

        Rezervacija r1 = new Rezervacija(1, z, k1, a1, new ArrayList<>(), new Date(), 1000.0);
        Rezervacija r2 = new Rezervacija(2, z, k1, a1, new ArrayList<>(), new Date(), 1000.0);
        Rezervacija r3 = new Rezervacija(3, z, k1, a2, new ArrayList<>(), new Date(), 1000.0);
        Rezervacija r4 = new Rezervacija(3, z, k2, a1, new ArrayList<>(), new Date(), 1000.0);

        assertTrue(r1.equals(r2));
        assertFalse(r1.equals(r3));
        assertFalse(r1.equals(r4));
        assertFalse(r3.equals(r4));
    }

    @Test
    public void testKonstruktor_ValidneVrednosti() {
        Zaposleni zaposleni1 = new Zaposleni(1, "Petar", "Petrović", "pp", "123");
        Klijent klijent1 = new Klijent(1, "Milan", "Milić", "milan@example.com", 641234567);
        TipAranzmana tip1 = new TipAranzmana(1, "Letovanje");
        Grad grad1 = new Grad(1, "Atina", "Grčka", "Prestonica Grčke");
        Aranzman aranzman1 = new Aranzman(1, "Grčka leto", new Date(System.currentTimeMillis() + 86400000),
                7, 499.99, tip1, grad1);
        FakultativnaUsluga usluga1 = new FakultativnaUsluga(1, "Izlet", "Obilazak Akropolja", 50.0);
        StavkaRezervacije stavka1 = new StavkaRezervacije(1, 50.0, usluga1);
        List<StavkaRezervacije> stavke1 = new ArrayList<>();
        stavke1.add(stavka1);

        Date datum = new Date();
        Rezervacija r = new Rezervacija(1, zaposleni1, klijent1, aranzman1, stavke1, datum, 1000.0);

        assertEquals(1, r.getRezervacijaID());
        assertEquals(zaposleni1, r.getZaposleni());
        assertEquals(klijent1, r.getKlijent());
        assertEquals(aranzman1, r.getAranzman());
        assertEquals(stavke1, r.getStavke());
        assertEquals(datum, r.getDatum());
        assertEquals(1000.0, r.getUkupnaCena());
    }

    @Test
    public void testKonstruktor_AranzmanNull() {
        Zaposleni zaposleni1 = new Zaposleni(1, "Petar", "Petrović", "pp", "123");
        Klijent klijent1 = new Klijent(1, "Milan", "Milić", "milan@example.com", 641234567);
        List<StavkaRezervacije> stavke1 = new ArrayList<>();
        stavke.add(new StavkaRezervacije(1, 50.0, new FakultativnaUsluga(1, "Izlet", "Opis", 50.0)));

        Date datum = new Date();

        Exception e = assertThrows(NullPointerException.class,
                () -> new Rezervacija(1, zaposleni1, klijent1, null, stavke1, datum, 1000.0));
        assertEquals("Aranzman ne sme biti null", e.getMessage());
    }

    public void testSetZaposleni_Validno() {
        rez.setZaposleni(zaposleni);
        assertEquals(zaposleni, rezervacija.getZaposleni());
    }

    @Test
    public void testSetZaposleni_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> rez.setZaposleni(null));
        assertEquals("Zaposleni ne sme biti null", e.getMessage());
    }

    public void testSetKlijent_Validno() {
        rez.setKlijent(klijent);
        assertEquals(klijent, rez.getKlijent());
    }

    @Test
    public void testSetKlijent_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> rez.setKlijent(null));
        assertEquals("Klijent ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetAranzman_Validno() {
        rez.setAranzman(aranzman);
        assertEquals(aranzman, rez.getAranzman());
    }

    @Test
    public void testSetAranzman_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> rez.setAranzman(null));
        assertEquals("Aranzman ne sme biti null", e.getMessage());
    }

    public void testSetStavke_Validno() {
        rez.setStavke(stavke);
        assertEquals(stavke, rez.getStavke());
    }

    @Test
    public void testSetStavke_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> rez.setStavke(null));
        assertEquals("Lista stavki ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetDatum_ValidanDatum() {

        Date danas = new Date();

        rez.setDatum(danas);

        assertEquals(danas, rez.getDatum());
    }

    @Test
    public void testSetDatum_NullVrednost() {

        Exception e = assertThrows(NullPointerException.class, () -> rez.setDatum(null));
        assertEquals("Datum rezervacije ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetUkupnaCena_Validna() {
        rez.setUkupnaCena(1200.0);
        assertEquals(1200.0, rez.getUkupnaCena());
    }

    @Test
    public void testSetUkupnaCena_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> rez.setUkupnaCena(null));
        assertEquals("Ukupna cena ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetUkupnaCena_NeispravnaVrednost() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> rez.setUkupnaCena(0.0));
        assertEquals("Ukupna cena mora biti veća od nule", e.getMessage());
    }

    @Test
    public void testVratiNazivTabele() {
        assertEquals("rezervacija", rezervacija.vratiNazivTabele());
    }

    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("datum,ukupnacena,zaposleni,klijent,aranzman", rezervacija.vratiKoloneZaUbacivanje());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        String expected = "rezervacijaID=" + rezervacija.getRezervacijaID();
        assertEquals(expected, rezervacija.vratiPrimarniKljuc());
    }

    @Test
    public void testVratiVrednostiZaUbacivanje() {
        String expected = "'" + new java.sql.Date(rezervacija.getDatum().getTime()) + "',"
                + rezervacija.getUkupnaCena() + ","
                + rezervacija.getZaposleni().getZaposleniID() + ","
                + rezervacija.getKlijent().getKlijentID() + ","
                + rezervacija.getAranzman().getAranzmanID();
        assertEquals(expected, rezervacija.vratiVrednostiZaUbacivanje());
    }

    @Test
    public void testVratiVrednostZaIzmenu() {
        String expected = "ukupnaCena=" + rezervacija.getUkupnaCena()
                + ", datum='" + new java.sql.Date(rezervacija.getDatum().getTime())
                + "', aranzman=" + rezervacija.getAranzman().getAranzmanID()
                + ", klijent=" + rezervacija.getKlijent().getKlijentID()
                + ", zaposleni=" + rezervacija.getZaposleni().getZaposleniID();
        assertEquals(expected, rezervacija.vratiVrednostZaIzmenu());
    }

    @Test
    public void testVratiListu() throws Exception {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getInt("rezervacijaID")).thenReturn(rezervacija.getRezervacijaID());
        when(rs.getDouble("ukupnaCena")).thenReturn(rezervacija.getUkupnaCena());
        when(rs.getDate("rezervacija.datum"))
                .thenReturn(new java.sql.Date(rezervacija.getDatum().getTime()));

        when(rs.getInt("zaposleniID")).thenReturn(rezervacija.getZaposleni().getZaposleniID());
        when(rs.getString("zaposleni.ime")).thenReturn(rezervacija.getZaposleni().getIme());
        when(rs.getString("zaposleni.prezime")).thenReturn(rezervacija.getZaposleni().getPrezime());
        when(rs.getString("zaposleni.username")).thenReturn(rezervacija.getZaposleni().getUsername());
        when(rs.getString("zaposleni.password")).thenReturn(rezervacija.getZaposleni().getPassword());

        when(rs.getInt("klijentID")).thenReturn(rezervacija.getKlijent().getKlijentID());
        when(rs.getString("klijent.ime")).thenReturn(rezervacija.getKlijent().getIme());
        when(rs.getString("klijent.prezime")).thenReturn(rezervacija.getKlijent().getPrezime());
        when(rs.getString("klijent.email")).thenReturn(rezervacija.getKlijent().getEmail());
        when(rs.getLong("klijent.brojTelefona")).thenReturn(rezervacija.getKlijent().getBrojTelefona());

        when(rs.getInt("tipID")).thenReturn(rezervacija.getAranzman().getTipAranzmana().getTipID());
        when(rs.getString("tipAranzmana.nazivTipa"))
                .thenReturn(rezervacija.getAranzman().getTipAranzmana().getNazivTipa());

        when(rs.getInt("gradID")).thenReturn(rezervacija.getAranzman().getGrad().getGradID());
        when(rs.getString("grad.drzava")).thenReturn(rezervacija.getAranzman().getGrad().getDrzava());
        when(rs.getString("grad.imeGrada"))
                .thenReturn(rezervacija.getAranzman().getGrad().getNazivGrada());
        when(rs.getString("grad.opis")).thenReturn(rezervacija.getAranzman().getGrad().getOpis());

        when(rs.getInt("aranzmanID")).thenReturn(rezervacija.getAranzman().getAranzmanID());
        when(rs.getString("aranzman.naziv")).thenReturn(rezervacija.getAranzman().getNaziv());
        when(rs.getInt("aranzman.brojNocenja"))
                .thenReturn(rezervacija.getAranzman().getBrojNocenja());
        when(rs.getDouble("aranzman.cena")).thenReturn(rezervacija.getAranzman().getCena());
        when(rs.getDate("aranzman.datum"))
                .thenReturn(new java.sql.Date(rezervacija.getAranzman().getDatum().getTime()));

        List<ApstraktniDomenskiObjekat> lista = rezervacija.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(1, lista.size());

        Rezervacija r = (Rezervacija) lista.get(0);
        assertEquals(rezervacija.getRezervacijaID(), r.getRezervacijaID());
        assertEquals(rezervacija.getUkupnaCena(), r.getUkupnaCena());
        assertEquals(rezervacija.getKlijent().getIme(), r.getKlijent().getIme());
        assertEquals(rezervacija.getZaposleni().getIme(), r.getZaposleni().getIme());
    }

}
