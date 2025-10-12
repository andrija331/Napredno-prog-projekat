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
 *
 * @author KORISNIK
 */
public class TipAranzmana implements ApstraktniDomenskiObjekat {

    private int tipID;
    private String nazivTipa;

    public TipAranzmana() {
    }

    public TipAranzmana(int tipID, String nazivTipa) {
        this.tipID = tipID;
        this.nazivTipa = nazivTipa;
    }

    public int getTipID() {
        return tipID;
    }

    public void setTipID(int tipID) {
        this.tipID = tipID;
    }

    public String getNazivTipa() {
        return nazivTipa;
    }

    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }

    @Override
    public String toString() {
        return nazivTipa;
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
        final TipAranzmana other = (TipAranzmana) obj;
        return Objects.equals(this.nazivTipa, other.nazivTipa);
    }

    @Override
    public String vratiNazivTabele() {
        return "tipAranzmana";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int tip = rs.getInt("tipID");

            String naziv = rs.getString("tipAranzmana.nazivTipa");

            TipAranzmana tipAr = new TipAranzmana(tip, naziv);
            lista.add(tipAr);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivTipa";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + nazivTipa + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "tipAranzmana.tipID=" + tipID;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
