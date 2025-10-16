/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.klijenti;

import java.util.ArrayList;
import java.util.List;
import rs.np.turagencija.controller.Controller;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.domen.Rezervacija;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja brise klijenta iz baze podataka zajedno sa svim
 * njegovim rezervacijama.
 * <p>
 *
 * Tokom brisanja, prvo se pronalaze i uklanjaju sve rezervacije vezane za datog
 * klijenta, a zatim se brise sam klijent iz baze.
 *
 * @author Andrija Panovic
 */
public class ObrisiKlijentaSO extends ApstraktnaGenerickaOperacija {

    /**
     * Pokazatelj da li je klijent uspesno obrisan.
     */
    private boolean obrisanKlijent = false;

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
     * Brise klijenta iz baze podataka zajedno sa svim njegovim rezervacijama.
     *
     * @param param objekat tipa {@link Klijent} koji se brise iz baze
     * @param kljuc dodatni parametar (nije potreban za ovu operaciju, moze biti
     * null)
     * @throws Exception ako brisanje klijenta ili njegovih rezervacija ne uspe
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Klijent klijent = (Klijent) param;
        obrisiKlijentoveRezervacije(klijent);

        broker.delete(klijent);
        obrisanKlijent = true;

    }

    /**
     * Vraca informaciju o tome da li je klijent uspesno obrisan.
     *
     * @return {@code true} ako je klijent obrisan, u suprotnom {@code false}
     */
    public boolean obrisan() {
        return obrisanKlijent;
    }

    /**
     * Pomocna metoda koja pronalazi i brise sve rezervacije povezane sa datim
     * klijentom.
     *
     * @param klijent klijent cije rezervacije treba obrisati
     * @throws Exception ako brisanje neke rezervacije ne uspe
     */
    private void obrisiKlijentoveRezervacije(Klijent klijent) throws Exception {
        List<Rezervacija> sveRezervacije = Controller.getInst().ucitajRezervacije();
        System.out.println(" Sve Rezervacije" + sveRezervacije);

        List<Rezervacija> rezervacijeZaBrisanje = new ArrayList<>();
        for (Rezervacija r : sveRezervacije) {
            System.out.println("Klijent iz liste svih rezervacija: " + r.getKlijent());
            System.out.println("Klijent za brisanje: " + klijent);
            if (r.getKlijent().equals(klijent)) {
                rezervacijeZaBrisanje.add(r);
            }

        }
        System.out.println("Rezervacije za brisanje" + rezervacijeZaBrisanje);

        for (Rezervacija r : rezervacijeZaBrisanje) {
            Controller.getInst().obrisiRezervaciju(r);
        }
    }

}
