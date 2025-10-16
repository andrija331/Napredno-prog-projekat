/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.gradovi;

import java.util.List;
import rs.np.turagencija.domen.Grad;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja ucitava sve gradove iz baze podataka.
 * <p>
 * Operacija ne zahteva dodatne parametre ni preduslove. Nakon uspesnog
 * izvrsenja, lista gradova moze se preuzeti pomocu metode
 * {@link #getGradovi()}.
 *
 * @author Andrija Panovic
 */
public class UcitajGradoveSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista svih gradova ucitanih iz baze.
     */
    List<Grad> gradovi;

    /**
     * Ova operacija nema definisane preduslove.
     *
     * @param param ne koristi se u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    /**
     * Iz baze ucitava sve gradove i cuva ih u internu listu {@code gradovi}.
     *
     * @param param instanca klase {@link Grad} (koristi se kao tip)
     * @param kljuc dodatni parametar (moze biti null),
     * @throws Exception ako dodje do greske prilikom citanja iz baze
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        gradovi = broker.getAll(param, kljuc);
    }

    /**
     * Vraca listu svih gradova ucitanih iz baze.
     *
     * @return lista gradova
     */
    public List<Grad> getGradovi() {
        return gradovi;
    }

}
