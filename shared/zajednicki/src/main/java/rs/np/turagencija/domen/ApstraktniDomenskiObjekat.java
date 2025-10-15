/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.np.turagencija.domen;

import java.io.Serializable;
import java.util.List;
import java.sql.*;

/**
 * Interfejs koji definise osnovne metode koje svaka klasa domena mora da
 * implementira kako bi omogucila genericke CRUD operacije nad bazom podataka.
 *
 * <p>
 * Interfejs se koristi kao apstrakcija između sloja baze podataka i domen
 * modela. Omogućava različitim klasama da budu tretirane generički prilikom
 * rada sa bazom, bez potrebe da se poznaje njihova konkretna
 * implementacija.</p>
 *
 * @author Andrija Panovic
 */
public interface ApstraktniDomenskiObjekat extends Serializable {

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara konkretnom domenskom
     * objektu.
     *
     * @return naziv tabele kao {@code String}
     */
    public String vratiNazivTabele();

    /**
     * Na osnovu prosleđenog {@link ResultSet} objekta formira i vraća listu
     * domenskih objekata.
     *
     * @param rs ResultSet koji sadrzi rezultate SQL upita
     * @return lista domenskih objekata koji implementiraju ovaj interfejs
     * @throws Exception ako dođe do greske prilikom citanja podataka iz
     * ResultSet-a
     */
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;

    /**
     * Vraca nazive kolona koje se koriste prilikom ubacivanja novog reda u
     * bazu.
     *
     * @return niz naziva kolona odvojenih zarezima
     */
    public String vratiKoloneZaUbacivanje();

    /**
     * Vraca vrednosti koje ce biti ubacene u bazu prilikom INSERT operacije.
     *
     * @return niz vrednosti kao {@code String}, formatiran za SQL upit
     */
    public String vratiVrednostiZaUbacivanje();

    /**
     * Vraca primarni kljuc koji identifikuje konkretan zapis u bazi.
     *
     * @return primarni kljuc kao {@code String} u obliku "tabela.id=vrednost"
     */
    public String vratiPrimarniKljuc();

    /**
     * Vraca deo SQL upita koji se koristi prilikom izmene (UPDATE) postojećeg
     * reda.
     *
     * @return formatiran niz "kolona=vrednost" parova spremnih za SQL upit
     */
    public String vratiVrednostZaIzmenu();

}
