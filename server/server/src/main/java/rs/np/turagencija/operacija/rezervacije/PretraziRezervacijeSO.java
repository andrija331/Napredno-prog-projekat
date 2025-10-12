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
 *
 * @author KORISNIK
 */
public class PretraziRezervacijeSO extends ApstraktnaGenerickaOperacija {

    private List<Rezervacija> lista;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        lista = broker.getAll(param, " JOIN zaposleni ON rezervacija.zaposleni=zaposleni.zaposleniID"
                + " JOIN klijent ON rezervacija.klijent=klijent.klijentID"
                + " JOIN aranzman ON rezervacija.aranzman=aranzman.aranzmanID"
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

    public List<Rezervacija> getLista() {
        return lista;
    }

    public void setLista(List<Rezervacija> lista) {
        this.lista = lista;
    }

}
