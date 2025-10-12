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
 *
 * @author KORISNIK
 */
public class ObrisiKlijentaSO extends ApstraktnaGenerickaOperacija {

    private boolean obrisanKlijent = false;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent ili je null.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Klijent klijent = (Klijent) param;
        obrisiKlijentoveRezervacije(klijent);

        broker.delete(klijent);
        obrisanKlijent = true;

    }

    public boolean obrisan() {
        return obrisanKlijent;
    }

    private void obrisiKlijentoveRezervacije(Klijent klijent) throws Exception {
        List<Rezervacija> sveRezervacije = Controller.getInst().ucitajRezervacije();

        List<Rezervacija> rezervacijeZaBrisanje = new ArrayList<>();
        for (Rezervacija r : sveRezervacije) {
            if (r.getKlijent().equals(klijent)) {
                rezervacijeZaBrisanje.add(r);
            }

        }
        for (Rezervacija r : rezervacijeZaBrisanje) {
            Controller.getInst().obrisiRezervaciju(r);
        }
    }

}
