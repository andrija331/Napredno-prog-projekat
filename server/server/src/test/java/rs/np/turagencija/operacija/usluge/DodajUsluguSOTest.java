/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.operacija.usluge;

import java.sql.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.np.turagencija.domen.FakultativnaUsluga;
import rs.np.turagencija.domen.Grad;
import rs.np.turagencija.repository.db.DbConnectionFactory;

/**
 *
 * @author KORISNIK
 */
public class DodajUsluguSOTest {

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
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.rollback();
        connection.close();
    }

    @Test
    public void testDodajUsluguUspesno() throws Exception {
        FakultativnaUsluga usluga = new FakultativnaUsluga(0, "Izlet na Avali", "Organizovan izlet na Avali", 2500);

        DodajUsluguSO so = new DodajUsluguSO();
        so.izvrsi(usluga, null);

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM fakultativnausluga");

        assertTrue(rs.next());
        assertEquals("Izlet na Avali", rs.getString("naziv"));
        assertEquals("Organizovan izlet na Avali", rs.getString("opis"));
        assertEquals(2500, rs.getDouble("cena"));
        assertFalse(rs.next());

    }

    @Test
    public void testDodajUsluguParametarNull() {
        DodajUsluguSO so = new DodajUsluguSO();
        Exception e = assertThrows(Exception.class, () -> so.izvrsi(null, null));
        assertEquals("Prosledjeni objekat nije instanca klase FakultativnaUsluga ili je null.", e.getMessage());
    }

    @Test
    public void testDodajUsluguPogresanTipObjekta() {
        DodajUsluguSO so = new DodajUsluguSO();
        Exception e = assertThrows(Exception.class, () -> so.izvrsi(new Grad(), null));
        assertEquals("Prosledjeni objekat nije instanca klase FakultativnaUsluga ili je null.", e.getMessage());
    }
}
