/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.*;

import java.util.Date;
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
public class AranzmanTest {

    private Aranzman a;
    private TipAranzmana tip;
    private Grad grad;

    @BeforeEach
    public void setUp() {
        tip = new TipAranzmana(1, "Letovanje");
        grad = new Grad(1, "Atina", "Grčka", "Prestonica Grčke");
        a = new Aranzman(1, "Grčka leto", new Date(System.currentTimeMillis() + 86400000), 7, 499.99, tip, grad);
    }

    @AfterEach
    public void tearDown() {
        a = null;
        tip = null;
        grad = null;
    }

    @Test
    public void testToString() {
        assertTrue(a.toString().contains("Grčka leto"));
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
        TipAranzmana tipAr = new TipAranzmana(1, "Letovanje");
        Aranzman a1 = new Aranzman(1, naziv1, new Date(), bn1, 400.0, tipAr);
        Aranzman a2 = new Aranzman(2, naziv2, new Date(), bn2, 400.0, tipAr);
        assertEquals(expected, a1.equals(a2));
    }

    @Test
    public void testKonstruktor_ValidneVrednosti() {
        TipAranzmana tip2 = new TipAranzmana(1, "Letovanje");
        Grad grad2 = new Grad(1, "Atina", "Grčka", "Prestonica Grčke");
        Date datum = new Date(System.currentTimeMillis() + 86400000); // sutra
        Aranzman a2 = new Aranzman(1, "Grčka Leto", datum, 7, 499.99, tip2, grad2);

        assertEquals(1, a2.getAranzmanID());
        assertEquals("Grčka Leto", a2.getNaziv());
        assertEquals(datum, a2.getDatum());
        assertEquals(7, a2.getBrojNocenja());
        assertEquals(Double.valueOf(499.99), a2.getCena());
        assertEquals(tip2, a2.getTipAranzmana());
        assertEquals(grad2, a2.getGrad());
    }

    @Test
    public void testKonstruktor_NullTipAranzmana() {
        Grad grad2 = new Grad(1, "Atina", "Grčka", "Prestonica Grčke");
        Date datum = new Date(System.currentTimeMillis() + 86400000);
        Exception e = assertThrows(NullPointerException.class,
                () -> new Aranzman(1, "Grčka Leto", datum, 7, 499.99, null, grad2));
        assertEquals("Tip aranzmana ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetNaziv_Validno() {
        a.setNaziv("Egipat avantura");
        assertEquals("Egipat avantura", a.getNaziv());
    }

    @Test
    public void testSetNaziv_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> a.setNaziv(null));
        assertEquals("Naziv aranzmana ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetNaziv_PrazanString() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> a.setNaziv(""));
        assertEquals("Naziv aranzmana ne sme biti prazan", e.getMessage());
    }

    /*
    @Test
    public void testSetDatum_ValidanDatum() {
        Date buduci = new Date(System.currentTimeMillis() + 172800000);
        a.setDatum(buduci);
        assertEquals(buduci, a.getDatum());
    }

    @Test
    public void testSetDatum_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> a.setDatum(null));
        assertEquals("Datum aranzmana ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetDatum_ProsliDatum() {
        Date prosli = new Date(System.currentTimeMillis() - 86400000);
        Exception e = assertThrows(IllegalArgumentException.class, () -> a.setDatum(prosli));
        assertEquals("Datum aranzmana ne sme biti u proslosti", e.getMessage());
    }

     */
    @Test
    public void testSetDatum_ValidanDatum() {
        Date buduciDatum = new Date(System.currentTimeMillis() + 86400000); // sutra
        a.setDatum(buduciDatum);
        assertEquals(buduciDatum, a.getDatum());
    }

    @Test
    public void testSetDatum_DanasnjiDatum() {
        Date danas = new Date();
        a.setDatum(danas); // danas dozvoljen
        assertEquals(danas, a.getDatum());
    }

