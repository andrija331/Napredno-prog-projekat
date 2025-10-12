/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.aranzmani;

import java.util.List;

import rs.np.turagencija.domen.TipAranzmana;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author KORISNIK
 */
public class UcitajTipoveAranzmanaSO extends ApstraktnaGenerickaOperacija {

    List<TipAranzmana> tipovi;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        tipovi = broker.getAll(param, kljuc);
    }

    public List<TipAranzmana> getTipovi() {
        return tipovi;
    }

}
