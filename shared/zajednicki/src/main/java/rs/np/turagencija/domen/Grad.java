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
public class Grad implements ApstraktniDomenskiObjekat {

    private int gradID;
    private String imeGrada;
    private String drzava;
    private String opis;

    public Grad() {
    }

    public Grad(int gradID, String imeGrada, String drzava, String opis) {
        this.gradID = gradID;
        this.imeGrada = imeGrada;
        this.drzava = drzava;
        this.opis = opis;
    }

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int gradID) {
        this.gradID = gradID;
    }

    public String getNazivGrada() {
        return imeGrada;
    }

    public void setNazivGrada(String nazivGrada) {
        this.imeGrada = nazivGrada;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return imeGrada;
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
