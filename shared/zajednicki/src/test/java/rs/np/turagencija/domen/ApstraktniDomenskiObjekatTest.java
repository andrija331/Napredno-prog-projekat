/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.domen;

import static org.mockito.Mockito.*;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author KORISNIK
 */
public abstract class ApstraktniDomenskiObjekatTest {

    protected ApstraktniDomenskiObjekat ado;

    public abstract ApstraktniDomenskiObjekat getInstance();

    @BeforeEach
    public void setUp() {
        ado = getInstance();
    }

    @AfterEach
    public void tearDown() {
        ado = null;
    }

    @Test
    public void testVratiNazivTabele() {
        assertNotNull(ado);
        String naziv = ado.vratiNazivTabele();

        if (ado instanceof Zaposleni) {
            assertEquals("zaposleni", naziv);
        } else if (ado instanceof Klijent) {
            assertEquals("klijent", naziv);
        } else if (ado instanceof TipAranzmana) {
            assertEquals("tipAranzmana", naziv);
        } else if (ado instanceof FakultativnaUsluga) {
            assertEquals("fakultativnausluga", naziv);
        } else if (ado instanceof Aranzman) {
            assertEquals("aranzman", naziv);
        } else if (ado instanceof Rezervacija) {
            assertEquals("rezervacija", naziv);
        } else if (ado instanceof StavkaRezervacije) {
            assertEquals("stavkaRezervacije", naziv);
        } else {
            fail("Nepoznata klasa za testiranje naziva tabele!");
        }
    }

    @Test
    public void testVratiKoloneZaUbacivanje() {
        assertNotNull(ado);
        String kolone = ado.vratiKoloneZaUbacivanje();

        if (ado instanceof Zaposleni) {
            assertEquals("ime,prezime,username,password", kolone);
        } else if (ado instanceof Klijent) {
            assertEquals("ime,prezime,email,brojTelefona", kolone);
        } else if (ado instanceof TipAranzmana) {
            assertEquals("nazivTipa", kolone);
        } else if (ado instanceof FakultativnaUsluga) {
            assertEquals("naziv,opis,cena", kolone);
        } else if (ado instanceof Aranzman) {
            assertEquals("naziv,datum,brojNocenja,cena,tipAranzmana", kolone);
        } else if (ado instanceof Rezervacija) {
            assertEquals("datum,ukupnacena,zaposleni,klijent,aranzman", kolone);
        } else if (ado instanceof StavkaRezervacije) {
            assertEquals("rb,rezervacija,cena,usluga", kolone);
        } else {
            fail("Nepoznata klasa za testiranje kolona!");
        }
    }

    @Test
    public void testVratiPrimarniKljuc() {
        assertNotNull(ado);
        String pk = ado.vratiPrimarniKljuc();

        if (ado instanceof Zaposleni z) {
            assertEquals("zaposleni.zaposleniID=" + z.getZaposleniID(), pk);
        } else if (ado instanceof Klijent k) {
            assertEquals("klijent.klijentID=" + k.getKlijentID(), pk);
        } else if (ado instanceof TipAranzmana t) {
            assertEquals("tipAranzmana.tipID=" + t.getTipID(), pk);
        } else if (ado instanceof FakultativnaUsluga f) {
            assertEquals("fakultativnausluga.uslugaID=" + f.getUslugaID(), pk);
        } else if (ado instanceof Aranzman a) {
            assertEquals("aranzman.aranzmanID=" + a.getAranzmanID(), pk);
        } else if (ado instanceof Rezervacija r) {
            assertEquals("rezervacijaID=" + r.getRezervacijaID(), pk);
        } else if (ado instanceof StavkaRezervacije s) {
            assertEquals("rb=" + s.getRb() + " AND stavkarezervacije.rezervacija=" + s.getRezervacija().getRezervacijaID(), pk);
        } else {
            fail("Nepoznata klasa za testiranje primarnog ključa!");
        }
    }

