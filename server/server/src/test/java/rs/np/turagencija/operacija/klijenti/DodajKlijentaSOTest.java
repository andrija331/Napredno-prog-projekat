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
import rs.np.turagencija.domen.Grad;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.repository.db.DbConnectionFactory;

/**
 *
 * @author KORISNIK
 */
public class DodajKlijentaSOTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/turisticka_agencija_test", "root", "");
        connection.setAutoCommit(false);
        DbConnectionFactory.getInst().setTestConnection(connection);

        Statement st = connection.createStatement();
        st.executeUpdate("DELETE FROM klijent");
        st.close();

    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.close();

    }

    @Test
    public void testDodajKlijentaUBazu() throws Exception {
        Klijent k = new Klijent();
        k.setIme("TestIme");
        k.setPrezime("TestPrezime");

        DodajKlijentaSO so = new DodajKlijentaSO();

        so.izvrsi(k, null);

        Statement st = connection.createStatement();
        ResultSet rsUkupnoKlijenata = st.executeQuery("SELECT COUNT(*) FROM klijent");

        assertTrue(rsUkupnoKlijenata.next());
        assertEquals(1, rsUkupnoKlijenata.getInt(1), "U bazi ne postoji tačno jedan klijent — moguce dupliranje ili visak podataka.");

        rsUkupnoKlijenata.close();
        st.close();

        PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM klijent WHERE ime=? AND prezime=?");
        ps.setString(1, "TestIme");
        ps.setString(2, "TestPrezime");
        ResultSet rs = ps.executeQuery();

        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1), "Klijent nije uspešno dodat u bazu.");
        rs.close();
        ps.close();
    }

    @Test
    public void testDodajKlijentaParametarNull() {
        DodajKlijentaSO so = new DodajKlijentaSO();
        Exception e = assertThrows(java.lang.Exception.class, () -> so.izvrsi(null, null));
        assertEquals(e.getMessage(), "Prosledjeni objekat nije instanca klase Klijent ili je null.");
    }

    @Test
    public void testDodajKlijentaPogresanTipObjekta() {
        DodajKlijentaSO so = new DodajKlijentaSO();
        Exception e = assertThrows(Exception.class, () -> so.izvrsi(new Grad(), null));
        assertEquals("Prosledjeni objekat nije instanca klase Klijent ili je null.", e.getMessage());
    }
}
