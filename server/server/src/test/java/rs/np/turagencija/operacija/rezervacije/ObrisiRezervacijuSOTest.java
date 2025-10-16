/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.operacija.rezervacije;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.domen.FakultativnaUsluga;
import rs.np.turagencija.domen.Grad;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.domen.Rezervacija;
import rs.np.turagencija.domen.StavkaRezervacije;
import rs.np.turagencija.domen.TipAranzmana;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.repository.db.DbConnectionFactory;

/**
 *
 * @author KORISNIK
 */
public class ObrisiRezervacijuSOTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/turisticka_agencija_test", "root", "");
        connection.setAutoCommit(false);
        DbConnectionFactory.getInst().setTestConnection(connection);

        try ( Statement st = connection.createStatement()) {

            st.executeUpdate("DELETE FROM stavkarezervacije");
            st.executeUpdate("DELETE FROM rezervacija");
            st.executeUpdate("DELETE FROM aranzman");
            st.executeUpdate("DELETE FROM grad");
            st.executeUpdate("DELETE FROM tiparanzmana");
            st.executeUpdate("DELETE FROM fakultativnausluga");
            st.executeUpdate("DELETE FROM klijent");
            st.executeUpdate("DELETE FROM zaposleni");

            st.executeUpdate("INSERT INTO tiparanzmana (tipID, nazivTipa) VALUES (1, 'Letovanje')");
            st.executeUpdate("INSERT INTO grad (gradID, imeGrada, drzava, opis) VALUES (1, 'Atina', 'Grcka', 'Glavni grad Grcke')");
            st.executeUpdate("INSERT INTO klijent (klijentID, ime, prezime, email, brojTelefona) VALUES (1, 'Petar', 'Petrovic', 'petar@mail.com', 555555)");
            st.executeUpdate("INSERT INTO zaposleni (zaposleniID, ime, prezime, username, password) VALUES (1, 'Jovan', 'Jovanovic', 'jj', '123')");
            st.executeUpdate("INSERT INTO aranzman (aranzmanID, naziv, datum, brojNocenja, cena, tipAranzmana, grad) VALUES (1, 'Grcka', '2025-07-10', 10, 700.0, 1, 1)");
            st.executeUpdate("INSERT INTO fakultativnausluga (uslugaID, naziv, opis, cena) VALUES (1, 'Izlet', 'Dodatni izlet', 50)");

            st.executeUpdate("INSERT INTO rezervacija (rezervacijaID, klijent, zaposleni, aranzman, datum, ukupnaCena) VALUES (1, 1, 1, 1, '2025-07-10', 750.0)");
            st.executeUpdate("INSERT INTO stavkarezervacije (rb, rezervacija, usluga, cena) VALUES (1, 1, 1, 50.0)");
        }

    }

    @AfterEach
    public void tearDown() throws Exception {

        connection.close();
    }

    @Test
    public void testObrisiRezervaciju() throws Exception {

        Klijent klijent = new Klijent(1, "Petar", "Petrovic", "petar@mail.com", 555555);
        Zaposleni zaposleni = new Zaposleni(1, "Jovan", "Jovanovic", "jj", "123");
        Grad grad = new Grad(1, "Atina", "Grcka", "Glavni grad Grcke");
        TipAranzmana tip = new TipAranzmana(1, "Letovanje");
        Aranzman aranzman = new Aranzman(1, "Grcka", new java.util.Date(), 10, 700.0, tip, grad);

        Rezervacija r = new Rezervacija();
        r.setRezervacijaID(1);
        r.setKlijent(klijent);
        r.setZaposleni(zaposleni);
        r.setAranzman(aranzman);
        r.setDatum(new java.util.Date());
        r.setUkupnaCena(750.0);

        FakultativnaUsluga usluga = new FakultativnaUsluga(1, "Izlet", "Dodatni izlet", 50.0);
        StavkaRezervacije s = new StavkaRezervacije(1, 50, usluga);
        s.setRezervacija(r);
        List<StavkaRezervacije> stavke = new ArrayList<>();
        stavke.add(s);
        r.setStavke(stavke);

        // izvršavanje operacije
        ObrisiRezervacijuSO so = new ObrisiRezervacijuSO();
        so.izvrsi(r, null);

        assertTrue(so.obrisana(), "Rezervacija mora biti označena kao obrisana.");

        // provera da li su stvarno obrisane iz baze
        try ( Statement st = connection.createStatement()) {
            ResultSet rs1 = st.executeQuery("SELECT COUNT(*) FROM stavkarezervacije WHERE rezervacija = 1");
            assertTrue(rs1.next());
            assertEquals(0, rs1.getInt(1), "Sve stavke rezervacije moraju biti obrisane.");

            ResultSet rs2 = st.executeQuery("SELECT COUNT(*) FROM rezervacija WHERE rezervacijaID = 1");
            assertTrue(rs2.next());
            assertEquals(0, rs2.getInt(1), "Rezervacija mora biti obrisana iz baze.");
        }
    }

    @Test
    public void testObrisiRezervacijuParametarNull() {
        ObrisiRezervacijuSO so = new ObrisiRezervacijuSO();
        Exception e = assertThrows(Exception.class, () -> so.izvrsi(null, null));
        assertEquals("Prosledjeni objekat nije instanca klase Rezervacija ili je null.", e.getMessage());
    }

    @Test
    public void testObrisiRezervacijuPogresanTipObjekta() {
        ObrisiRezervacijuSO so = new ObrisiRezervacijuSO();
        Exception e = assertThrows(Exception.class, () -> so.izvrsi(new Grad(), null));
        assertEquals("Prosledjeni objekat nije instanca klase Rezervacija ili je null.", e.getMessage());
    }
}
