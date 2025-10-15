/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import rs.np.turagencija.domen.ApstraktniDomenskiObjekat;

/**
 * Predstavlja klijenta turisticke agencije koji ima svoj jedinstveni ID, ime,
 * prezime, email adresu i broj telefona.
 *
 * <p>
 * Klasa implementira interfejs {@link ApstraktniDomenskiObjekat} i omogucava
 * mapiranje objekta na odgovarajucu tabelu u bazi podataka ("klijent"). Koristi
 * se u operacijama dodavanja, pretrage, azuriranja i brisanja podataka o
 * klijentima.</p>
 *
 * @author Andrija Panovic
 */
public class Klijent implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator klijenta u bazi podataka.
     */
    private int klijentID;

    /**
     * Ime klijenta kao String.
     */
    private String ime;

    /**
     * Prezime klijenta kao String.
     */
    private String prezime;

    /**
     * Email adresa klijenta kao String.
     */
    private String email;

    /**
     * Broj telefona klijenta kao Long.
     */
    private long brojTelefona;

    /**
     * Podrazumevani konstruktor koji inicijalizuje objekat sa atributima koji
     * imaju default vrednosti.
     */
    public Klijent() {
    }

    /**
     * Konstruktor koji kreira objekat klase {@code Klijent} sa svim relevantnim
     * podacima.
     *
     * @param klijentID jedinstveni identifikator klijenta
     * @param ime ime klijenta
     * @param prezime prezime klijenta
     * @param email email adresa klijenta
     * @param brojTelefona broj telefona klijenta
     */
    public Klijent(int klijentID, String ime, String prezime, String email, long brojTelefona) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.brojTelefona = brojTelefona;
    }

    /**
     * Vraca formatiran string sa osnovnim podacima o klijentu.
     *
     * @return Sting sa podacima o klijentu u formatu :ime='Ime',
     * prezime='Prezime', email='Email'
     */
    @Override
    public String toString() {
        return "ime=" + ime + ", prezime=" + prezime + ", email=" + email;
    }

    /**
     * @return jedinstveni identifikator klijenta
     */
    public int getKlijentID() {
        return klijentID;
    }

    /**
     * Postavlja jedinstveni identifikator klijenta
     *
     * @param KlijentID KlijentID- jedinstveni identifikator klijenta kao
     * Integer
     */
    public void setKlijentID(int KlijentID) {
        this.klijentID = KlijentID;
    }

    /**
     * Vraca ime klijenta
     *
     * @return ime klijenta kao String
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja novo ime klijenta
     *
     * @param ime Ime klijenta kao String
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Vraca prezime klijenta
     *
     * @return prezime klijenta kao String
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja novo prezime klijenta
     *
     * @param prezime Prezime klijenta kao String
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * Vraca email klijenta
     *
     * @return email klijenta kao String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja novi email Klijenta
     *
     * @param email Email klijenta kao String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Vraca broj telefona klijenta
     *
     * @return broj telefona klijenta kao Long
     */
    public long getBrojTelefona() {
        return brojTelefona;
    }

    /**
     * Postavlja novi broj telefona klijenta
     *
     * @param brojTelefona broj telefona klijenta kao Long
     */
    public void setBrojTelefona(long brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    /**
     * Racuna hash kod objekta na osnovu default vrednosti.
     *
     * @return hash vrednost objekta
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Poredi dva objekta klase {@code Klijent} na osnovu email adrese.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako je uneti objeakt razlicit od null, ako je klase
     * Klijent i ako su email adrese iste, u suprotnom {@code false}
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
        final Klijent other = (Klijent) obj;
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int klijentid = rs.getInt("klijentID");
            long brTel = rs.getInt("brojTelefona");
            String ime = rs.getString("klijent.ime");
            String prezime = rs.getString("klijent.prezime");
            String email = rs.getString("klijent.email");
            Klijent k = new Klijent(klijentid, ime, prezime, email, brTel);
            lista.add(k);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,brojTelefona";

    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + email + "'," + brojTelefona;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "klijent.klijentID=" + klijentID;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', email='" + email + "', brojTelefona=" + brojTelefona;
    }

}
