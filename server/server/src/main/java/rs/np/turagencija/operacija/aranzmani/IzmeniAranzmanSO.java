/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.aranzmani;

import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja azurira podatke o postojecem aranzmanu u bazi
 * podataka.
 * <p>
 * Pre izvrsenja proverava se da li je prosledjeni objekat validan i da li je
 * instanca klase {@link Aranzman}. Ukoliko nije, baca se izuzetak.
 * <p>
 * Nakon uspesnog izvrsenja, atribut {@code uspesnoIzmenjen} postavlja se na
 * {@code true}.
 *
 * @author Andrija Panovic
 */
public class IzmeniAranzmanSO extends ApstraktnaGenerickaOperacija {

    /**
     * Pokazatelj da li je aranzman uspesno izmenjen.
     */
    private boolean uspesnoIzmenjen = false;

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
     * Izmenjuje postojeci zapis o aranzmanu u bazi.
     *
     * @param param objekat tipa {@link Aranzman} koji sadrzi azurirane podatke
     * @param kljuc dodatni parametar (nije potreban za ovu operaciju, moze biti
     * null)
     * @throws Exception ako azuriranje aranzmana ne uspe
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Aranzman) param);
        uspesnoIzmenjen = true;

    }

    /**
     * Vraca informaciju o tome da li je aranzman uspesno izmenjen.
     *
     * @return {@code true} ako je izmena uspesno izvrsena, {@code false} inace
     */
    public boolean izmenjen() {
        return uspesnoIzmenjen;
    }

}
