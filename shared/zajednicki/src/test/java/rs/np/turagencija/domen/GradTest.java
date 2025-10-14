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
public class GradTest extends ApstraktniDomenskiObjekatTest {

    private Grad grad;

    @BeforeEach
    public void setUp() {
        super.setUp();
        grad = (Grad) ado;
    }

    @AfterEach
    public void tearDown() {
        grad = null;
    }

    @Override
    public ApstraktniDomenskiObjekat getInstance() {
        return new Grad(1, "Beograd", "Srbija", "Glavni grad Srbije.");
    }

    @Test
    public void testToString() {
        assertEquals("Beograd", grad.toString());
    }

    @ParameterizedTest
    @CsvSource({
        "Prag,Ceska,Prag,Ceska,true",
        "Krakov,Ceska,Prag,Ceska,false",
        "Prag,Poljska,Prag,Ceska,false",
        "Krakov,Poljska,Prag,Ceska,false"})
    public void testEquals(String grad1, String drzava1, String grad2, String drzava2, boolean ocekivano) {
        Grad g1 = new Grad(1, grad1, drzava1, "Opis1");
        Grad g2 = new Grad(2, grad2, drzava2, "Opis2");

        assertEquals(ocekivano, g1.equals(g2));
    }

}
