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
 * Sistemska operacija koja dodaje novu rezervaciju u bazu podataka zajedno sa
 * svim njenim stavkama.
 *
 * Pre izvrsenja proverava se da li je prosledjeni objekat validan i da li je
 * instanca klase {@link Rezervacija}. Ako nije, baca se izuzetak. Nakon
 * uspesnog dodavanja rezervacije, sve njene stavke se dodaju u bazu.
 *
 * @author Andrija Panovic
 */
public class DodajRezervacijuSO extends ApstraktnaGenerickaOperacija {

    /**
     * Rezervacija u koju se postavlja rezervacija sa privatnim kljucem iz baze
     * .
     */
    Rezervacija rezervacija = null;

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
     * Dodaje rezervaciju u bazu podataka nakon cega dodaje sve njene stavke.
     *
     * @param param objekat tipa {@link Rezervacija} koji se dodaje
     * @param kljuc dodatni parametar (nije potreban u ovoj operaciji, moze biti
     * null)
     * @throws Exception ako dodavanje rezervacije ili stavki ne uspe
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        Rezervacija rez = (Rezervacija) param;

        int id = broker.add(rez);
        System.out.println("Dodata rezervacija. Njen id je " + id);

        rez.setRezervacijaID(id);
        dodajStavke(rez);
        rezervacija = rez;

    }

    /**
     * Pomocna metoda koja dodaje sve stavke rezervacije u bazu.
     *
     * @param r rezervacija sa stavkama koje treba dodati
     * @throws Exception ako dodavanje neke stavke ne uspe
     */
    private void dodajStavke(Rezervacija r) throws Exception {
        System.out.println("STIGLI SMO DO DODAVANJA STAVKI");
        List<StavkaRezervacije> stavke = r.getStavke();
        for (StavkaRezervacije s : stavke) {
            s.setRezervacija(r);
            broker.add(s);
        }

    }

    /**
     * Vraca rezervaciju koja je uspesno dodata u bazu.
     *
     * @return objekat tipa {@link Rezervacija}
     */
    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    /**
     * Postavlja rezervaciju .
     *
     * @param rezervacija objekat tipa {@link Rezervacija}
     */
    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

}
