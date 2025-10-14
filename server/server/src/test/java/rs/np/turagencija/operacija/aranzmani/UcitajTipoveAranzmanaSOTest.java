/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.operacija.aranzmani;

import java.sql.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.np.turagencija.domen.TipAranzmana;
import rs.np.turagencija.repository.db.DbConnectionFactory;

/**
 *
 * @author KORISNIK
 */
public class UcitajTipoveAranzmanaSOTest {

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
            st.executeUpdate("DELETE FROM tiparanzmana");

            st.executeUpdate("INSERT INTO tiparanzmana (tipID, nazivTipa) VALUES (1, 'Letovanje')");
            st.executeUpdate("INSERT INTO tiparanzmana (tipID, nazivTipa) VALUES (2, 'Zimovanje')");
            st.executeUpdate("INSERT INTO tiparanzmana (tipID, nazivTipa) VALUES (3, 'Vikend putovanje')");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void testUcitajTipoveAranzmana() throws Exception {
        UcitajTipoveAranzmanaSO so = new UcitajTipoveAranzmanaSO();

        so.izvrsi(new TipAranzmana(), null);

        List<TipAranzmana> tipovi = so.getTipovi();

        assertNotNull(tipovi, "Lista tipova aranžmana ne sme biti null.");
        assertEquals(3, tipovi.size(), "Lista tipova aranžmana ne sadrži očekivani broj redova.");

        boolean postojiLetovanje = tipovi.stream()
                .anyMatch(t -> t.getNazivTipa().equals("Letovanje"));
        assertTrue(postojiLetovanje, "Tip 'Letovanje' nije učitan iz baze.");
    }

}
