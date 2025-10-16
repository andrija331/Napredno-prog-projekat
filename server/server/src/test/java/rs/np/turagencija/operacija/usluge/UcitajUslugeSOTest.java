/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.operacija.usluge;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.np.turagencija.domen.FakultativnaUsluga;
import rs.np.turagencija.repository.db.DbConnectionFactory;

/**
 *
 * @author KORISNIK
 */
public class UcitajUslugeSOTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/turisticka_agencija_test", "root", "");
        connection.setAutoCommit(false);
        DbConnectionFactory.getInst().setTestConnection(connection);

        try ( Statement st = connection.createStatement()) {
            st.executeUpdate("DELETE FROM rezervacija");
            st.executeUpdate("DELETE FROM stavkarezervacije");
            st.executeUpdate("DELETE FROM fakultativnausluga");
            st.executeUpdate("INSERT INTO fakultativnausluga (naziv, opis, cena) VALUES ('Izlet na Avali', 'Organizovan izlet sa vodicem', 2500)");
            st.executeUpdate("INSERT INTO fakultativnausluga (naziv, opis, cena) VALUES ('Krstarenje Savom', 'Panoramsko krstarenje brodom', 4000)");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {

        connection.close();
    }

    @Test
    public void testUcitajSveUsluge() throws Exception {
        UcitajUslugeSO so = new UcitajUslugeSO();
        so.izvrsi(new FakultativnaUsluga(), null);

        List<FakultativnaUsluga> usluge = so.getUsluge();

        assertNotNull(usluge);
        assertEquals(2, usluge.size());
        assertEquals("Izlet na Avali", usluge.get(0).getNaziv());
        assertEquals("Krstarenje Savom", usluge.get(1).getNaziv());
    }
}
