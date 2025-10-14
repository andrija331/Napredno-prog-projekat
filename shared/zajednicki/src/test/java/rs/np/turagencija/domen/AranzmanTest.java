/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.domen;

import java.util.Date;
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
public class AranzmanTest extends ApstraktniDomenskiObjekatTest {

    Aranzman a;

    @BeforeEach
    public void setUp() {
        super.setUp();
        a = (Aranzman) ado;
    }

    @AfterEach
    public void tearDown() {
        a = null;
    }

    @Override
    public ApstraktniDomenskiObjekat getInstance() {
        TipAranzmana tip = new TipAranzmana(1, "Letovanje");
        Grad grad = new Grad(1, "Atina", "Grcka", "Prestonica Grcke...");
        return new Aranzman(1, "Grcka", new Date(), 7, 499.99, tip, grad);
    }

    @Test
    public void testToString() {
        assertTrue(a.toString().contains("Grcka"));
        assertTrue(a.toString().contains("7"));
    }

    @ParameterizedTest
    @CsvSource({
        "Grcka,7,Grcka,7,true",
        "Grcka,7,Italija,7,false",
        "Grcka,7,Grcka,10,false",
        "Grcka,7,Italija,10,false"
    })
    public void testEquals(String naziv1, int bn1, String naziv2, int bn2, boolean expected) {
        TipAranzmana tip = new TipAranzmana(1, "Letovanje");
        Aranzman a1 = new Aranzman(1, naziv1, new Date(), bn1, 400.0, tip);
        Aranzman a2 = new Aranzman(2, naziv2, new Date(), bn2, 400.0, tip);
        assertEquals(expected, a1.equals(a2));
    }
}
