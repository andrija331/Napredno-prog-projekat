/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.domen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author KORISNIK
 */
public class KlijentTest extends ApstraktniDomenskiObjekatTest {

    Klijent k;

    @BeforeEach
    public void setUp() {
        super.setUp();
        k = (Klijent) ado;
    }

    @AfterEach
    public void tearDown() {
        k = null;
    }

    @Override
    public ApstraktniDomenskiObjekat getInstance() {
        return new Klijent(1, "Milan", "Milic", "milan@example.com", 641234562);
    }

    @Test
    public void testToString() {
        assertTrue(k.toString().contains("Milan"));
        assertTrue(k.toString().contains("Milic"));
        assertTrue(k.toString().contains("milan@example.com"));
    }

    @Test
    public void testEquals() {
        Klijent k1 = new Klijent(1, "Milan", "Milić", "milan@example.com", 641234560);
        Klijent k2 = new Klijent(2, "Petar", "Perić", "milan@example.com", 641111111);
        Klijent k3 = new Klijent(3, "Jovan", "Jović", "jovan@example.com", 641234562);
        assertTrue(k1.equals(k2));
        assertFalse(k1.equals(k3));

    }
}
