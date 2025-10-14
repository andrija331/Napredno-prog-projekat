/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.operacija.klijenti;

import java.sql.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.repository.db.DbConnectionFactory;

/**
 *
 * @author KORISNIK
 */
public class ObrisiKlijentaSOTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/turisticka_agencija_test",
                "root", ""
        );
        connection.setAutoCommit(false);
        DbConnectionFactory.getInst().setTestConnection(connection);

        try ( Statement st = connection.createStatement()) {

            st.executeUpdate("DELETE FROM rezervacija");
            st.executeUpdate("DELETE FROM aranzman");
            st.executeUpdate("DELETE FROM grad");
            st.executeUpdate("DELETE FROM tiparanzmana");
            st.executeUpdate("DELETE FROM klijent");
            st.executeUpdate("DELETE FROM zaposleni");

            st.executeUpdate(
                    "INSERT INTO klijent (klijentID, ime, prezime, email, brojTelefona) "
                    + "VALUES (1, 'Za', 'Brisanje', 'za@mail.com', 123456)"
            );

            st.executeUpdate(
                    "INSERT INTO grad (gradID, imeGrada, drzava, opis) "
                    + "VALUES (1, 'Atina', 'Grcka', 'Prelep grad sa antickom istorijom')"
            );

            st.executeUpdate(
                    "INSERT INTO tiparanzmana (tipID, nazivTipa) "
                    + "VALUES (1, 'Letovanje')"
            );

            st.executeUpdate(
                    "INSERT INTO aranzman (aranzmanID, naziv, datum, brojNocenja, cena, tipAranzmana, grad) "
                    + "VALUES (1, 'Letovanje u Atini', '2025-11-15',8, 50000, 1, 1)"
            );

            st.executeUpdate(
                    "INSERT INTO zaposleni (zaposleniID, ime, prezime, username, password) "
                    + "VALUES (1, 'Petar', 'Petrović', 'ppetrovic', '123')"
            );

            st.executeUpdate(
                    "INSERT INTO rezervacija (rezervacijaID, datum, ukupnaCena, zaposleni, klijent, aranzman) "
                    + "VALUES (1, '2025-10-15', 50000, 1, 1, 1)"
            );

        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        /*
        try ( Statement st = connection.createStatement()) {

            st.executeUpdate("DELETE FROM rezervacija");
            st.executeUpdate("DELETE FROM aranzman");
            st.executeUpdate("DELETE FROM grad");
            st.executeUpdate("DELETE FROM tiparanzmana");
            st.executeUpdate("DELETE FROM klijent");
            st.executeUpdate("DELETE FROM zaposleni");
        }
         */
        connection.close();
    }

    @Test
    public void testObrisiKlijentaSaRezervacijama() throws Exception {

        try ( ResultSet rs = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM rezervacija WHERE klijent=1")) {
            assertTrue(rs.next());
            assertEquals(1, rs.getInt(1), "Pre brisanja mora postojati jedna rezervacija.");
        }

        Klijent k = new Klijent(1, "Za", "Brisanje", "za@mail.com", 123456);

        System.out.println("Stize do ovde");
        ObrisiKlijentaSO so = new ObrisiKlijentaSO();
        so.izvrsi(k, null);
        System.out.println("Ali ne do ovde");

        try ( ResultSet rs = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM rezervacija WHERE klijent=1")) {
            assertTrue(rs.next());
            assertEquals(0, rs.getInt(1), "Rezervacije povezane sa klijentom nisu obrisane.");
        }

        try ( ResultSet rs = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM klijent WHERE klijentID=1")) {
            assertTrue(rs.next());
            assertEquals(0, rs.getInt(1), "Klijent nije uspešno obrisan iz baze.");
        }

        assertTrue(so.obrisan(), "Sistemska operacija nije označila da je klijent obrisan.");

    }
}
