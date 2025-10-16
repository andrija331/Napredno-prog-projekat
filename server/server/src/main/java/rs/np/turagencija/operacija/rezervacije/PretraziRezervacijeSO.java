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
 * Sistemska operacija koja pretrazuje rezervacije na osnovu prosledjenog kljuca
 * (npr. email adrese klijenta).
 * <p>
 * Operacija ucitava sve rezervacije koje pripadaju klijentu sa zadatom email
 * adresom, zajedno sa njihovim stavkama.
 *
 * @author Andrija Panovic
 */
public class PretraziRezervacijeSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista rezervacija pronadjenih u bazi.
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
     * Pretrazuje rezervacije po zadatom kljucu i ucitava pripadajuce stavke.
     *
     * @param param instanca klase {@link Rezervacija} (koristi se kao tip)
     * @param kljuc Sting koji sadrzi parametar po kom se rezervacije pretrazuju
     * (npr. email adresa klijenta)
     * @throws Exception ako dodje do greske prilikom pretrage
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        lista = broker.getAll(param, " JOIN zaposleni ON rezervacija.zaposleni=zaposleni.zaposleniID"
                + " JOIN klijent ON rezervacija.klijent=klijent.klijentID"
                + " JOIN aranzman ON rezervacija.aranzman=aranzman.aranzmanID"
                + " JOIN grad ON aranzman.grad=grad.gradID"
                + " JOIN tiparanzmana ON aranzman.tipAranzmana=tipAranzmana.tipID"
                + " WHERE klijent.email=" + "'" + kljuc + "'");

        for (Rezervacija r : lista) {
            List<StavkaRezervacije> stavke = Controller.getInst().ucitajStavke(r.getRezervacijaID());
            for (StavkaRezervacije s : stavke) {
                s.setRezervacija(r);
            }
            r.setStavke(stavke);
        }
    }

    /**
     * Vraca listu rezervacija pronadjenih u bazi.
     *
     * @return lista rezervacija
     */
    public List<Rezervacija> getLista() {
        return lista;
    }

    /**
     * Postavlja listu rezervacija.
     *
     * @param lista lista objekata tipa {@link Rezervacija}
     */
    public void setLista(List<Rezervacija> lista) {
        this.lista = lista;
    }

}
