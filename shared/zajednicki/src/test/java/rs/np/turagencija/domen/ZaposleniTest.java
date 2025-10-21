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
public class ZaposleniTest {

    private Zaposleni z;
    private Zaposleni zaposleni;

    public ZaposleniTest() {
    }

    @BeforeEach
    public void setUp() {
        z = new Zaposleni(1, "Petar", "Petrovic", "ppetro", "12345");
        zaposleni = new Zaposleni();
    }

    @AfterEach
    public void tearDown() {
        z = null;
        zaposleni = null;
    }

    @Test
    public void testToString() {
        assertTrue(z.toString().contains("Petar"));
        assertTrue(z.toString().contains("Petrovic"));
        assertTrue(z.toString().contains("ppetro"));
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

    @Test
    public void testKonstruktor_ValidneVrednosti() {
        Zaposleni z2 = new Zaposleni(1, "Petar", "Petrović", "pp", "12345");

        assertEquals(1, z2.getZaposleniID());
        assertEquals("Petar", z2.getIme());
        assertEquals("Petrović", z2.getPrezime());
        assertEquals("pp", z2.getUsername());
        assertEquals("12345", z2.getPassword());
    }

    @Test
    public void testKonstruktor_PraznoIme() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new Zaposleni(1, "", "Petrović", "pp", "12345"));
        assertEquals("Ime ne sme biti prazno", e.getMessage());
    }

    @Test
    public void testSetIme_Validno() {
        zaposleni.setIme("Milan");
        assertEquals("Milan", zaposleni.getIme());
    }

    @Test
    public void testSetIme_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> zaposleni.setIme(null));
        assertEquals("Ime ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetIme_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> zaposleni.setIme(""));
        assertEquals("Ime ne sme biti prazno", e.getMessage());
    }

    @Test
    public void testSetPrezime_Validno() {
        zaposleni.setPrezime("Markovic");
        assertEquals("Markovic", zaposleni.getPrezime());
    }

    @Test
    public void testSetPrezime_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> zaposleni.setPrezime(null));
        assertEquals("Prezime ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetPrezime_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> zaposleni.setPrezime(""));
        assertEquals("Prezime ne sme biti prazno", e.getMessage());
    }

    @Test
    public void testSetUsername_Validno() {
        zaposleni.setUsername("pera");
        assertEquals("pera", zaposleni.getUsername());
    }

    @Test
    public void testSetUsername_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> zaposleni.setUsername(null));
        assertEquals("Username ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetUsername_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> zaposleni.setUsername(""));
        assertEquals("Username ne sme biti prazan", e.getMessage());
    }

    @Test
    public void testSetPassword_Validno() {
        zaposleni.setPassword("lozinka123");
        assertEquals("lozinka123", zaposleni.getPassword());
    }

    @Test
    public void testSetPassword_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> zaposleni.setPassword(null));
        assertEquals("Sifra ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetPassword_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> zaposleni.setPassword(""));
        assertEquals("Sifra ne sme biti prazna", e.getMessage());
    }

    @Test
    public void testVratiNazivTabele() {
        assertEquals("zaposleni", z.vratiNazivTabele());
    }

    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("ime,prezime,username,password", z.vratiKoloneZaUbacivanje());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        String expected = "zaposleni.zaposleniID=" + z.getZaposleniID();
        assertEquals(expected, z.vratiPrimarniKljuc());
    }

    @Test
    public void testVratiVrednostZaIzmenu() {
        String expected = "ime='" + z.getIme() + "', prezime='" + z.getPrezime()
                + "', username='" + z.getUsername() + "', password='" + z.getPassword() + "'";
        assertEquals(expected, z.vratiVrednostZaIzmenu());
    }

    @Test
    public void testVratiVrednostiZaUbacivanje() {
        String expected = "'" + z.getIme() + "','" + z.getPrezime() + "','" + z.getUsername() + "','" + z.getPassword() + "'";
        assertEquals(expected, z.vratiVrednostiZaUbacivanje());
    }

    @Test
    public void testVratiListu() throws Exception {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("zaposleniID")).thenReturn(z.getZaposleniID());
        when(rs.getString("zaposleni.ime")).thenReturn(z.getIme());
        when(rs.getString("zaposleni.prezime")).thenReturn(z.getPrezime());
        when(rs.getString("zaposleni.username")).thenReturn(z.getUsername());
        when(rs.getString("zaposleni.password")).thenReturn(z.getPassword());

        List<ApstraktniDomenskiObjekat> lista = z.vratiListu(rs);

        assertEquals(1, lista.size());
        Zaposleni rezultat = (Zaposleni) lista.get(0);
        assertEquals(z.getIme(), rezultat.getIme());
        assertEquals(z.getPrezime(), rezultat.getPrezime());
        assertEquals(z.getUsername(), rezultat.getUsername());
        assertEquals(z.getPassword(), rezultat.getPassword());
    }

}
