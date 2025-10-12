/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.aranzmani;

import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author KORISNIK
 */
public class IzmeniAranzmanSO extends ApstraktnaGenerickaOperacija {

    private boolean uspesnoIzmenjen = false;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Aranzman)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Aranzman ili je null.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Aranzman) param);
        uspesnoIzmenjen = true;

    }

    public boolean izmenjen() {
        return uspesnoIzmenjen;
    }

}
