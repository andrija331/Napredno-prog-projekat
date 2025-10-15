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
 * Predstavlja dodatnu (fakultativnu) uslugu koja se moze doplatiti uz osnovni
 * aranzman.
 *
 * <p>
 * Klasa implementira interfejs {@link ApstraktniDomenskiObjekat} i omogucava
 * mapiranje objekta na odgovarajucu tabelu u bazi podataka
 * ("fakultativnausluga"). Fakultativna usluga predstavlja dodatnu ponudu koja
 * moze da se doda uz osnovni aranzman (npr. izleti, dodatni obroci, turisticki
 * vodic i slicno).</p>
 *
 * @author Andrija Panovic
 */
public class FakultativnaUsluga implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator fakultativne usluge u bazi podataka.
     */
    private int uslugaID;

    /**
     * Naziv fakultativne usluge kao String.
     */
    private String naziv;

    /**
     * Opis fakultativne usluge.
     */
    private String opis;

    /**
     * Cena fakultativne usluge kao Double vrednost.
     */
    private double cena;

    /**
     * Podrazumevani konstruktor koji inicijalizuje objekat klase
     * {@code FakultativnaUsluga} sa atributima koji imaju default vrednosti.
     */
    public FakultativnaUsluga() {
    }

    /**
     * Konstruktor koji kreira objekat klase {@code FakultativnaUsluga} sa svim
     * atributima.
     *
     * @param uslugaID jedinstveni identifikator fakultativne usluge
     * @param naziv naziv fakultativne usluge
     * @param opis opis fakultativne usluge
     * @param cena cena fakultativne usluge
     */
    public FakultativnaUsluga(int uslugaID, String naziv, String opis, double cena) {
        this.uslugaID = uslugaID;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    /**
     * Vraca opis fakultativne usluge.
     *
     * @return opis fakultativne usluge kao String
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis fakultativne usluge.
     *
     * @param opis opis fakultativne usluge kao String
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * Vraca jedinstveni identifikator fakultativne usluge.
     *
     * @return jedinstveni identifikator fakultativne usluge kao Integer
     * vrednost
     */
    public int getUslugaID() {
        return uslugaID;
    }

    /**
     * Postavlja jedinstveni identifikator fakultativne usluge.
     *
     * @param uslugaID uslugaID fakultativne usluge kao Integer vrednost
     */
    public void setUslugaID(int uslugaID) {
        this.uslugaID = uslugaID;
    }

    /**
     * Vraca naziv fakultativne usluge.
     *
     * @return naziv fakultativne usluge kao String
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv fakultativne usluge.
     *
     * @param naziv naziv fakultativne usluge kao String
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Vraca cenu fakultativne usluge.
     *
     * @return cena fakultativne usluge kao Double vrednost
     */
    public double getCena() {
        return cena;
    }

    /**
     * Postavlja cenu fakultativne usluge.
     *
     * @param cena cena fakultativne usluge kao Double vrednost
     */
    public void setCena(double cena) {
        this.cena = cena;
    }

    /**
     * Vraca naziv fakultativne usluge kao string reprezentaciju objekta.
     *
     * @return naziv fakultativne usluge kao String
     */
    @Override
    public String toString() {
        return naziv;
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
     * Poredi dva objekta klase {@code FakultativnaUsluga} na osnovu naziva i
     * cene.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako je uneti objekat razlicit od null, ako je klase
     * FakultativnaUsluga i ako su naziv i cena isti, u suprotnom {@code false}
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
        final FakultativnaUsluga other = (FakultativnaUsluga) obj;
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String vratiNazivTabele() {
        return "fakultativnausluga";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int uslugaid = rs.getInt("uslugaID");

            String naziv = rs.getString("fakultativnausluga.naziv");
            String opis = rs.getString("opis");
            Double cena = rs.getDouble("fakultativnausluga.cena");
            FakultativnaUsluga usluga = new FakultativnaUsluga(uslugaid, naziv, opis, cena);

            lista.add(usluga);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,opis,cena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "','" + opis + "'," + cena;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "fakultativnausluga.uslugaID=" + uslugaID;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='" + naziv + "', opis='" + opis + "', cena=" + cena;
    }

}