    @Test
    public void testVratiVrednostZaIzmenu() {
        assertNotNull(ado);

        if (ado instanceof Zaposleni z) {
            String expected = "ime='" + z.getIme() + "', prezime='" + z.getPrezime()
                    + "', username='" + z.getUsername() + "', password='" + z.getPassword() + "'";
            assertEquals(expected, z.vratiVrednostZaIzmenu());

        } else if (ado instanceof Klijent k) {
            String expected = "ime='" + k.getIme() + "', prezime='" + k.getPrezime()
                    + "', email='" + k.getEmail() + "', brojTelefona=" + k.getBrojTelefona();
            assertEquals(expected, k.vratiVrednostZaIzmenu());

        } else if (ado instanceof TipAranzmana t) {
            String expected = "nazivTipa='" + t.getNazivTipa() + "'";
            assertEquals(expected, t.vratiVrednostZaIzmenu());

        } else if (ado instanceof FakultativnaUsluga f) {
            String expected = "naziv='" + f.getNaziv() + "', opis='" + f.getOpis() + "', cena=" + f.getCena();
            assertEquals(expected, f.vratiVrednostZaIzmenu());

        } else if (ado instanceof Aranzman a) {
            String expected = "naziv='" + a.getNaziv() + "', datum='" + new java.sql.Date(a.getDatum().getTime())
                    + "', brojNocenja=" + a.getBrojNocenja() + ", cena=" + a.getCena()
                    + ", tipAranzmana=" + a.getTipAranzmana().getTipID();
            assertEquals(expected, a.vratiVrednostZaIzmenu());

        } else if (ado instanceof Rezervacija r) {
            String expected = "ukupnaCena=" + r.getUkupnaCena() + ", datum='" + new java.sql.Date(r.getDatum().getTime())
                    + "', aranzman=" + r.getAranzman().getAranzmanID()
                    + ", klijent=" + r.getKlijent().getKlijentID()
                    + ", zaposleni=" + r.getZaposleni().getZaposleniID();
            assertEquals(expected, r.vratiVrednostZaIzmenu());

        } else if (ado instanceof StavkaRezervacije s) {
            String expected = "rb=" + s.getRb() + ", rezervacija=" + s.getRezervacija().getRezervacijaID()
                    + ", cena=" + s.getCena() + ", usluga=" + s.getUsluga().getUslugaID();
            assertEquals(expected, s.vratiVrednostZaIzmenu());

        } else {
            fail("Nepoznata klasa za testiranje vrednosti za izmenu!");
        }
    }

    @Test
    public void testVratiVrednostiZaUbacivanje() {
        assertNotNull(ado);
        String vrednosti = ado.vratiVrednostiZaUbacivanje();

        if (ado instanceof Zaposleni z) {
            String expected = "'" + z.getIme() + "','" + z.getPrezime() + "','" + z.getUsername() + "','" + z.getPassword() + "'";
            assertEquals(expected, vrednosti);

        } else if (ado instanceof Klijent k) {
            String expected = "'" + k.getIme() + "','" + k.getPrezime() + "','" + k.getEmail() + "'," + k.getBrojTelefona();
            assertEquals(expected, vrednosti);

        } else if (ado instanceof TipAranzmana t) {
            String expected = "'" + t.getNazivTipa() + "'";
            assertEquals(expected, vrednosti);

        } else if (ado instanceof FakultativnaUsluga f) {
            String expected = "'" + f.getNaziv() + "','" + f.getOpis() + "'," + f.getCena();
            assertEquals(expected, vrednosti);

        } else if (ado instanceof Aranzman a) {
            String expected = "'" + a.getNaziv() + "','" + new java.sql.Date(a.getDatum().getTime()) + "',"
                    + a.getBrojNocenja() + "," + a.getCena() + "," + a.getTipAranzmana().getTipID();
            assertEquals(expected, vrednosti);

        } else if (ado instanceof Rezervacija r) {
            String expected = "'" + new java.sql.Date(r.getDatum().getTime()) + "'," + r.getUkupnaCena() + ","
                    + r.getZaposleni().getZaposleniID() + "," + r.getKlijent().getKlijentID() + "," + r.getAranzman().getAranzmanID();
            assertEquals(expected, vrednosti);

        } else if (ado instanceof StavkaRezervacije s) {
            String expected = s.getRb() + "," + s.getRezervacija().getRezervacijaID() + ","
                    + s.getCena() + "," + s.getUsluga().getUslugaID();
            assertEquals(expected, vrednosti);

        } else {
            fail("Nepoznata klasa za testiranje vrednosti za ubacivanje!");
        }
    }

    @Test
    public void testVratiListu() throws Exception {
        assertNotNull(ado);

        ResultSet rs = mock(ResultSet.class);

        if (ado instanceof Zaposleni z) {
            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("zaposleniID")).thenReturn(z.getZaposleniID());
            when(rs.getString("zaposleni.ime")).thenReturn(z.getIme());
            when(rs.getString("zaposleni.prezime")).thenReturn(z.getPrezime());
            when(rs.getString("zaposleni.username")).thenReturn(z.getUsername());
            when(rs.getString("zaposleni.password")).thenReturn(z.getPassword());

            var lista = z.vratiListu(rs);
            assertEquals(1, lista.size());
            Zaposleni rez = (Zaposleni) lista.get(0);
            assertEquals(z.getIme(), rez.getIme());
            assertEquals(z.getUsername(), rez.getUsername());
        } else if (ado instanceof Klijent k) {
            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("klijentID")).thenReturn(k.getKlijentID());
            when(rs.getString("klijent.ime")).thenReturn(k.getIme());
            when(rs.getString("klijent.prezime")).thenReturn(k.getPrezime());
            when(rs.getString("klijent.email")).thenReturn(k.getEmail());
            when(rs.getLong("brojTelefona")).thenReturn(k.getBrojTelefona());

