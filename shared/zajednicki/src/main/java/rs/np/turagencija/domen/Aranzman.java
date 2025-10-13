/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import rs.np.turagencija.domen.ApstraktniDomenskiObjekat;

/**
 *
 * @author KORISNIK
 */
public class Aranzman implements ApstraktniDomenskiObjekat {

    private int aranzmanID;
    private String naziv;
    private Date datum;
    private int brojNocenja;
    private Double cena;
    private TipAranzmana tipAranzmana;

    public Aranzman() {
    }

    public Aranzman(int aranzmanID, String naziv, Date datum, int brojNocenja, Double cena, TipAranzmana tipAranzmana) {
        this.aranzmanID = aranzmanID;
        this.naziv = naziv;
        this.datum = datum;
        this.brojNocenja = brojNocenja;
        this.cena = cena;
        this.tipAranzmana = tipAranzmana;
    }

    public int getAranzmanID() {
        return aranzmanID;
    }

    public void setAranzmanID(int aranzmanID) {
        this.aranzmanID = aranzmanID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getBrojNocenja() {
        return brojNocenja;
    }

    public void setBrojNocenja(int brojNocenja) {
        this.brojNocenja = brojNocenja;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public TipAranzmana getTipAranzmana() {
        return tipAranzmana;
    }

    public void setTipAranzmana(TipAranzmana tipAranzmana) {
        this.tipAranzmana = tipAranzmana;
    }

    @Override
    public String toString() {
        return "naziv=" + naziv + ", brojNocenja=" + brojNocenja;
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
        final Aranzman other = (Aranzman) obj;
        if (this.brojNocenja != other.brojNocenja) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String vratiNazivTabele() {
        return "aranzman";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int aranzmanid = rs.getInt("aranzmanID");
            String naziv = rs.getString("aranzman.naziv");
            double cena = rs.getDouble("aranzman.cena");
            Timestamp ts = rs.getTimestamp("aranzman.datum");
            Date datum = new Date(ts.getTime());
            int brNocenja = rs.getInt("aranzman.brojNocenja");
            int tipID = rs.getInt("tipAranzmana.tipID");
            String nazivTipa = rs.getString("tipAranzmana.nazivTipa");
            TipAranzmana tip = new TipAranzmana(tipID, nazivTipa);

            Aranzman a = new Aranzman(aranzmanid, naziv, datum, brNocenja, cena, tip);
            lista.add(a);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,datum,brojNocenja,cena,tipAranzmana";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "','" + new java.sql.Date(datum.getTime()) + "'," + brojNocenja + "," + cena + "," + tipAranzmana.getTipID();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "aranzman.aranzmanID=" + aranzmanID;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='" + naziv + "', datum='" + new java.sql.Date(datum.getTime()) + "', brojNocenja=" + brojNocenja + ", cena=" + cena + ", tipAranzmana=" + tipAranzmana.getTipID();
    }

}
