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
 * Predstavlja turisticki aranzman koji nudi turisticka agencija.
 *
 * <p>
 * Klasa implementira interfejs {@link ApstraktniDomenskiObjekat} i omogucava
 * mapiranje objekta na odgovarajucu tabelu u bazi podataka ("aranzman").
 * Aranzman sadrzi informacije o nazivu, datumu, broju nocenja, ceni, tipu
 * aranzmana i gradu u kome se realizuje.</p>
 *
 * @author Andrija Panovic
 */
public class Aranzman implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator aranzmana u bazi podataka.
     */
    private int aranzmanID;

    /**
     * Naziv aranzmana kao String.
     */
    private String naziv;

    /**
     * Datum pocetka aranzmana.
     */
    private Date datum;

    /**
     * Broj nocenja koji aranzman obuhvata.
     */
    private int brojNocenja;

    /**
     * Cena aranzmana kao Double vrednost.
     */
    private Double cena;

    /**
     * Tip aranzmana (letovanje, zimovanje, vikend putovanje i sl.).
     */
    private TipAranzmana tipAranzmana;

    /**
     * Grad u kome se aranzman realizuje.
     */
    private Grad grad;

    /**
     * Podrazumevani konstruktor koji inicijalizuje objekat klase
     * {@code Aranzman} sa atributima koji imaju default vrednosti.
     */
    public Aranzman() {
    }

    /**
     * Konstruktor koji kreira objekat klase {@code Aranzman} sa svim atributima
     * osim grada.
     *
     * @param aranzmanID jedinstveni identifikator aranzmana
     * @param naziv naziv aranzmana
     * @param datum datum pocetka aranzmana
     * @param brojNocenja broj nocenja u okviru aranzmana
     * @param cena cena aranzmana
     * @param tipAranzmana tip aranzmana
     */
    public Aranzman(int aranzmanID, String naziv, Date datum, int brojNocenja, Double cena, TipAranzmana tipAranzmana) {
        this.aranzmanID = aranzmanID;
        this.naziv = naziv;
        this.datum = datum;
        this.brojNocenja = brojNocenja;
        this.cena = cena;
        this.tipAranzmana = tipAranzmana;
    }

    /**
     * Konstruktor koji kreira objekat klase {@code Aranzman} sa svim
     * atributima.
     *
     * @param aranzmanID jedinstveni identifikator aranzmana
     * @param naziv naziv aranzmana
     * @param datum datum pocetka aranzmana
     * @param brojNocenja broj nocenja u okviru aranzmana
     * @param cena cena aranzmana
     * @param tipAranzmana tip aranzmana
     * @param grad grad u kome se aranzman realizuje
     */
    public Aranzman(int aranzmanID, String naziv, Date datum, int brojNocenja, Double cena, TipAranzmana tipAranzmana, Grad grad) {
        this.aranzmanID = aranzmanID;
        this.naziv = naziv;
        this.datum = datum;
        this.brojNocenja = brojNocenja;
        this.cena = cena;
        this.tipAranzmana = tipAranzmana;
        this.grad = grad;
    }

    /**
     * Vraca jedinstveni identifikator aranzmana.
     *
     * @return aranzmanID kao Integer vrednost
     */
    public int getAranzmanID() {
        return aranzmanID;
    }

    /**
     * Postavlja jedinstveni identifikator aranzmana.
     *
     * @param aranzmanID aranzmanID kao Integer vrednost
     */
    public void setAranzmanID(int aranzmanID) {
        this.aranzmanID = aranzmanID;
    }

    /**
     * Vraca naziv aranzmana.
     *
     * @return naziv aranzmana kao String
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv aranzmana.
     *
     * @param naziv naziv aranzmana kao String
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Vraca datum pocetka aranzmana.
     *
     * @return datum aranzmana kao Date
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * Postavlja datum pocetka aranzmana.
     *
     * @param datum datum aranzmana kao Date
     */
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    /**
     * Vraca broj nocenja u okviru aranzmana.
     *
     * @return broj nocenja kao Integer vrednost
     */
    public int getBrojNocenja() {
        return brojNocenja;
    }

    /**
     * Postavlja broj nocenja u okviru aranzmana.
     *
     * @param brojNocenja broj nocenja kao Integer vrednost
     */
    public void setBrojNocenja(int brojNocenja) {
        this.brojNocenja = brojNocenja;
    }

    /**
     * Vraca cenu aranzmana.
     *
     * @return cena aranzmana kao Double vrednost
     */
    public Double getCena() {
        return cena;
    }

    /**
     * Postavlja cenu aranzmana.
     *
     * @param cena cena aranzmana kao Double vrednost
     */
    public void setCena(Double cena) {
        this.cena = cena;
    }

    /**
     * Vraca tip aranzmana.
     *
     * @return tip aranzmana kao objekat klase {@code TipAranzmana}
     */
    public TipAranzmana getTipAranzmana() {
        return tipAranzmana;
    }

    /**
     * Postavlja tip aranzmana.
     *
     * @param tipAranzmana tip aranzmana kao objekat klase {@code TipAranzmana}
     */
    public void setTipAranzmana(TipAranzmana tipAranzmana) {
        this.tipAranzmana = tipAranzmana;
    }

    /**
     * Vraca grad u kome se aranzman realizuje.
     *
     * @return grad kao objekat klase {@code Grad}
     */
    public Grad getGrad() {
        return grad;
    }

    /**
     * Postavlja grad u kome se aranzman realizuje.
     *
     * @param grad grad kao objekat klase {@code Grad}
     */
    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    /**
     * Vraca formatiran string sa osnovnim informacijama o aranzmanu.
     *
     * @return String u formatu: naziv='Naziv', brojNocenja='Broj'
     */
    @Override
    public String toString() {
        return "naziv=" + naziv + ", brojNocenja=" + brojNocenja;
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
     * Poredi dva objekta klase {@code Aranzman} na osnovu naziva i broja
     * nocenja.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako je uneti objekat razlicit od null, ako je klase
     * Aranzman i ako su naziv i broj nocenja isti, u suprotnom {@code false}
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
            int gradID = rs.getInt("grad.gradID");
            String imeGrada = rs.getString("grad.imeGrada");
            String drzava = rs.getString("grad.drzava");
            String opis = rs.getString("grad.opis");
            Grad grad = new Grad(gradID, imeGrada, drzava, opis);

            Aranzman a = new Aranzman(aranzmanid, naziv, datum, brNocenja, cena, tip, grad);
            lista.add(a);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,datum,brojNocenja,cena,tipAranzmana,grad";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "','" + new java.sql.Date(datum.getTime()) + "'," + brojNocenja + "," + cena + "," + tipAranzmana.getTipID() + "," + grad.getGradID();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "aranzman.aranzmanID=" + aranzmanID;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='" + naziv + "', datum='" + new java.sql.Date(datum.getTime()) + "', brojNocenja=" + brojNocenja + ", cena=" + cena + ", tipAranzmana=" + tipAranzmana.getTipID() + ", grad=" + grad.getGradID();
    }

}
