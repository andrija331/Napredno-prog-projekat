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
 *
 * @author KORISNIK
 */
public class DodajRezervacijuSO extends ApstraktnaGenerickaOperacija {

    Rezervacija rezervacija = null;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Rezervacija ili je null.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        Rezervacija rez = (Rezervacija) param;

        int id = broker.add(rez);
        System.out.println("Dodata rezervacija. Njen id je " + id);

        rez.setRezervacijaID(id);
        dodajStavke(rez);
        rezervacija = rez;

    }

    private void dodajStavke(Rezervacija r) throws Exception {
        System.out.println("STIGLI SMO DO DODAVANJA STAVKI");
        List<StavkaRezervacije> stavke = r.getStavke();
        for (StavkaRezervacije s : stavke) {
            s.setRezervacija(r);
            broker.add(s);
        }

    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

}
