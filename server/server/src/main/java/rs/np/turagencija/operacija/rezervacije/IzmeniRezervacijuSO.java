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
public class IzmeniRezervacijuSO extends ApstraktnaGenerickaOperacija {

    boolean uspesnoIzmenjeno = false;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Rezervacija ili je null.");
        }
    }

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

    public boolean izmenjeno() {
        return uspesnoIzmenjeno;
    }

}
