/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.usluge;

import rs.np.turagencija.domen.FakultativnaUsluga;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja dodaje novu fakultativnu uslugu u bazu podataka.
 * <p>
 * Pre izvrsenja proverava se da li je prosledjeni objekat validan i da li je
 * instanca klase {@link FakultativnaUsluga}. Ukoliko nije, baca se izuzetak.
 * <p>
 * Nakon uspesnog izvrsenja, nova usluga se cuva u bazi podataka.
 *
 * @author Andrija Panovic
 */
public class DodajUsluguSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava da li je prosledjeni parametar razlicit od null i odgovarajuce
     * klase.
     *
     * @param param objekat koji treba da bude instanca klase
     * {@link FakultativnaUsluga}
     * @throws Exception ako je parametar {@code null} ili nije instanca klase
     * {@code FakultativnaUsluga}
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof FakultativnaUsluga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase FakultativnaUsluga ili je null.");
        }
    }

    /**
     * Dodaje novu fakultativnu uslugu u bazu podataka.
     *
     * @param param objekat tipa {@link FakultativnaUsluga} koji se dodaje
     * @param kljuc dodatni parametar (nije potreban za ovu operaciju, moze biti
     * null)
     * @throws Exception ako dodavanje usluge ne uspe
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((FakultativnaUsluga) param);

    }

}
