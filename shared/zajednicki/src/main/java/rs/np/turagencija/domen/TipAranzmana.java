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
 * Predstavlja tip aranzmana (na primer: letovanje, zimovanje, vikend putovanje
 * i slicno).
 *
 * <p>
 * Klasa implementira interfejs {@link ApstraktniDomenskiObjekat} i omogucava
 * mapiranje objekta na odgovarajucu tabelu u bazi podataka ("tiparanzmana").
 * Koristi se za kategorizaciju aranzmana u sistemu turisticke agencije.</p>
 *
 * @author Andrija Panovic
 */
public class TipAranzmana implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator tipa aranzmana u bazi podataka.
     */
    private int tipID;

    /**
     * Naziv tipa aranzmana kao String.
     */
    private String nazivTipa;

    /**
     * Podrazumevani konstruktor koji inicijalizuje objekat klase
     * {@code TipAranzmana} sa atributima koji imaju default vrednosti.
     */
    public TipAranzmana() {
    }

    /**
     * Konstruktor koji kreira objekat klase {@code TipAranzmana} sa svim
     * atributima.
     *
     * @param tipID jedinstveni identifikator tipa aranzmana
     * @param nazivTipa naziv tipa aranzmana
     */
    public TipAranzmana(int tipID, String nazivTipa) {
        this.tipID = tipID;
        this.nazivTipa = nazivTipa;
    }

    /**
     * Vraca jedinstveni identifikator tipa aranzmana.
     *
     * @return tipID tipa aranzmana kao Integer vrednost
     */
    public int getTipID() {
        return tipID;
    }

    /**
     * Postavlja jedinstveni identifikator tipa aranzmana.
     *
     * @param tipID tipID tipa aranzmana kao Integer vrednost
     */
    public void setTipID(int tipID) {
        this.tipID = tipID;
    }

    /**
     * Vraca naziv tipa aranzmana.
     *
     * @return naziv tipa aranzmana kao String
     */
    public String getNazivTipa() {
        return nazivTipa;
    }

    /**
     * Postavlja naziv tipa aranzmana.
     *
     * @param nazivTipa naziv tipa aranzmana kao String
     */
    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }

    /**
     * Vraca naziv tipa aranzmana u obliku stringa.
     *
     * @return naziv tipa aranzmana kao String
     */
    @Override
    public String toString() {
        return nazivTipa;
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
     * Poredi dva objekta klase {@code TipAranzmana} na osnovu naziva tipa
     * aranzmana.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako je uneti objekat razlicit od null, ako je klase
     * TipAranzmana i ako je naziv tipa isti, u suprotnom {@code false}
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

        return "nazivTipa='" + nazivTipa + "'";
    }

}
