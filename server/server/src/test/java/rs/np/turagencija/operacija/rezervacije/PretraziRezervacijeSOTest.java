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
import rs.np.turagencija.domen.Rezervacija;
import rs.np.turagencija.repository.db.DbConnectionFactory;

/**
 *
 * @author KORISNIK
 */
public class PretraziRezervacijeSOTest {

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
            st.executeUpdate("INSERT INTO klijent (klijentID, ime, prezime, email, brojTelefona) VALUES (1, 'Marko', 'Markovic', 'marko@mail.com', 123456)");
            st.executeUpdate("INSERT INTO klijent (klijentID, ime, prezime, email, brojTelefona) VALUES (2, 'Jelena', 'Petrovic', 'jelena@mail.com', 654321)");
            st.executeUpdate("INSERT INTO zaposleni (zaposleniID, ime, prezime, username, password) VALUES (1, 'Jovan', 'Jovanovic', 'jj', '123')");
            st.executeUpdate("INSERT INTO aranzman (aranzmanID, naziv, datum, brojNocenja, cena, tipAranzmana, grad) VALUES (1, 'Grcka', '2025-07-10', 10, 700.0, 1, 1)");
            st.executeUpdate("INSERT INTO fakultativnausluga (uslugaID, naziv, opis, cena) VALUES (1, 'Izlet', 'Dodatni izlet', 50)");
            st.executeUpdate("INSERT INTO rezervacija (rezervacijaID, zaposleni, klijent, aranzman, datum, ukupnaCena) VALUES (1, 1, 1, 1, '2025-07-01', 750.0)");
            st.executeUpdate("INSERT INTO rezervacija (rezervacijaID, zaposleni, klijent, aranzman, datum, ukupnaCena) VALUES (2, 1, 2, 1, '2025-08-01', 700.0)");
            st.executeUpdate("INSERT INTO stavkarezervacije (rezervacija, rb, cena, usluga) VALUES (1, 1, 50, 1)");
            st.executeUpdate("INSERT INTO stavkarezervacije (rezervacija, rb, cena, usluga) VALUES (2, 1, 50, 1)");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {

        connection.close();
    }

    @Test
    public void testPretraziRezervacijePoEmailu() throws Exception {
        PretraziRezervacijeSO so = new PretraziRezervacijeSO();
        String kljuc = "marko@mail.com";
        so.izvrsi(new Rezervacija(), kljuc);

        List<Rezervacija> rezervacije = so.getLista();

        assertNotNull(rezervacije);
        assertEquals(1, rezervacije.size(), "Treba biti pronadjena tacno jedna rezervacija za datog klijenta.");

        Rezervacija r = rezervacije.get(0);
        assertEquals(1, r.getRezervacijaID());
        assertEquals("Marko", r.getKlijent().getIme());
        assertEquals("Grcka", r.getAranzman().getNaziv());
        assertEquals(1, r.getStavke().size());
        assertEquals(50, r.getStavke().get(0).getCena());
    }

    @Test
    public void testPretraziRezervacijeNepostojeciEmail() throws Exception {
        PretraziRezervacijeSO so = new PretraziRezervacijeSO();
        String kljuc = "nepostoji@mail.com";
        so.izvrsi(new Rezervacija(), kljuc);

        List<Rezervacija> rezervacije = so.getLista();
        assertNotNull(rezervacije);
        assertTrue(rezervacije.isEmpty(), "Lista rezervacija treba biti prazna za nepostojeci email.");
    }

}
