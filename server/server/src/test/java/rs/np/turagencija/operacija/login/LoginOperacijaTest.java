/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.operacija.login;

import java.sql.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.repository.db.DbConnectionFactory;

/**
 *
 * @author KORISNIK
 */
public class LoginOperacijaTest {

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
            st.executeUpdate("DELETE FROM stavkarezervacije");
            st.executeUpdate("DELETE FROM rezervacija");
            st.executeUpdate("DELETE FROM aranzman");
            st.executeUpdate("DELETE FROM grad");
            st.executeUpdate("DELETE FROM tiparanzmana");
            st.executeUpdate("DELETE FROM fakultativnausluga");
            st.executeUpdate("DELETE FROM klijent");
            st.executeUpdate("DELETE FROM zaposleni");

            st.executeUpdate("INSERT INTO zaposleni (zaposleniID, ime, prezime, username, password) "
                    + "VALUES (1, 'Petar', 'Petrovic', 'ppetrovic', '1234')");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void testUspesanLogin() throws Exception {
        Zaposleni z = new Zaposleni();
        z.setUsername("ppetrovic");
        z.setPassword("1234");

        LoginOperacija so = new LoginOperacija();
        so.izvrsi(z, null);

        Zaposleni rezultat = so.getZaposleni();

        assertNotNull(rezultat, "Login nije uspeo - zaposleni nije pronadjen.");
        assertEquals("Petar", rezultat.getIme(), "Ime pronađenog zaposlenog nije ispravno.");
        assertEquals("Petrovic", rezultat.getPrezime(), "Prezime pronađenog zaposlenog nije ispravno.");
    }

    @Test
    public void testNeuspesanLogin() throws Exception {
        Zaposleni z = new Zaposleni();
        z.setUsername("pogresan");
        z.setPassword("1234");

        LoginOperacija so = new LoginOperacija();
        so.izvrsi(z, null);

        assertNull(so.getZaposleni(), "Login je uspeo za nepostojeće korisničko ime.");
    }
}
