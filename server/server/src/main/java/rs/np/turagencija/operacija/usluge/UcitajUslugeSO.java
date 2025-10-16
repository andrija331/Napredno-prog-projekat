/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.usluge;

import java.util.List;
import rs.np.turagencija.domen.FakultativnaUsluga;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja ucitava sve fakultativne usluge iz baze podataka.
 *
 * Operacija ne zahteva dodatne parametre ni preduslove. Nakon uspesnog
 * izvrsenja, lista usluga moze se preuzeti pomocu metode {@link #getUsluge()}.
 *
 * @author Andrija Panovic
 */
public class UcitajUslugeSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista svih fakultativnih usluga ucitanih iz baze podataka.
     */
    List<FakultativnaUsluga> usluge;

    /**
     * Ova operacija nema definisane preduslove.
     *
     * @param param ne koristi se u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    /**
     * Iz baze ucitava sve fakultativne usluge i cuva ih u internu listu
     * {@code usluge}.
     *
     * @param param instanca klase {@link FakultativnaUsluga} (koristi se kao
     * tip)
     * @param kljuc dodatni parametar (nije potreban u ovoj operaciji, moze biti
     * null)
     * @throws Exception ako dodje do greske prilikom citanja iz baze
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        usluge = broker.getAll(param, null);

    }

    /**
     * Vraca listu svih ucitanih fakultativnih usluga.
     *
     * @return lista usluga
     */
    public List<FakultativnaUsluga> getUsluge() {
        return usluge;
    }

    /**
     * Postavlja listu ucitanih usluga.
     *
     * @param usluge lista objekata tipa {@link FakultativnaUsluga}
     */
    public void setUsluge(List<FakultativnaUsluga> usluge) {
        this.usluge = usluge;
    }

}
