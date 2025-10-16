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
 * Sistemska operacija koja ucitava sve rezervacije iz baze podataka zajedno sa
 * njihovim stavkama.
 * <p>
 * Operacija ne zahteva dodatne parametre ni preduslove. Svaka rezervacija
 * sadrzi reference na klijenta, zaposlenog, aranzman i tip aranzmana.
 *
 * @author Andrija Panovic
 */
public class UcitajRezervacijeSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista svih rezervacija ucitanih iz baze podataka.
     */
    private List<Rezervacija> lista;

    /**
     * Ova operacija nema definisane preduslove.
     *
     * @param param ne koristi se u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    /**
     * Iz baze ucitava sve rezervacije zajedno sa njihovim stavkama.
     *
     * @param param instanca klase {@link Rezervacija} (koristi se kao tip)
     * @param kljuc String u kom je upisan SQL JOIN upit za tabele grada,
     * tipAranzmana, aranzman, klijent i zaposleni.
     * @throws Exception ako dodje do greske prilikom citanja
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        lista = broker.getAll(param, kljuc);

        for (Rezervacija r : lista) {
            List<StavkaRezervacije> stavke = Controller.getInst().ucitajStavke(r.getRezervacijaID());
            for (StavkaRezervacije s : stavke) {
                s.setRezervacija(r);
            }
            r.setStavke(stavke);
        }
    }

    /**
     * Vraca listu svih rezervacija ucitanih iz baze.
     *
     * @return lista rezervacija
     */
    public List<Rezervacija> getLista() {
        return lista;
    }

    /**
     * Postavlja listu rezervacija .
     *
     * @param lista lista objekata tipa {@link Rezervacija}
     */
    public void setLista(List<Rezervacija> lista) {
        this.lista = lista;
    }

}
