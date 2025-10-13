/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.domen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author KORISNIK
 */
public class RezervacijaTest extends ApstraktniDomenskiObjekatTest {

    Rezervacija r;

    @BeforeEach
    public void setUp() {
        super.setUp();
        r = (Rezervacija) ado;
    }

    @AfterEach
    public void tearDown() {
        r = null;
    }

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

    @Test
    public void testToString() {
        assertTrue(r.toString().contains("1"));
        assertTrue(r.toString().contains("Izlet"));
    }

    @Test
    public void testEquals() {
        Zaposleni z = new Zaposleni(1, "Petar", "Peric", "pera", "123");
        Klijent k1 = new Klijent(1, "Milan", "Milic", "milan@example.com", 641234560);
        Klijent k2 = new Klijent(1, "Nenad", "Nenadic", "nenad@example.com", 641234562);
        TipAranzmana tip = new TipAranzmana(1, "Letovanje");
        Aranzman a1 = new Aranzman(1, "Grcka", new Date(), 7, 499.99, tip);
        Aranzman a2 = new Aranzman(2, "Italija", new Date(), 7, 499.99, tip);

        Rezervacija r1 = new Rezervacija(1, z, k1, a1, new ArrayList<>(), new Date(), 1000.0);
        Rezervacija r2 = new Rezervacija(2, z, k1, a1, new ArrayList<>(), new Date(), 1000.0);
        Rezervacija r3 = new Rezervacija(3, z, k1, a2, new ArrayList<>(), new Date(), 1000.0);
        Rezervacija r4 = new Rezervacija(3, z, k2, a1, new ArrayList<>(), new Date(), 1000.0);

        assertTrue(r1.equals(r2));
        assertFalse(r1.equals(r3));
        assertFalse(r1.equals(r4));
        assertFalse(r3.equals(r4));
    }
}
