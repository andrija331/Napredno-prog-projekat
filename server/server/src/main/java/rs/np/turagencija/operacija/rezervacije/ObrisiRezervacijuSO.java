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
public class ObrisiRezervacijuSO extends ApstraktnaGenerickaOperacija {

    private boolean uspesnoObrisana = false;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Rezervacija ili je null.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        Rezervacija rez = (Rezervacija) param;
        System.out.println("Rezervacija za brisanje: " + rez);
        obrisiStavke(rez);
        broker.delete(rez);
        uspesnoObrisana = true;
        System.out.println("OBRISANA REZ");
    }

    public boolean obrisana() {
        return uspesnoObrisana;
    }

    private void obrisiStavke(Rezervacija rez) throws Exception {
        System.out.println("Stigao dovde");
        List<StavkaRezervacije> stavke = rez.getStavke();
        for (StavkaRezervacije s : stavke) {
            broker.delete(s);
        }
        System.out.println("OBRISANE STAVKE");
    }

}
