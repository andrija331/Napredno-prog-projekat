/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja jednu stavku u okviru rezervacije, odnosno dodatnu uslugu koja je
 * deo odredjene rezervacije.
 *
 * <p>
 * Klasa implementira interfejs {@link ApstraktniDomenskiObjekat} i omogucava
 * mapiranje objekta na odgovarajucu tabelu u bazi podataka
 * ("stavkarezervacije"). Svaka stavka sadrzi informacije o rednom broju, ceni i
 * fakultativnoj usluzi koja se odnosi na odredjenu rezervaciju.</p>
 *
 * @author Andrija Panovic
 */
public class StavkaRezervacije implements ApstraktniDomenskiObjekat {

    /**
     * Rezervacija kojoj stavka pripada.
     */
    private Rezervacija rezervacija;

    /**
     * Redni broj stavke u okviru rezervacije.
     */
    private int rb;

    /**
     * Cena stavke, odnosno fakultativne usluge.
     */
    private double cena;

    /**
     * Fakultativna usluga koja je deo rezervacije.
     */
    private FakultativnaUsluga usluga;

    /**
     * Podrazumevani konstruktor koji inicijalizuje objekat klase
     * {@code StavkaRezervacije} sa atributima koji imaju default vrednosti.
     */
    public StavkaRezervacije() {
    }

    /**
     * Konstruktor koji kreira objekat klase {@code StavkaRezervacije} sa svim
     * atributima osim rezervacije.
     *
     * @param rb redni broj stavke u okviru rezervacije
     * @param cena cena stavke (fakultativne usluge)
     * @param usluga fakultativna usluga koja je deo rezervacije
     */
    public StavkaRezervacije(int rb, double cena, FakultativnaUsluga usluga) {
        this.rb = rb;
        this.cena = cena;
        this.usluga = usluga;
    }

    /**
     * Vraca rezervaciju kojoj stavka pripada.
     *
     * @return rezervacija kao objekat klase {@code Rezervacija}
     */
    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    /**
     * Postavlja rezervaciju kojoj stavka pripada.
     *
     * @param rezervacija rezervacija kao objekat klase {@code Rezervacija}
     */
    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    /**
     * Vraca redni broj stavke u okviru rezervacije.
     *
     * @return redni broj stavke kao Integer vrednost
     */
    public int getRb() {
        return rb;
    }

    /**
     * Postavlja redni broj stavke u okviru rezervacije.
     *
     * @param rb redni broj stavke kao Integer vrednost
     */
    public void setRb(int rb) {
        this.rb = rb;
    }

    /**
     * Vraca cenu stavke (fakultativne usluge).
     *
     * @return cena stavke kao Double vrednost
     */
    public double getCena() {
        return cena;
    }

    /**
     * Postavlja cenu stavke (fakultativne usluge).
     *
     * @param cena cena stavke kao Double vrednost
     */
    public void setCena(double cena) {
        this.cena = cena;
    }

    /**
     * Vraca fakultativnu uslugu koja je deo rezervacije.
     *
     * @return usluga kao objekat klase {@code FakultativnaUsluga}
     */
    public FakultativnaUsluga getUsluga() {
        return usluga;
    }

    /**
     * Postavlja fakultativnu uslugu koja je deo rezervacije.
     *
     * @param usluga fakultativna usluga kao objekat klase
     * {@code FakultativnaUsluga}
     */
    public void setUsluga(FakultativnaUsluga usluga) {
        this.usluga = usluga;
    }

    /**
     * Vraca string reprezentaciju objekta klase {@code StavkaRezervacije}.
     *
     * @return String u formatu: "StavkaRezervacije{rb=..., cena=...,
     * usluga=...}"
     */
    @Override
    public String toString() {
        return "StavkaRezervacije{" + "rb=" + rb + ", cena=" + cena + ", usluga=" + usluga + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaRezervacije";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {

            StavkaRezervacije stavka = new StavkaRezervacije();
            stavka.setRb(rs.getInt("rb"));
            stavka.setCena(rs.getDouble("stavkaRezervacije.cena"));

            FakultativnaUsluga usluga = new FakultativnaUsluga();
            usluga.setNaziv(rs.getString("fakultativnausluga.naziv"));
            usluga.setOpis(rs.getString("fakultativnausluga.opis"));
            usluga.setCena(rs.getDouble("fakultativnausluga.cena"));
            usluga.setUslugaID(rs.getInt("fakultativnausluga.uslugaID"));

            stavka.setUsluga(usluga);
            lista.add(stavka);

        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb,rezervacija,cena,usluga";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return rb + "," + rezervacija.getRezervacijaID() + "," + cena + "," + usluga.getUslugaID();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "rb=" + rb + " AND stavkarezervacije.rezervacija=" + rezervacija.getRezervacijaID();
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "rb=" + rb + ", rezervacija=" + rezervacija.getRezervacijaID() + ", cena=" + cena + ", usluga=" + usluga.getUslugaID();
    }
}
