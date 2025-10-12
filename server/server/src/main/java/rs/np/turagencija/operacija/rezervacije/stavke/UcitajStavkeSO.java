/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.rezervacije.stavke;

import java.util.List;
import rs.np.turagencija.domen.StavkaRezervacije;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author KORISNIK
 */
public class UcitajStavkeSO extends ApstraktnaGenerickaOperacija {

    List<StavkaRezervacije> stavke;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN fakultativnausluga on stavkaRezervacije.usluga=fakultativnausluga.uslugaID WHERE stavkaRezervacije.rezervacija=" + (int) param;
        stavke = broker.getAll(new StavkaRezervacije(), uslov);

    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

}
