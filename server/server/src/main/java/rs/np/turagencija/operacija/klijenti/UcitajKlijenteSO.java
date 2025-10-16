/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.klijenti;

import java.util.List;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja ucitava sve klijente iz baze podataka.
 *
 * Operacija ne zahteva dodatne parametre ni preduslove. Nakon uspesnog
 * izvrsenja, lista klijenata moze se preuzeti pomocu metode
 * {@link #getKlijenti()}.
 *
 * @author KORISNIK
 */
public class UcitajKlijenteSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista svih klijenata ucitanih iz baze podataka.
     */
    List<Klijent> klijenti;

    /**
     * Ova operacija nema definisane preduslove.
     *
     * @param param ne koristi se u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Iz baze ucitava sve klijente i cuva ih u internu listu {@code klijenti}.
     *
     * @param param instanca klase {@link Klijent} (koristi se kao tip)
     * @param kljuc dodatni parametar (nije potreban za ovu operaciju, moze biti
     * null)
     * @throws Exception ako dodje do greske prilikom citanja iz baze
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        klijenti = broker.getAll(param, kljuc);
    }

    /**
     * Vraca listu svih klijenata ucitanih iz baze.
     *
     * @return Lista klijenata
     */
    public List<Klijent> getKlijenti() {
        return klijenti;
    }

}
