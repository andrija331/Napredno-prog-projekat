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

        DbConnectionFactory factory = DbConnectionFactory.getInst();
        factory.setTestMode(true);
        factory.setTestConnection(connection);

    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.rollback();
    }

    @Test
    public void testDodajKlijentaUBazu() throws Exception {
        Klijent k = new Klijent();
        k.setIme("TestIme");
        k.setPrezime("TestPrezime");

        DodajKlijentaSO so = new DodajKlijentaSO();

        so.izvrsi(k, null);

        PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM klijent WHERE ime=? AND prezime=?");
        ps.setString(1, "TestIme");
        ps.setString(2, "TestPrezime");
        ResultSet rs = ps.executeQuery();

        assertTrue(rs.next());
        assertTrue(rs.getInt(1) >= 1);
        rs.close();
        ps.close();
    }
}
