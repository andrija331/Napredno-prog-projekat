/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja zaposlenog radnika turisticke agencije koji ima svoj ID, ime,
 * prezime, korisnicko ime i lozinku.
 *
 * <p>
 * Klasa implementira interfejs {@link ApstraktniDomenskiObjekat} i omogucava
 * mapiranje zaposlenog na tabelu u bazi podataka. Koristi se za autentifikaciju
 * i administrativne operacije u sistemu.</p>
 *
 * @author Andrija Panovic
 */
public class Zaposleni implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator zaposlenog.
     */
    private int zaposleniID;
    /**
     * Ime zaposlenog.
     */
    private String ime;
    /**
     * Prezime zaposlenog.
     */
    private String prezime;
    /**
     * Korisnicko ime zaposlenog (username).
     */
    private String username;
    /**
     * Lozinka zaposlenog.
     */
    private String password;

    /**
     * Podrazumevani konstruktor koji kreira prazan objekat klase
     * {@code Zaposleni}.
     */
    public Zaposleni() {
    }

    /**
     * Konstruktor koji kreira zaposlenog sa svim atributima.
     *
     * @param zaposleniID jedinstveni identifikator
     * @param ime ime zaposlenog
     * @param prezime prezime zaposlenog
     * @param username korisnicko ime
     * @param password lozinka
     */
    public Zaposleni(int zaposleniID, String ime, String prezime, String username, String password) {
        this.zaposleniID = zaposleniID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    /**
     * Vraca jedinstveni identifikator zaposlenog.
     *
     * @return ID zaposlenog kao Integer vrednost
     */
    public int getZaposleniID() {
        return zaposleniID;
    }

    /**
     * Postavlja jedinstveni identifikator zaposlenog.
     *
     * @param zaposleniID ID zaposlenog kao Integer vrednost
     */
    public void setZaposleniID(int zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    /**
     * Vraca ime zaposlenog.
     *
     * @return ime zaposlenog kao String
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime zaposlenog.
     *
     * @param ime ime zaposlenog kao String
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Vraca prezime zaposlenog.
     *
     * @return prezime zaposlenog kao String
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime zaposlenog.
     *
     * @param prezime prezime zaposlenog kao String
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * Vraca korisnicko ime zaposlenog.
     *
     * @return korisnicko ime zaposlenog kao String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Postavlja korisnicko ime zaposlenog.
     *
     * @param username korisnicko ime zaposlenog kao String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Vraca lozinku zaposlenog.
     *
     * @return lozinka zaposlenog kao String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Postavlja lozinku zaposlenog.
     *
     * @param password lozinka zaposlenog kao String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Vraca formatiran string sa osnovnim podacima o zaposlenom.
     *
     * @return String u formatu: ime='Ime', prezime='Prezime',
     * username='Username'
     */
    @Override
    public String toString() {
        return "ime=" + ime + ", prezime=" + prezime + ", username=" + username;
    }

    /**
     * Racuna hash kod objekta na osnovu default vrednosti.
     *
     * @return hash vrednost objekta
     */
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /**
     * Poredi dva objekta klase {@code Zaposleni} na osnovu korisnickog imena i
     * lozinke.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako je uneti objekat razlicit od null, ako je klase
     * Zaposleni i ako su korisnicka imena i lozinke iste, u suprotnom
     * {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zaposleni other = (Zaposleni) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int zaposleniid = rs.getInt("zaposleniID");
            String ime = rs.getString("zaposleni.ime");
            String prezime = rs.getString("zaposleni.prezime");
            String username = rs.getString("zaposleni.username");
            String password = rs.getString("zaposleni.password");
            Zaposleni z = new Zaposleni(zaposleniid, ime, prezime, username, password);
            lista.add(z);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,username,password";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + username + "','" + password + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "zaposleni.zaposleniID=" + zaposleniID;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', username='" + username + "', password='" + password + "'";
    }

}
