/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.operacija.klijenti;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
public class UcitajKlijenteSOTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/turisticka_agencija_test", "root", "");
        connection.setAutoCommit(false);
        DbConnectionFactory.getInst().setTestConnection(connection);

        try ( Statement st = connection.createStatement()) {
            st.executeUpdate("DELETE FROM klijent");
            st.executeUpdate("INSERT INTO klijent (ime, prezime, email, brojTelefona) VALUES ('Marko', 'Markovic', 'marko@mail.com', 123456)");
            st.executeUpdate("INSERT INTO klijent (ime, prezime, email, brojTelefona) VALUES ('Jelena', 'Jovanovic', 'jelena@mail.com', 654321)");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void testUcitajSveKlijente() throws Exception {
        UcitajKlijenteSO so = new UcitajKlijenteSO();
        so.izvrsi(new Klijent(), null);

        List<Klijent> klijenti = new ArrayList<>();
        assertEquals(0, klijenti.size());
        klijenti = so.getKlijenti();

        assertNotNull(klijenti);
        assertEquals(2, klijenti.size(), "Nisu učitana 2 klijenta iz baze.");
        assertEquals("Marko", klijenti.get(0).getIme());
    }

}
