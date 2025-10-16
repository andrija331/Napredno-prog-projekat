/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.rezervacije.stavke;

import java.util.List;
import rs.np.turagencija.domen.StavkaRezervacije;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja ucitava sve stavke jedne rezervacije iz baze
 * podataka.
 * <p>
 * Operacija koristi ID rezervacije i pomocu JOIN upita ucitava i pripadajuce
 * fakultativne usluge.
 *
 * @author Andrija Panovic
 */
public class UcitajStavkeSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista svih stavki rezervacije ucitanih iz baze.
     */
    List<StavkaRezervacije> stavke;

    /**
     * Ova operacija nema definisane preduslove.
     *
     * @param param ne koristi se u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    /**
     * Iz baze ucitava sve stavke rezervacije na osnovu njenog ID-ja.
     *
     * @param param ID rezervacije (tipa {@code int})
     * @param kljuc dodatni parametar (nije potreban u ovoj operaciji, moze biti
     * null)
     * @throws Exception ako dodje do greske prilikom citanja iz baze
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN fakultativnausluga on stavkaRezervacije.usluga=fakultativnausluga.uslugaID WHERE stavkaRezervacije.rezervacija=" + (int) param;
        stavke = broker.getAll(new StavkaRezervacije(), uslov);

    }

    /**
     * Vraca listu svih stavki rezervacije ucitanih iz baze.
     *
     * @return lista stavki rezervacije
     */
    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

}
