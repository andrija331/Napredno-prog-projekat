/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.rezervacije;

import java.util.List;
import rs.np.turagencija.domen.Rezervacija;
import rs.np.turagencija.domen.StavkaRezervacije;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja brise rezervaciju iz baze podataka zajedno sa svim
 * njenim stavkama.
 *
 * Prvo se brisu sve stavke rezervacije, a zatim i sama rezervacija.
 *
 * @author Andrija Panovic
 */
public class ObrisiRezervacijuSO extends ApstraktnaGenerickaOperacija {

    /**
     * Pokazatelj da li je rezervacija uspesno obrisana.
     */
    private boolean uspesnoObrisana = false;

    /**
     * Proverava da li je prosledjeni objekat razlicit od null i odgovarajuce
     * klase.
     *
     * @param param objekat koji treba da bude instanca klase
     * {@link Rezervacija}
     * @throws Exception ako je parametar {@code null} ili nije instanca klase
     * {@code Rezervacija}
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Rezervacija ili je null.");
        }
    }

    /**
     * Brise rezervaciju iz baze zajedno sa svim njenim stavkama.
     *
     * Prvo se poziva pomocna metoda obrisiStavke, koja na osnovu prosledjene
     * Rezervacije pronalazi sve stavke te rezervacije i brise ih. Nakon
     * brisanja stavki brise se Rezervacija
     *
     * @param param objekat tipa {@link Rezervacija} koji se brise
     * @param kljuc dodatni parametar (nije potreban u ovoj operaciji, moze biti
     * null)
     * @throws Exception ako brisanje ne uspe
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        Rezervacija rez = (Rezervacija) param;
        System.out.println("Rezervacija za brisanje: " + rez);
        obrisiStavke(rez);
        broker.delete(rez);
        uspesnoObrisana = true;
        System.out.println("OBRISANA REZ");
    }

    /**
     * Vraca informaciju o tome da li je rezervacija uspesno obrisana.
     *
     * @return {@code true} ako je rezervacija obrisana, {@code false} inace
     */
    public boolean obrisana() {
        return uspesnoObrisana;
    }

    /**
     * Pomocna metoda koja brise sve stavke prosledjene rezervacije iz baze.
     *
     * @param rez rezervacija cije stavke treba obrisati
     * @throws Exception ako brisanje neke stavke ne uspe
     */
    private void obrisiStavke(Rezervacija rez) throws Exception {
        System.out.println("Stigao dovde");
        List<StavkaRezervacije> stavke = rez.getStavke();
        for (StavkaRezervacije s : stavke) {
            broker.delete(s);
        }
        System.out.println("OBRISANE STAVKE");
    }

}
