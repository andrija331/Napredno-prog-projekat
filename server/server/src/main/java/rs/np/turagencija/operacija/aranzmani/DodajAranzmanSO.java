/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.aranzmani;

import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja dodaje novi aranzman u bazu podataka.
 * <p>
 * Pre izvrsenja proverava se da li je prosledjeni objekat validan i da li je
 * instanca klase {@link Aranzman}. Ukoliko nije, baca se izuzetak.
 * </p>
 *
 * @author Andrija Panovic
 */
public class DodajAranzmanSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava da li je prosledjeni parametar razlicit od null i odgovarajuce
     * klase.
     *
     * @param param objekat koji treba da bude instanca klase {@link Aranzman}
     * @throws Exception ako je parametar {@code null} ili nije instanca klase
     * {@code Aranzman}
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Aranzman)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Aranzman ili je null.");
        }

    }

    /**
     * Dodaje aranzman u bazu podataka.
     *
     * @param param objekat tipa {@link Aranzman} koji se dodaje
     * @param kljuc dodatni parametar (nije potreban za ovu operaciju, moze biti
     * null)
     * @throws Exception ako dodavanje aranzmana ne uspe
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        broker.add((Aranzman) param);
    }

}
