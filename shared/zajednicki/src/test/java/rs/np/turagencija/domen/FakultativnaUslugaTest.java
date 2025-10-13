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
public class FakultativnaUslugaTest extends ApstraktniDomenskiObjekatTest {

    FakultativnaUsluga f;

    @BeforeEach
    public void setUp() {
        super.setUp();
        f = (FakultativnaUsluga) ado;
    }

    @AfterEach
    public void tearDown() {
        f = null;
    }

    @Override
    public ApstraktniDomenskiObjekat getInstance() {
        return new FakultativnaUsluga(1, "Izlet brodom", "Krstarenje rekom", 45.5);
    }

    @Test
    public void testToString() {
        assertEquals("Izlet brodom", f.toString());
    }

    @ParameterizedTest
    @CsvSource({
        "Izlet brodom,45.5,Izlet brodom,45.5,true",
        "Izlet brodom,40.0,Izlet brodom,45.5,false",
        "Planinarenje,45.5,Izlet brodom,45.5,false",
        "Planinarenje,40.5,Izlet brodom,45.5,false"
    })
    public void testEquals(String naziv1, double cena1, String naziv2, double cena2, boolean expected) {
        FakultativnaUsluga f1 = new FakultativnaUsluga(1, naziv1, "opis", cena1);
        FakultativnaUsluga f2 = new FakultativnaUsluga(2, naziv2, "opis", cena2);
        assertEquals(expected, f1.equals(f2));
    }
}
