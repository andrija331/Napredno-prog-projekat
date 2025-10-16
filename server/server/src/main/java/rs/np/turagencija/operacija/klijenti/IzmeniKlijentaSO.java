/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.klijenti;

import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja azurira podatke o postojecem klijentu u bazi
 * podataka.
 * <p>
 * Pre izvrsenja proverava se da li je prosledjeni objekat validan i da li je
 * instanca klase {@link Klijent}. Ukoliko nije, baca se izuzetak.
 * <p>
 * Nakon uspesnog izvrsenja, atribut {@code uspesnoIzmenjen} postavlja se na
 * {@code true}.
 *
 * @author Andrija Panovic
 */
public class IzmeniKlijentaSO extends ApstraktnaGenerickaOperacija {

    /**
     * Pokazatelj da li je klijent uspesno izmenjen.
     */
    private boolean uspesnoIzmenjen = false;

    /**
     * Proverava da li je prosledjeni parametar razlicit od null i odgovarajuce
     * klase.
     *
     * @param param objekat koji treba da bude instanca klase {@link Klijent}
     * @throws Exception ako je parametar {@code null} ili nije instanca klase
     * {@code Klijent}
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent ili je null.");
        }
    }

    /**
     * Izmenjuje postojeci zapis o klijentu u bazi.
     *
     * @param param objekat tipa {@link Klijent} koji sadrzi azurirane podatke
     * @param kljuc dodatni parametar (nije potreban za ovu operaciju, moze biti
     * null)
     * @throws Exception ako azuriranje klijenta ne uspe
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Klijent) param);
        uspesnoIzmenjen = true;
    }

    /**
     * Vraca informaciju o tome da li je klijent uspesno izmenjen.
     *
     * @return {@code true} ako je izmena uspesno izvrsena, u suprotnom
     * {@code false}
     */
    public boolean izmenjen() {
        return uspesnoIzmenjen;
    }

}
