/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.domen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author KORISNIK
 */
public class TipAranzmanaTest extends ApstraktniDomenskiObjekatTest {

    TipAranzmana tip;

    @BeforeEach
    public void setUp() {
        super.setUp();
        tip = (TipAranzmana) ado;
    }

    @AfterEach
    public void tearDown() {
        tip = null;
    }

    @Override
    public ApstraktniDomenskiObjekat getInstance() {
        return new TipAranzmana(1, "Letovanje");
    }

    @Test
    public void testToString() {
        assertEquals("Letovanje", tip.toString());
    }

    public void testEquals() {
        TipAranzmana t1 = new TipAranzmana(1, "Letovanje");
        TipAranzmana t2 = new TipAranzmana(2, "Zimovanje");

        assertTrue(tip.equals(t1));
        assertFalse(tip.equals(t2));
    }
}
