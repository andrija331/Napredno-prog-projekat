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
public class FakultativnaUslugaTest {

    private FakultativnaUsluga f;
    private FakultativnaUsluga usluga;

    @BeforeEach
    public void setUp() {
        f = new FakultativnaUsluga(1, "Rafting", "Avantura na Tari", 150.0);
        usluga = new FakultativnaUsluga();

    }

    @AfterEach
    public void tearDown() {
        usluga = null;
        f = null;
    }

    @Test
    public void testToString() {
        assertEquals("Rafting", f.toString());
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

    @Test
    public void testKonstruktor_ValidneVrednosti() {
        FakultativnaUsluga f1 = new FakultativnaUsluga(1, "Izlet brodom", "Tura po reci Dunav", 40.0);

        assertEquals(1, f1.getUslugaID());
        assertEquals("Izlet brodom", f1.getNaziv());
        assertEquals("Tura po reci Dunav", f1.getOpis());
        assertEquals(40.0, f1.getCena());
    }

    @Test
    public void testKonstruktor_NullNaziv() {
        Exception e = assertThrows(NullPointerException.class,
                () -> new FakultativnaUsluga(1, null, "Opis izleta", 50.0));
        assertEquals("Naziv usluge ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetNaziv_Validno() {
        usluga.setNaziv("Skijanje");
        assertEquals("Skijanje", usluga.getNaziv());
    }

    @Test
    public void testSetNaziv_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> usluga.setNaziv(null));
        assertEquals("Naziv usluge ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetNaziv_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> usluga.setNaziv(""));
        assertEquals("Naziv usluge ne sme biti prazan", e.getMessage());
    }

    @Test
    public void testSetOpis_Validno() {
        usluga.setOpis("Vožnja čamcem kroz kanjon reke Drine");
        assertEquals("Vožnja čamcem kroz kanjon reke Drine", usluga.getOpis());
    }

    @Test
    public void testSetOpis_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> usluga.setOpis(null));
        assertEquals("Opis ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetOpis_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> usluga.setOpis(""));
        assertEquals("Opis ne sme biti prazan", e.getMessage());
    }

    @Test
    public void testSetCena_Validna() {
        usluga.setCena(120.5);
        assertEquals(120.5, usluga.getCena());
    }

    @Test
    public void testSetCena_NulaIliNegativna() {
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> usluga.setCena(0));
        assertEquals("Cena mora biti veca od nule", e1.getMessage());

        Exception e2 = assertThrows(IllegalArgumentException.class, () -> usluga.setCena(-10));
        assertEquals("Cena mora biti veca od nule", e2.getMessage());
    }

    @Test
    public void testVratiNazivTabele() {
        assertEquals("fakultativnausluga", f.vratiNazivTabele());
    }

    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("naziv,opis,cena", f.vratiKoloneZaUbacivanje());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        String expected = "fakultativnausluga.uslugaID=" + f.getUslugaID();
        assertEquals(expected, f.vratiPrimarniKljuc());
    }

    @Test
    public void testVratiVrednostZaIzmenu() {
        String expected = "naziv='" + f.getNaziv() + "', opis='" + f.getOpis() + "', cena=" + f.getCena();
        assertEquals(expected, f.vratiVrednostZaIzmenu());
    }

    @Test
    public void testVratiVrednostiZaUbacivanje() {
        String expected = "'" + f.getNaziv() + "','" + f.getOpis() + "'," + f.getCena();
        assertEquals(expected, f.vratiVrednostiZaUbacivanje());
    }

    @Test
    public void testVratiListu() throws Exception {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("uslugaID")).thenReturn(f.getUslugaID());
        when(rs.getString("fakultativnausluga.naziv")).thenReturn(f.getNaziv());
        when(rs.getString("opis")).thenReturn(f.getOpis());
        when(rs.getDouble("fakultativnausluga.cena")).thenReturn(f.getCena());

        List<ApstraktniDomenskiObjekat> lista = f.vratiListu(rs);

        assertEquals(1, lista.size());
        FakultativnaUsluga rezultat = (FakultativnaUsluga) lista.get(0);
        assertEquals(f.getNaziv(), rezultat.getNaziv());
        assertEquals(f.getOpis(), rezultat.getOpis());
        assertEquals(f.getCena(), rezultat.getCena());
    }

}
