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
public class IzmeniAranzmanSOTest {

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

            st.executeUpdate("INSERT INTO grad (gradID, imeGrada, drzava, opis) VALUES (1, 'Rim', 'Italija', 'Grad istorije')");
            st.executeUpdate("INSERT INTO tiparanzmana (tipID, nazivTipa) VALUES (1, 'Letovanje')");

            st.executeUpdate("INSERT INTO aranzman (aranzmanID, naziv, datum, brojNocenja, cena, tipAranzmana, grad) "
                    + "VALUES (1, 'Rim 2025', '2025-06-10', 5, 60000, 1, 1)");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void testIzmeniAranzman() throws Exception {

        int id = 0;
        try ( Statement st = connection.createStatement();  ResultSet rs = st.executeQuery("SELECT aranzmanID FROM aranzman WHERE naziv='Rim 2025'")) {
            if (rs.next()) {
                id = rs.getInt(1);
            }
        }
        assertNotEquals(0, id);

        Aranzman a = new Aranzman();
        a.setAranzmanID(id);
        a.setNaziv("Rim 2025 - Nova tura");
        a.setDatum(Date.valueOf("2025-06-15"));
        a.setBrojNocenja(6);
        a.setCena(65000.0);
        a.setTipAranzmana(new TipAranzmana(1, "Letovanje"));
        a.setGrad(new Grad(1, "Rim", "Italija", "Grad istorije"));

        IzmeniAranzmanSO so = new IzmeniAranzmanSO();
        so.izvrsi(a, null);

        PreparedStatement ps = connection.prepareStatement(
                "SELECT naziv, brojNocenja, cena FROM aranzman WHERE aranzmanID=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        assertTrue(rs.next());
        assertEquals("Rim 2025 - Nova tura", rs.getString("naziv"), "Naziv aranžmana nije izmenjen.");
        assertEquals(6, rs.getInt("brojNocenja"), "Broj noćenja nije ažuriran.");
        assertEquals(65000, rs.getDouble("cena"), "Cena aranžmana nije ažurirana.");

        rs.close();
        ps.close();

        assertTrue(so.izmenjen());
    }

}
