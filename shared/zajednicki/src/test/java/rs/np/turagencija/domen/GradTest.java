/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.ResultSet;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.*;

/**
 *
 * @author KORISNIK
 */
public class GradTest {

    private Grad g;
    private Grad grad;

    @BeforeEach
    public void setUp() {
        g = new Grad(1, "Beograd", "Srbija", "Glavni grad Srbije, na uscu Save u Dunav.");
        grad = new Grad();
    }

    @AfterEach
    public void tearDown() {
        g = null;
        grad = null;

    }

    @Test
    public void testToString() {
        assertEquals("Beograd", g.toString());
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

    @Test
    public void testKonstruktor_ValidneVrednosti() {
        Grad g1 = new Grad(1, "Atina", "Grčka", "Prestonica sa bogatom istorijom");

        assertEquals(1, g1.getGradID());
        assertEquals("Atina", g1.getNazivGrada());
        assertEquals("Grčka", g1.getDrzava());
        assertEquals("Prestonica sa bogatom istorijom", g1.getOpis());
    }

    @Test
    public void testKonstruktor_NullImeGrada() {
        Exception e = assertThrows(NullPointerException.class,
                () -> new Grad(1, null, "Grčka", "Opis grada"));
        assertEquals("Naziv grada ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetNazivGrada_Validno() {
        grad.setNazivGrada("Novi Sad");
        assertEquals("Novi Sad", grad.getNazivGrada());
    }

    @Test
    public void testSetNazivGrada_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> grad.setNazivGrada(null));
        assertEquals("Naziv grada ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetNazivGrada_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> grad.setNazivGrada(""));
        assertEquals("Naziv grada ne sme biti prazan", e.getMessage());
    }

    @Test
    public void testSetDrzava_Validno() {
        grad.setDrzava("Srbija");
        assertEquals("Srbija", grad.getDrzava());
    }

    @Test
    public void testSetDrzava_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> grad.setDrzava(null));
        assertEquals("Naziv drzave ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetDrzava_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> grad.setDrzava(""));
        assertEquals("Naziv drzave ne sme biti prazan", e.getMessage());
    }

    @Test
    public void testSetOpis_Validno() {
        grad.setOpis("Grad na obali Dunava, poznat po Petrovaradinskoj tvrđavi.");
        assertEquals("Grad na obali Dunava, poznat po Petrovaradinskoj tvrđavi.", grad.getOpis());
    }

    @Test
    public void testSetOpis_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> grad.setOpis(null));
        assertEquals("Opis ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetOpis_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> grad.setOpis(""));
        assertEquals("Opis ne sme biti prazan", e.getMessage());
    }

    @Test
    public void testVratiNazivTabele() {
        assertEquals("grad", g.vratiNazivTabele());
    }

    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("imeGrada,drzava,opis", g.vratiKoloneZaUbacivanje());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        String expected = "grad.gradID=" + g.getGradID();
        assertEquals(expected, g.vratiPrimarniKljuc());
    }

    @Test
    public void testVratiVrednostZaIzmenu() {
        String expected = "imeGrada='" + g.getNazivGrada() + "', drzava='" + g.getDrzava() + "', opis='" + g.getOpis() + "'";
        assertEquals(expected, g.vratiVrednostZaIzmenu());
    }

    @Test
    public void testVratiVrednostiZaUbacivanje() {
        String expected = "'" + g.getNazivGrada() + "','" + g.getDrzava() + "','" + g.getOpis() + "'";
        assertEquals(expected, g.vratiVrednostiZaUbacivanje());
    }

    @Test
    public void testVratiListu() throws Exception {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("gradID")).thenReturn(g.getGradID());
        when(rs.getString("grad.imeGrada")).thenReturn(g.getNazivGrada());
        when(rs.getString("grad.opis")).thenReturn(g.getOpis());
        when(rs.getString("grad.drzava")).thenReturn(g.getDrzava());

        List<ApstraktniDomenskiObjekat> lista = g.vratiListu(rs);

        assertEquals(1, lista.size());
        Grad rezultat = (Grad) lista.get(0);
        assertEquals(g.getNazivGrada(), rezultat.getNazivGrada());
        assertEquals(g.getDrzava(), rezultat.getDrzava());
        assertEquals(g.getOpis(), rezultat.getOpis());
    }
}
