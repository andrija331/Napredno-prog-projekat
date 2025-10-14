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
import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.repository.db.DbConnectionFactory;

/**
 *
 * @author KORISNIK
 */
public class UcitajAranzmaneSOTest {

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

            st.executeUpdate("INSERT INTO grad (gradID, imeGrada, drzava, opis) VALUES (1, 'Prag', 'Ceska', 'Prelep grad sa mostovima')");
            st.executeUpdate("INSERT INTO tiparanzmana (tipID, nazivTipa) VALUES (1, 'Letovanje')");
            st.executeUpdate("INSERT INTO aranzman (aranzmanID, naziv, datum, brojNocenja, cena,tipAranzmana ,grad ) "
                    + "VALUES (1, 'Put u Prag', '2025-06-15', 5, 45000, 1, 1)");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void testUcitajAranzmane() throws Exception {
        UcitajAranzmaneSO so = new UcitajAranzmaneSO();

        so.izvrsi(new Aranzman(), null);

        List<Aranzman> lista = so.getListaAr();

        assertNotNull(lista, "Lista ne sme biti null.");
        assertFalse(lista.isEmpty(), "Lista aranžmana ne sme biti prazna.");
        assertEquals("Put u Prag", lista.get(0).getNaziv(), "Naziv prvog aranžmana nije očekivan.");
    }
}
