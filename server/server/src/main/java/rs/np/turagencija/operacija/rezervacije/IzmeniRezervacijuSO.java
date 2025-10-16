/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.rezervacije;

import java.util.List;
import rs.np.turagencija.controller.Controller;
import rs.np.turagencija.domen.Rezervacija;
import rs.np.turagencija.domen.StavkaRezervacije;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja azurira postojecu rezervaciju u bazi podataka
 * zajedno sa njenim stavkama.
 * <p>
 * Prilikom izmene, sve stare stavke rezervacije se brisu, pa se nakon toga
 * ubacuju sve nove stavke.
 * <p>
 * Ako je operacija uspesno izvrsena, atribut {@code uspesnoIzmenjeno} postavlja
 * se na {@code true}.
 *
 * @author Andrija Panovic
 */
public class IzmeniRezervacijuSO extends ApstraktnaGenerickaOperacija {

    /**
     * Pokazatelj da li je rezervacija uspesno izmenjena.
     */
    boolean uspesnoIzmenjeno = false;

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
     * Azurira rezervaciju i sve njene stavke u bazi podataka.
     * <p>
     * Prvo se brisu stare stavke, a zatim se dodaju nove.
     *
     * @param param objekat tipa {@link Rezervacija} koji sadrzi nove podatke
     * @param kljuc dodatni parametar (nije potreban u ovoj operaciji, moze biti
     * null)
     * @throws Exception ako izmene rezervacije ili stavki ne uspeju
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Rezervacija rez = (Rezervacija) param;
        broker.edit(rez);

        List<StavkaRezervacije> stareStavke = Controller.getInst().ucitajStavke(rez.getRezervacijaID());
        for (StavkaRezervacije s : stareStavke) {
            s.setRezervacija(rez);
            broker.delete(s);
        }

        for (StavkaRezervacije st : rez.getStavke()) {
            broker.add(st);
        }
        uspesnoIzmenjeno = true;

    }

    /**
     * Vraca informaciju da li je rezervacija uspesno izmenjena.
     *
     * @return {@code true} ako je uspesno izmenjena, u suprotnom {@code false}
     */
    public boolean izmenjeno() {
        return uspesnoIzmenjeno;
    }

}