    @Test
    public void testSetDatum_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> a.setDatum(null));
        assertEquals("Datum aranzmana ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetDatum_Proslost() {
        Date prosliDatum = new Date(System.currentTimeMillis() - 172800000); // pre 2 dana
        Exception e = assertThrows(IllegalArgumentException.class, () -> a.setDatum(prosliDatum));
        assertEquals("Datum aranzmana ne sme biti u proslosti", e.getMessage());
    }

    @Test
    public void testSetBrojNocenja_Validno() {
        a.setBrojNocenja(10);
        assertEquals(10, a.getBrojNocenja());
    }

    @Test
    public void testSetBrojNocenja_ManjeOdJedan() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> a.setBrojNocenja(0));
        assertEquals("Broj nocenja mora biti veci od nula", e.getMessage());
    }

    @Test
    public void testSetCena_Validna() {
        a.setCena(899.99);
        assertEquals(899.99, a.getCena());
    }

    @Test
    public void testSetCena_NullVrednost() {
        Exception e = assertThrows(NullPointerException.class, () -> a.setCena(null));
        assertEquals("Cena ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetCena_ManjaOdNule() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> a.setCena(-100.0));
        assertEquals("Cena mora biti veca od nula", e.getMessage());
    }

    @Test
    public void testSetTipAranzmana_Validan() {
        TipAranzmana noviTip = new TipAranzmana(2, "Zimovanje");
        a.setTipAranzmana(noviTip);
        assertEquals(noviTip, a.getTipAranzmana());
    }

    @Test
    public void testSetTipAranzmana_Null() {
        Exception e = assertThrows(NullPointerException.class, () -> a.setTipAranzmana(null));
        assertEquals("Tip aranzmana ne sme biti null", e.getMessage());
    }

    @Test
    public void testSetGrad_Validan() {
        Grad noviGrad = new Grad(3, "Rim", "Italija", "Večni grad");
        a.setGrad(noviGrad);
        assertEquals(noviGrad, a.getGrad());
    }

    @Test
    public void testSetGrad_Null() {
        Exception e = assertThrows(NullPointerException.class, () -> a.setGrad(null));
        assertEquals("Grad ne sme biti null", e.getMessage());
    }

    @Test
    public void testVratiNazivTabele() {
        assertEquals("aranzman", a.vratiNazivTabele());
    }

    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertEquals("naziv,datum,brojNocenja,cena,tipAranzmana,grad", a.vratiKoloneZaUbacivanje());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        assertEquals("aranzman.aranzmanID=" + a.getAranzmanID(), a.vratiPrimarniKljuc());
    }

    @Test
    public void testVratiVrednostiZaUbacivanje() {
        String expected = "'" + a.getNaziv() + "','" + new java.sql.Date(a.getDatum().getTime()) + "',"
                + a.getBrojNocenja() + "," + a.getCena() + "," + a.getTipAranzmana().getTipID() + "," + a.getGrad().getGradID();
        assertEquals(expected, a.vratiVrednostiZaUbacivanje());
    }

    @Test
    public void testVratiVrednostZaIzmenu() {
        String expected = "naziv='" + a.getNaziv() + "', datum='" + new java.sql.Date(a.getDatum().getTime())
                + "', brojNocenja=" + a.getBrojNocenja() + ", cena=" + a.getCena()
                + ", tipAranzmana=" + a.getTipAranzmana().getTipID() + ", grad=" + a.getGrad().getGradID();
        assertEquals(expected, a.vratiVrednostZaIzmenu());
    }

    @Test
    public void testVratiListu() throws Exception {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("aranzmanID")).thenReturn(a.getAranzmanID());
        when(rs.getString("aranzman.naziv")).thenReturn(a.getNaziv());
        when(rs.getDouble("aranzman.cena")).thenReturn(a.getCena());
        when(rs.getTimestamp("aranzman.datum")).thenReturn(new Timestamp(a.getDatum().getTime()));
        when(rs.getInt("aranzman.brojNocenja")).thenReturn(a.getBrojNocenja());

        when(rs.getInt("tipAranzmana.tipID")).thenReturn(tip.getTipID());
        when(rs.getString("tipAranzmana.nazivTipa")).thenReturn(tip.getNazivTipa());

        when(rs.getInt("grad.gradID")).thenReturn(grad.getGradID());
        when(rs.getString("grad.imeGrada")).thenReturn(grad.getNazivGrada());
        when(rs.getString("grad.drzava")).thenReturn(grad.getDrzava());
        when(rs.getString("grad.opis")).thenReturn(grad.getOpis());

        List<ApstraktniDomenskiObjekat> lista = a.vratiListu(rs);
        assertEquals(1, lista.size());

        Aranzman rezultat = (Aranzman) lista.get(0);
        assertEquals(a.getNaziv(), rezultat.getNaziv());
        assertEquals(a.getBrojNocenja(), rezultat.getBrojNocenja());
        assertEquals(a.getTipAranzmana().getNazivTipa(), rezultat.getTipAranzmana().getNazivTipa());
        assertEquals(a.getGrad().getNazivGrada(), rezultat.getGrad().getNazivGrada());
    }
}
