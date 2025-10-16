/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.operacija.aranzmani;

import java.sql.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.domen.Grad;
import rs.np.turagencija.domen.TipAranzmana;
import rs.np.turagencija.repository.db.DbConnectionFactory;

/**
 *
 * @author KORISNIK
 */
public class DodajAranzmanSOTest {

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
            st.executeUpdate("DELETE FROM aranzman");
            st.executeUpdate("DELETE FROM grad");
            st.executeUpdate("DELETE FROM tiparanzmana");

            st.executeUpdate("INSERT INTO grad (gradID, imeGrada, drzava, opis) VALUES (1, 'Bec', 'Austrija', 'Glavni grad Austrije')");
            st.executeUpdate("INSERT INTO tiparanzmana (tipID, nazivTipa) VALUES (1, 'Vikend putovanje')");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void testDodajAranzman() throws Exception {
        Aranzman a = new Aranzman();
        a.setNaziv("Bec 2025");
        a.setDatum(Date.valueOf("2025-05-01"));
        a.setBrojNocenja(3);
        a.setCena(25000.0);
        a.setTipAranzmana(new TipAranzmana(1, "Vikend putovanje"));
        a.setGrad(new Grad(1, "Bec", "Austrija", "Glavni grad Austrije"));

        DodajAranzmanSO so = new DodajAranzmanSO();
        so.izvrsi(a, null);

        Statement st = connection.createStatement();
        ResultSet rsUkupnoAr = st.executeQuery("SELECT COUNT(*) FROM ARANZMAN");

        assertTrue(rsUkupnoAr.next());
        assertEquals(1, rsUkupnoAr.getInt(1));

        rsUkupnoAr.close();
        st.close();

        PreparedStatement ps = connection.prepareStatement(
                "SELECT COUNT(*) FROM aranzman WHERE naziv='Bec 2025'");
        ResultSet rs = ps.executeQuery();

        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1), "Aranžman nije uspešno dodat u bazu.");

        rs.close();
        ps.close();
    }

    @Test
    public void testDodajAranzmanParametarNull() {
        DodajAranzmanSO so = new DodajAranzmanSO();
        Exception e = assertThrows(java.lang.Exception.class, () -> so.izvrsi(null, null));
        assertEquals(e.getMessage(), "Prosledjeni objekat nije instanca klase Aranzman ili je null.");
    }

    @Test
    public void testDodajAranzmanPogresanTipObjekta() {
        DodajAranzmanSO so = new DodajAranzmanSO();
        Exception e = assertThrows(Exception.class, () -> so.izvrsi(new Grad(), null));
        assertEquals("Prosledjeni objekat nije instanca klase Aranzman ili je null.", e.getMessage());
    }

}
