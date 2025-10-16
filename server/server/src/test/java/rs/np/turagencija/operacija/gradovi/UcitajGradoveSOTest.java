/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.operacija.gradovi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.np.turagencija.domen.Grad;
import rs.np.turagencija.repository.db.DbConnectionFactory;

/**
 *
 * @author KORISNIK
 */
public class UcitajGradoveSOTest {

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

            st.executeUpdate("INSERT INTO grad (imeGrada, drzava, opis) VALUES ('Beograd', 'Srbija', 'Glavni grad Srbije')");
            st.executeUpdate("INSERT INTO grad (imeGrada, drzava, opis) VALUES ('Novi Sad', 'Srbija', 'Centar Vojvodine')");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void testUcitajSveGradove() throws Exception {
        UcitajGradoveSO so = new UcitajGradoveSO();
        so.izvrsi(new Grad(), null);

        List<Grad> gradovi = so.getGradovi();

        assertNotNull(gradovi);
        assertEquals(2, gradovi.size());
        assertEquals("Beograd", gradovi.get(0).getNazivGrada());
        assertEquals("Srbija", gradovi.get(0).getDrzava());
    }
}
