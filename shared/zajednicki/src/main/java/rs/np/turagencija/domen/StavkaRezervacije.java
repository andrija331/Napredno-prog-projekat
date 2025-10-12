/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KORISNIK
 */
public class StavkaRezervacije implements ApstraktniDomenskiObjekat {

    private Rezervacija rezervacija;
    private int rb;
    private double cena;
    private FakultativnaUsluga usluga;

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(int rb, double cena, FakultativnaUsluga usluga) {
        this.rb = rb;
        this.cena = cena;
        this.usluga = usluga;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public FakultativnaUsluga getUsluga() {
        return usluga;
    }

    public void setUsluga(FakultativnaUsluga usluga) {
        this.usluga = usluga;
    }

    @Override
    public String toString() {
        return "StavkaRezervacije{rb=" + rb + '}';
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
