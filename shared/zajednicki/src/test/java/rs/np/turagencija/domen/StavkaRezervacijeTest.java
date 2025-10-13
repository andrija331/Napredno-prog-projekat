/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.domen;

import java.util.ArrayList;
import java.util.Date;
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
public class StavkaRezervacijeTest extends ApstraktniDomenskiObjekatTest {

    StavkaRezervacije s;

    @BeforeEach
    public void setUp() {
        super.setUp();
        s = (StavkaRezervacije) ado;
    }

    @AfterEach
    public void tearDown() {
        s = null;
    }

    @Override
    public ApstraktniDomenskiObjekat getInstance() {
        FakultativnaUsluga usluga = new FakultativnaUsluga(1, "Izlet", "opis", 30.0);
        Rezervacija rez = new Rezervacija(1, new Zaposleni(), new Klijent(), new Aranzman(), new ArrayList<>(), new Date(), 200.0);
        StavkaRezervacije st = new StavkaRezervacije(1, 30.0, usluga);
        st.setRezervacija(rez);
        return st;
    }

    @Test
    public void testToString() {
        assertTrue(s.toString().contains("1"));
        assertTrue(s.toString().contains("30.0"));
        assertTrue(s.toString().contains("Izlet"));
    }
}