            var lista = k.vratiListu(rs);
            assertEquals(1, lista.size());
            Klijent rez = (Klijent) lista.get(0);
            assertEquals(k.getEmail(), rez.getEmail());
        } else if (ado instanceof TipAranzmana t) {
            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("tipID")).thenReturn(t.getTipID());
            when(rs.getString("tipAranzmana.nazivTipa")).thenReturn(t.getNazivTipa());

            var lista = t.vratiListu(rs);
            assertEquals(1, lista.size());
            TipAranzmana rez = (TipAranzmana) lista.get(0);
            assertEquals(t.getNazivTipa(), rez.getNazivTipa());
        } else if (ado instanceof FakultativnaUsluga f) {
            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("uslugaID")).thenReturn(f.getUslugaID());
            when(rs.getString("fakultativnausluga.naziv")).thenReturn(f.getNaziv());
            when(rs.getString("opis")).thenReturn(f.getOpis());
            when(rs.getDouble("fakultativnausluga.cena")).thenReturn(f.getCena());

            var lista = f.vratiListu(rs);
            assertEquals(1, lista.size());
            FakultativnaUsluga rez = (FakultativnaUsluga) lista.get(0);
            assertEquals(f.getNaziv(), rez.getNaziv());
        } else if (ado instanceof Aranzman a) {
            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("aranzmanID")).thenReturn(a.getAranzmanID());
            when(rs.getString("aranzman.naziv")).thenReturn(a.getNaziv());
            when(rs.getDouble("aranzman.cena")).thenReturn(a.getCena());
            when(rs.getInt("aranzman.brojNocenja")).thenReturn(a.getBrojNocenja());
            when(rs.getTimestamp("aranzman.datum")).thenReturn(new Timestamp(a.getDatum().getTime()));
            when(rs.getInt("tipAranzmana.tipID")).thenReturn(a.getTipAranzmana().getTipID());
            when(rs.getString("tipAranzmana.nazivTipa")).thenReturn(a.getTipAranzmana().getNazivTipa());

            var lista = a.vratiListu(rs);
            assertEquals(1, lista.size());
            Aranzman rez = (Aranzman) lista.get(0);
            assertEquals(a.getNaziv(), rez.getNaziv());
            assertEquals(a.getBrojNocenja(), rez.getBrojNocenja());
        } else if (ado instanceof Rezervacija r) {
            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("rezervacijaID")).thenReturn(r.getRezervacijaID());
            when(rs.getDouble("ukupnaCena")).thenReturn(r.getUkupnaCena());
            when(rs.getDate("rezervacija.datum")).thenReturn(new java.sql.Date(r.getDatum().getTime()));

            when(rs.getInt("zaposleniID")).thenReturn(r.getZaposleni().getZaposleniID());
            when(rs.getString("zaposleni.ime")).thenReturn(r.getZaposleni().getIme());
            when(rs.getString("zaposleni.prezime")).thenReturn(r.getZaposleni().getPrezime());

            when(rs.getInt("klijentID")).thenReturn(r.getKlijent().getKlijentID());
            when(rs.getString("klijent.ime")).thenReturn(r.getKlijent().getIme());
            when(rs.getString("klijent.prezime")).thenReturn(r.getKlijent().getPrezime());
            when(rs.getString("klijent.email")).thenReturn(r.getKlijent().getEmail());
            when(rs.getLong("klijent.brojTelefona")).thenReturn(r.getKlijent().getBrojTelefona());

            when(rs.getInt("aranzmanID")).thenReturn(r.getAranzman().getAranzmanID());
            when(rs.getString("aranzman.naziv")).thenReturn(r.getAranzman().getNaziv());
            when(rs.getInt("tipID")).thenReturn(r.getAranzman().getTipAranzmana().getTipID());
            when(rs.getString("tipAranzmana.nazivTipa")).thenReturn(r.getAranzman().getTipAranzmana().getNazivTipa());

            var lista = r.vratiListu(rs);
            assertEquals(1, lista.size());
            Rezervacija rez = (Rezervacija) lista.get(0);
            assertEquals(r.getRezervacijaID(), rez.getRezervacijaID());
            assertEquals(r.getKlijent().getEmail(), rez.getKlijent().getEmail());
        } else if (ado instanceof StavkaRezervacije s) {
            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("rb")).thenReturn(s.getRb());
            when(rs.getDouble("stavkaRezervacije.cena")).thenReturn(s.getCena());
            when(rs.getString("fakultativnausluga.naziv")).thenReturn(s.getUsluga().getNaziv());
            when(rs.getString("fakultativnausluga.opis")).thenReturn(s.getUsluga().getOpis());
            when(rs.getDouble("fakultativnausluga.cena")).thenReturn(s.getUsluga().getCena());
            when(rs.getInt("fakultativnausluga.uslugaID")).thenReturn(s.getUsluga().getUslugaID());

            var lista = s.vratiListu(rs);
            assertEquals(1, lista.size());
            StavkaRezervacije rez = (StavkaRezervacije) lista.get(0);
            assertEquals(s.getUsluga().getNaziv(), rez.getUsluga().getNaziv());
        } else {
            fail("Nepoznata klasa za testiranje metode vratiListu!");
        }
    }

}
