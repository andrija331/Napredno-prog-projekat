/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.*;

/**
 *
 * @author KORISNIK
 */
public class KlijentTest {

    Klijent k;
    Klijent klijent;

    @BeforeEach
    public void setUp() {

        k = new Klijent(1, "Milan", "Milic", "milan@example.com", 641234562);
        klijent = new Klijent();
    }

    @AfterEach
    public void tearDown() {
        k = null;
        klijent = null;
    }

    @Test
    public void testToString() {
        assertTrue(k.toString().contains("Milan"));
        assertTrue(k.toString().contains("Milic"));
        assertTrue(k.toString().contains("milan@example.com"));
    }

    @Test
    public void testEquals() {
        Klijent k1 = new Klijent(1, "Milan", "Milic", "milan@example.com", 641234560);
        Klijent k2 = new Klijent(2, "Petar", "Peric", "milan@example.com", 641111111);
        Klijent k3 = new Klijent(3, "Jovan", "Jovic", "jovan@example.com", 641234562);
        assertTrue(k1.equals(k2));
        assertFalse(k1.equals(k3));

    }

    @Test
    public void testKonstruktor_ValidneVrednosti() {
        Klijent k2 = new Klijent(1, "Marko", "Marković", "marko@example.com", 641234567);

        assertEquals(1, k2.getKlijentID());
        assertEquals("Marko", k2.getIme());
        assertEquals("Marković", k2.getPrezime());
        assertEquals("marko@example.com", k2.getEmail());
        assertEquals(641234567, k2.getBrojTelefona());
    }

    @Test
    public void testKonstruktor_NullIme() {
        Exception e = assertThrows(NullPointerException.class,
                () -> new Klijent(1, null, "Marković", "marko@example.com", 641234567));
        assertEquals("Ime ne sme biti null", e.getMessage());
    }

    @Test
    public void testKonstruktor_NeispravanBrojTelefona() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new Klijent(1, "Marko", "Marković", "marko@example.com", 799999999));
        assertEquals("Broj telefona nije u adekvatnom opsegu", e.getMessage());
    }

    @Test
    public void testSetIme_Validno() {

        klijent.setIme("Marko");
        assertEquals("Marko", klijent.getIme());
    }

    @Test
    public void testSetIme_Null() {
        Exception e = assertThrows(NullPointerException.class, () -> klijent.setIme(null));
        assertEquals("Ime ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetIme_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> klijent.setIme(""));
        assertEquals("Ime ne sme biti prazno", e.getMessage());
    }

    @Test
    public void testSetPrezime_Validno() {
        klijent.setPrezime("Petrovic");
        assertEquals("Petrovic", klijent.getPrezime());
    }

    @Test
    public void testSetPrezime_Null() {
        Exception e = assertThrows(NullPointerException.class, () -> klijent.setPrezime(null));
        assertEquals("Prezime ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetPrezime_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> klijent.setPrezime(""));
        assertEquals("Prezime ne sme biti prazno", e.getMessage());
    }

    @Test
    public void testSetEmail_Validno() {
        klijent.setEmail("marko@gmail.com");
        assertEquals("marko@gmail.com", klijent.getEmail());
    }

    @Test
    public void testSetEmail_Null() {
        Exception e = assertThrows(NullPointerException.class, () -> klijent.setEmail(null));
        assertEquals("Email ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetEmail_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> klijent.setEmail(""));
        assertEquals("Email ne sme biti prazan", e.getMessage());
    }

    @Test
    public void testSetBrojTelefona_ValidanBroj() {
        klijent.setBrojTelefona(612345678);
        assertEquals(612345678, klijent.getBrojTelefona());
    }

    @Test
    public void testSetBrojTelefona_IspodOpsega() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> klijent.setBrojTelefona(599999999));
        assertEquals("Broj telefona nije u adekvatnom opsegu", e.getMessage());
    }

    @Test
    public void testSetBrojTelefona_IznadOpsega() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> klijent.setBrojTelefona(700000000));
        assertEquals("Broj telefona nije u adekvatnom opsegu", e.getMessage());
    }

    @Test
    public void testVratiNazivTabele() {
        assertEquals("klijent", k.vratiNazivTabele());
    }

    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("ime,prezime,email,brojTelefona", k.vratiKoloneZaUbacivanje());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        String expected = "klijent.klijentID=" + k.getKlijentID();
        assertEquals(expected, k.vratiPrimarniKljuc());
    }

    @Test
    public void testVratiVrednostZaIzmenu() {
        String expected = "ime='" + k.getIme() + "', prezime='" + k.getPrezime()
                + "', email='" + k.getEmail() + "', brojTelefona=" + k.getBrojTelefona();
        assertEquals(expected, k.vratiVrednostZaIzmenu());
    }

    @Test
    public void testVratiVrednostiZaUbacivanje() {
        String expected = "'" + k.getIme() + "','" + k.getPrezime() + "','" + k.getEmail() + "'," + k.getBrojTelefona();
        assertEquals(expected, k.vratiVrednostiZaUbacivanje());
    }

    @Test
    public void testVratiListu() throws Exception {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("klijentID")).thenReturn(k.getKlijentID());
        when(rs.getString("klijent.ime")).thenReturn(k.getIme());
        when(rs.getString("klijent.prezime")).thenReturn(k.getPrezime());
        when(rs.getString("klijent.email")).thenReturn(k.getEmail());
        when(rs.getInt("brojTelefona")).thenReturn((int) k.getBrojTelefona());

        List<ApstraktniDomenskiObjekat> lista = k.vratiListu(rs);

        assertEquals(1, lista.size());
        Klijent rezultat = (Klijent) lista.get(0);
        assertEquals(k.getIme(), rezultat.getIme());
        assertEquals(k.getEmail(), rezultat.getEmail());
        assertEquals(k.getPrezime(), rezultat.getPrezime());
    }
}
