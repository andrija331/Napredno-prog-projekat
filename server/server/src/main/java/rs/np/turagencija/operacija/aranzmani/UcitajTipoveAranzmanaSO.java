/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.aranzmani;

import java.util.List;

import rs.np.turagencija.domen.TipAranzmana;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja ucitava sve tipove aranzmana iz baze podataka.
 * <p>
 * Operacija ne zahteva dodatne parametre ni preduslove. Nakon uspesnog
 * izvrsenja, lista tipova aranzmana moze se preuzeti pomocu metode
 * {@link #getTipovi()}.</p>
 *
 * @author Andrija Panovic
 */
public class UcitajTipoveAranzmanaSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista svih tipova aranzmana ucitanih iz baze.
     */
    List<TipAranzmana> tipovi;

    /**
     * Ova operacija nema definisane preduslove.
     *
     * @param param ne koristi se u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    /**
     * Iz baze ucitava sve tipove aranzmana i cuva ih u internu listu
     * {@code tipovi}.
     *
     * @param param instanca klase {@link TipAranzmana} (koristi se kao tip)
     * @param kljuc dodatni parametar (nije potreban za ovu operaciju, moze biti
     * null)
     * @throws Exception ako dodje do greske prilikom citanja iz baze
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        tipovi = broker.getAll(param, kljuc);
    }

    /**
     * Vraca listu svih tipova aranzmana ucitanih iz baze.
     *
     * @return lista tipova aranzmana
     */
    public List<TipAranzmana> getTipovi() {
        return tipovi;
    }

}
