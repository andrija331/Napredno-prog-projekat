/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.klijenti;

import java.util.List;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author KORISNIK
 */
public class UcitajKlijenteSO extends ApstraktnaGenerickaOperacija {

    List<Klijent> klijenti;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        klijenti = broker.getAll(param, kljuc);
    }

    public List<Klijent> getKlijenti() {
        return klijenti;
    }

}
