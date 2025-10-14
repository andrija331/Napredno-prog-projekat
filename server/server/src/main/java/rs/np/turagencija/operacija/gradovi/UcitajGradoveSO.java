/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.gradovi;

import java.util.List;
import rs.np.turagencija.domen.Grad;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author KORISNIK
 */
public class UcitajGradoveSO extends ApstraktnaGenerickaOperacija {

    List<Grad> gradovi;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        gradovi = broker.getAll(param, kljuc);
    }

    public List<Grad> getGradovi() {
        return gradovi;
    }

}
