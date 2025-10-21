/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.ResultSet;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author KORISNIK
 */
public class TipAranzmanaTest {

    private TipAranzmana t;
    private TipAranzmana tip;

    @BeforeEach
    public void setUp() {
        t = new TipAranzmana(1, "Letovanje");
        tip = new TipAranzmana();
    }

    @AfterEach
    public void tearDown() {
        t = null;
        tip = null;
    }

    @Test
    public void testToString() {
        assertEquals("Letovanje", t.toString());
    }

    public void testEquals() {
        TipAranzmana t1 = new TipAranzmana(1, "Letovanje");
        TipAranzmana t2 = new TipAranzmana(2, "Zimovanje");

        assertTrue(tip.equals(t1));
        assertFalse(tip.equals(t2));
    }

    @Test
    public void testKonstruktor_ValidneVrednosti() {
        TipAranzmana t1 = new TipAranzmana(1, "Letovanje");

        assertEquals(1, t1.getTipID());
        assertEquals("Letovanje", t1.getNazivTipa());
    }

    @Test
    public void testKonstruktor_NullNazivTipa() {
        Exception e = assertThrows(NullPointerException.class,
                () -> new TipAranzmana(1, null));
        assertEquals("Naziv tipa ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetNazivTipa_Validno() {
        tip.setNazivTipa("Zimovanje");
        assertEquals("Zimovanje", tip.getNazivTipa());
    }

    @Test
    public void testSetNazivTipa_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> tip.setNazivTipa(null));
        assertEquals("Naziv tipa ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetNazivTipa_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> tip.setNazivTipa(""));
        assertEquals("Naziv tipa ne sme biti prazan", e.getMessage());
    }

    @Test
    public void testVratiNazivTabele() {
        assertEquals("tipAranzmana", t.vratiNazivTabele());
    }

    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("nazivTipa", t.vratiKoloneZaUbacivanje());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        String expected = "tipAranzmana.tipID=" + t.getTipID();
        assertEquals(expected, t.vratiPrimarniKljuc());
    }

    @Test
    public void testVratiVrednostZaIzmenu() {
        String expected = "nazivTipa='" + t.getNazivTipa() + "'";
        assertEquals(expected, t.vratiVrednostZaIzmenu());
    }

    @Test
    public void testVratiVrednostiZaUbacivanje() {
        String expected = "'" + t.getNazivTipa() + "'";
        assertEquals(expected, t.vratiVrednostiZaUbacivanje());
    }

    @Test
    public void testVratiListu() throws Exception {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("tipID")).thenReturn(t.getTipID());
        when(rs.getString("tipAranzmana.nazivTipa")).thenReturn(t.getNazivTipa());

        List<ApstraktniDomenskiObjekat> lista = t.vratiListu(rs);

        assertEquals(1, lista.size());
        TipAranzmana rezultat = (TipAranzmana) lista.get(0);
        assertEquals(t.getTipID(), rezultat.getTipID());
        assertEquals(t.getNazivTipa(), rezultat.getNazivTipa());
    }
}
