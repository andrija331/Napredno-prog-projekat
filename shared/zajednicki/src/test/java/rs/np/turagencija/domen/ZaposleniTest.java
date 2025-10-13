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
public class ZaposleniTest extends ApstraktniDomenskiObjekatTest {

    Zaposleni z;

    public ZaposleniTest() {
    }

    @BeforeEach
    public void setUp() {
        super.setUp(); // poziva setUp() iz apstraktne klase, koji popunjava ado
        z = (Zaposleni) ado; // sad inicijalizuj i lokalni objekat z
    }

    @AfterEach
    public void tearDown() {
        z = null;
    }

    @Override
    public ApstraktniDomenskiObjekat getInstance() {
        return new Zaposleni(1, "Petar", "Peric", "peraperic", "pera");
    }

    @Test
    public void testToString() {
        assertTrue(z.toString().contains("Petar"));
        assertTrue(z.toString().contains("Peric"));
        assertTrue(z.toString().contains("peraperic"));
    }

    /**
     * Test of hashCode method, of class Zaposleni.
     */
    @ParameterizedTest
    @CsvSource({
        "pera,pera123,pera,pera123,true",
        "marko,pera123,pera,pera123,false",
        "pera,marko123,pera,pera123,false",
        "marko,marko123,pera,pera123,false"})
    public void testEquals(String username1, String password1, String username2, String password2, boolean ocekivano) {
        Zaposleni z1 = new Zaposleni(1, "Ime", "Prezime", username1, password1);
        Zaposleni z2 = new Zaposleni(1, "Ime", "Prezime", username2, password2);
        assertEquals(ocekivano, z1.equals(z2));
    }

}
