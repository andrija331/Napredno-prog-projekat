/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.np.turagencija.operacija.rezervacije.stavke;

import java.sql.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.np.turagencija.domen.StavkaRezervacije;
import rs.np.turagencija.repository.db.DbConnectionFactory;

/**
 *
 * @author KORISNIK
 */
public class UcitajStavkeSOTest {

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

            st.executeUpdate("INSERT INTO tiparanzmana (tipID, nazivTipa) VALUES (1, 'Letovanje')");
            st.executeUpdate("INSERT INTO grad (gradID, imeGrada, drzava, opis) VALUES (1, 'Atina', 'Grčka', 'Glavni grad Grčke')");
            st.executeUpdate("INSERT INTO klijent (klijentID, ime, prezime, email, brojTelefona) VALUES (1, 'Petar', 'Petrović', 'petar@mail.com', 123456)");
            st.executeUpdate("INSERT INTO zaposleni (zaposleniID, ime, prezime, username, password) VALUES (1, 'Jovan', 'Jovanović', 'jj', '123')");
            st.executeUpdate("INSERT INTO aranzman (aranzmanID, naziv, datum, brojNocenja, cena, tipAranzmana, grad) VALUES (1, 'Grčka', '2025-07-10', 10, 700.0, 1, 1)");
            st.executeUpdate("INSERT INTO fakultativnausluga (uslugaID, naziv, opis, cena) VALUES (1, 'Izlet', 'Dodatni izlet', 50)");
            st.executeUpdate("INSERT INTO rezervacija (rezervacijaID, klijent, zaposleni, aranzman, datum, ukupnaCena) VALUES (1, 1, 1, 1, '2025-07-10', 750.0)");
            st.executeUpdate("INSERT INTO stavkarezervacije (rb, rezervacija, usluga, cena) VALUES (1, 1, 1, 50.0)");

        }

    }

    @AfterEach
    public void tearDown() throws Exception {

        connection.close();
    }

    @Test
    public void testUcitajStavke_Uspesno() throws Exception {
        UcitajStavkeSO so = new UcitajStavkeSO();
        so.izvrsi(1, null);

        List<StavkaRezervacije> lista = so.getStavke();
        assertNotNull(lista, "Lista stavki ne sme biti null.");
        assertEquals(1, lista.size(), "Treba da postoji tačno jedna stavka rezervacije.");

        StavkaRezervacije s = lista.get(0);
        assertEquals(1, s.getRb());
        assertEquals(50.0, s.getCena());
        assertNotNull(s.getUsluga(), "Usluga mora biti učitana preko JOIN-a.");
        assertEquals("Izlet", s.getUsluga().getNaziv());
    }

    @Test
    public void testUcitajStavke_PraznaLista() throws Exception {

        try ( PreparedStatement psRez = connection.prepareStatement(
                "INSERT INTO rezervacija (rezervacijaID, klijent, zaposleni, aranzman, datum, ukupnaCena) VALUES (2, 1, 1, 1, '2025-07-15', 500.0)")) {
            psRez.executeUpdate();
        }

        UcitajStavkeSO so = new UcitajStavkeSO();
        so.izvrsi(2, null);

        List<StavkaRezervacije> lista = so.getStavke();
        assertNotNull(lista);
        assertTrue(lista.isEmpty(), "Za rezervaciju bez stavki lista mora biti prazna.");
    }

    @Test
    public void testUcitajStavke_NePostojecaRezervacija() throws Exception {
        UcitajStavkeSO so = new UcitajStavkeSO();
        so.izvrsi(999, null);

        List<StavkaRezervacije> lista = so.getStavke();
        assertNotNull(lista);
        assertTrue(lista.isEmpty(), "Za nepostojeću rezervaciju lista mora biti prazna.");
    }

}
