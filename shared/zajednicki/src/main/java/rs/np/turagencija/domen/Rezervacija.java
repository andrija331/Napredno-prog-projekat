/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author KORISNIK
 */
public class Rezervacija implements ApstraktniDomenskiObjekat {

    private int rezervacijaID;
    private Zaposleni zaposleni;
    private Klijent klijent;
    private Aranzman aranzman;
    private List<StavkaRezervacije> stavke;
    private Date datum;
    private Double ukupnaCena;

    public Rezervacija() {
    }

    public Rezervacija(int rezervacijaID, Zaposleni zaposleni, Klijent klijent, Aranzman aranzman, List<StavkaRezervacije> stavke, Date datum, Double ukupnaCena) {
        this.rezervacijaID = rezervacijaID;
        this.zaposleni = zaposleni;
        this.klijent = klijent;
        this.aranzman = aranzman;
        this.stavke = stavke;
        this.datum = datum;
        this.ukupnaCena = ukupnaCena;
    }

    public int getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Aranzman getAranzman() {
        return aranzman;
    }

    public void setAranzman(Aranzman aranzman) {
        this.aranzman = aranzman;
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRezervacije> stavke) {
        this.stavke = stavke;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(Double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    @Override
    public String toString() {
        return "Rezervacija{" + "rezervacijaID=" + rezervacijaID + ", stavke=" + stavke + '}';
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
        final Rezervacija other = (Rezervacija) obj;
        if (!Objects.equals(this.klijent, other.klijent)) {
            return false;
        }
        return Objects.equals(this.aranzman, other.aranzman);
    }

    @Override
    public String vratiNazivTabele() {
        return "rezervacija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Rezervacija rez = new Rezervacija();
            rez.setRezervacijaID(rs.getInt("rezervacijaID"));
            rez.setUkupnaCena(rs.getDouble("ukupnaCena"));
            rez.setDatum(rs.getDate("rezervacija.datum"));

            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setZaposleniID(rs.getInt("zaposleniID"));
            zaposleni.setIme(rs.getString("zaposleni.ime"));
            zaposleni.setPrezime(rs.getString("zaposleni.prezime"));
            zaposleni.setUsername(rs.getString("zaposleni.username"));
            zaposleni.setPassword(rs.getString("zaposleni.password"));

            Klijent klijent = new Klijent();
            klijent.setKlijentID(rs.getInt("klijentID"));
            klijent.setIme(rs.getString("klijent.ime"));
            klijent.setPrezime(rs.getString("klijent.prezime"));
            klijent.setEmail(rs.getString("klijent.email"));
            klijent.setBrojTelefona(rs.getLong("klijent.brojTelefona"));

            TipAranzmana tip = new TipAranzmana();
            tip.setTipID(rs.getInt("tipID"));
            tip.setNazivTipa(rs.getString("tipAranzmana.nazivTipa"));

            Aranzman aranzman = new Aranzman();
            aranzman.setAranzmanID(rs.getInt("aranzmanID"));
            aranzman.setNaziv(rs.getString("aranzman.naziv"));
            aranzman.setBrojNocenja(rs.getInt("aranzman.brojNocenja"));
            aranzman.setCena(rs.getDouble("aranzman.cena"));
            aranzman.setDatum(rs.getDate("aranzman.datum"));
            aranzman.setTipAranzmana(tip);

            rez.setZaposleni(zaposleni);
            rez.setKlijent(klijent);
            rez.setAranzman(aranzman);
            rez.setStavke(new ArrayList<>());

            lista.add(rez);
        }
        return lista;

    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum,ukupnacena,zaposleni,klijent,aranzman";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + new java.sql.Date(datum.getTime()) + "'," + ukupnaCena + "," + zaposleni.getZaposleniID() + "," + klijent.getKlijentID() + "," + aranzman.getAranzmanID();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "rezervacijaID=" + rezervacijaID;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "ukupnaCena=" + ukupnaCena + ", datum='" + new java.sql.Date(datum.getTime()) + "', aranzman=" + aranzman.getAranzmanID()
                + ", klijent=" + klijent.getKlijentID() + ", zaposleni=" + zaposleni.getZaposleniID();
    }

}
