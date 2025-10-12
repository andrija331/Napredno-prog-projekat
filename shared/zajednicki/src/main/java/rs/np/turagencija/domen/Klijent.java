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
 *
 * @author KORISNIK
 */
//public class Klijent implements ApstraktniDomenskiObjekat 
public class Klijent implements ApstraktniDomenskiObjekat {

    private int klijentID;
    private String ime;
    private String prezime;
    private String email;
    private long brojTelefona;

    public Klijent() {
    }

    public Klijent(int klijentID, String ime, String prezime, String email, long brojTelefona) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.brojTelefona = brojTelefona;
    }

    @Override
    public String toString() {
        return "ime=" + ime + ", prezime=" + prezime + ", email=" + email;
    }

    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int KlijentID) {
        this.klijentID = KlijentID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(long brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

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
