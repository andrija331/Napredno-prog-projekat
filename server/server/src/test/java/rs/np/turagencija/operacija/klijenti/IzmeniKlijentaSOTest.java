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
public class IzmeniKlijentaSOTest {

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

            st.executeUpdate("INSERT INTO klijent (ime, prezime, email, brojTelefona) VALUES ('Stari', 'Klijent', 'marko@mail.com', 123456)");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void testIzmeniKlijenta() throws Exception {
        // preuzmi ID klijenta
        int id = 0;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT klijentID FROM klijent WHERE ime='Stari'");
        if (rs.next()) {
            id = rs.getInt(1);
        }

        Klijent k = new Klijent();
        k.setKlijentID(id);
        k.setIme("Novi");
        k.setPrezime("Klijent");

        IzmeniKlijentaSO so = new IzmeniKlijentaSO();
        so.izvrsi(k, null);

        PreparedStatement ps = connection.prepareStatement("SELECT ime FROM klijent WHERE klijentID=?");
        ps.setInt(1, id);
        ResultSet rs2 = ps.executeQuery();

        assertTrue(rs2.next());
        assertEquals("Novi", rs2.getString("ime"), "Ime klijenta nije izmenjeno u bazi.");
        assertTrue(so.izmenjen());

        rs.close();
        ps.close();
    }

    @Test
    public void testIzmeniKlijentaParametarNull() {
        IzmeniKlijentaSO so = new IzmeniKlijentaSO();
        Exception e = assertThrows(Exception.class, () -> so.izvrsi(null, null));
        assertEquals("Prosledjeni objekat nije instanca klase Klijent ili je null.", e.getMessage());
    }

    @Test
    public void testIzmeniKlijentaPogresanTipObjekta() {
        IzmeniKlijentaSO so = new IzmeniKlijentaSO();
        Exception e = assertThrows(Exception.class, () -> so.izvrsi(new Grad(), null));
        assertEquals("Prosledjeni objekat nije instanca klase Klijent ili je null.", e.getMessage());
    }
}
