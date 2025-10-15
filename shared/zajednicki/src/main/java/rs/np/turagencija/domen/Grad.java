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
 * Predstavlja grad u kome se nalazi ili realizuje odredjeni turisticki
 * aranzman.
 *
 * <p>
 * Klasa implementira interfejs {@link ApstraktniDomenskiObjekat} i omogucava
 * mapiranje objekta na odgovarajucu tabelu u bazi podataka ("grad"). Grad
 * sadrzi osnovne informacije o nazivu grada, drzavi i opisu destinacije.
 * Koristi se prilikom kreiranja i pregleda aranzmana.</p>
 *
 * @author Andrija Panovic
 */
public class Grad implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator grada u bazi podataka.
     */
    private int gradID;

    /**
     * Naziv grada kao String.
     */
    private String imeGrada;

    /**
     * Naziv drzave u kojoj se grad nalazi.
     */
    private String drzava;

    /**
     * Opis grada (turisticke znamenitosti, istorija, lokacija i sl.).
     */
    private String opis;

    /**
     * Podrazumevani konstruktor koji inicijalizuje objekat klase {@code Grad}
     * sa atributima koji imaju default vrednosti.
     */
    public Grad() {
    }

    /**
     * Konstruktor koji kreira objekat klase {@code Grad} sa svim atributima.
     *
     * @param gradID jedinstveni identifikator grada
     * @param imeGrada ime grada
     * @param drzava naziv drzave u kojoj se grad nalazi
     * @param opis opis grada
     */
    public Grad(int gradID, String imeGrada, String drzava, String opis) {
        this.gradID = gradID;
        this.imeGrada = imeGrada;
        this.drzava = drzava;
        this.opis = opis;
    }

    /**
     * Vraca jedinstveni identifikator grada.
     *
     * @return jedinstveni identifikator grada kao Integer vrednost
     */
    public int getGradID() {
        return gradID;
    }

    /**
     * Postavlja jedinstveni identifikator grada.
     *
     * @param gradID gradID grada kao Integer vrednost
     */
    public void setGradID(int gradID) {
        this.gradID = gradID;
    }

    /**
     * Vraca ime grada.
     *
     * @return ime grada kao String
     */
    public String getNazivGrada() {
        return imeGrada;
    }

    /**
     * Postavlja ime grada.
     *
     * @param nazivGrada naziv grada kao String
     */
    public void setNazivGrada(String nazivGrada) {
        this.imeGrada = nazivGrada;
    }

    /**
     * Vraca naziv drzave u kojoj se grad nalazi.
     *
     * @return naziv drzave kao String
     */
    public String getDrzava() {
        return drzava;
    }

    /**
     * Postavlja naziv drzave u kojoj se grad nalazi.
     *
     * @param drzava naziv drzave kao String
     */
    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    /**
     * Vraca opis grada.
     *
     * @return opis grada kao String
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis grada.
     *
     * @param opis opis grada kao String
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * Vraca ime grada kao string reprezentaciju objekta.
     *
     * @return ime grada kao String
     */
    @Override
    public String toString() {
        return imeGrada;
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
     * Poredi dva objekta klase {@code Grad} na osnovu imena grada i drzave.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako je uneti objekat razlicit od null, ako je klase
     * Grad i ako su ime grada i drzava isti, u suprotnom {@code false}
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
        final Grad other = (Grad) obj;
        if (!Objects.equals(this.imeGrada, other.imeGrada)) {
            return false;
        }
        return Objects.equals(this.drzava, other.drzava);
    }

    @Override
    public String vratiNazivTabele() {
        return "grad";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {

        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int gradID = rs.getInt("gradID");
            String imeGrada = rs.getString("grad.imeGrada");
            String opis = rs.getString("grad.opis");
            String drzava = rs.getString("grad.drzava");
            Grad grad = new Grad(gradID, imeGrada, drzava, opis);
            lista.add(grad);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imeGrada,drzava,opis";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + imeGrada + "','" + drzava + "','" + opis + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "grad.gradID=" + gradID;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "imeGrada='" + imeGrada + "', drzava='" + drzava + "', opis='" + opis + "'";
    }

}
