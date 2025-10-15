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
public class FakultativnaUsluga implements ApstraktniDomenskiObjekat {

    private int uslugaID;
    private String naziv;
    private String opis;
    private double cena;

    public FakultativnaUsluga() {
    }

    public FakultativnaUsluga(int uslugaID, String naziv, String opis, double cena) {
        this.uslugaID = uslugaID;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getUslugaID() {
        return uslugaID;
    }

    public void setUslugaID(int uslugaID) {
        this.uslugaID = uslugaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return naziv;
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
