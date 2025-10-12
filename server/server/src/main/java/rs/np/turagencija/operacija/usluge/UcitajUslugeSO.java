/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.usluge;

import java.util.List;
import rs.np.turagencija.domen.FakultativnaUsluga;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author KORISNIK
 */
public class UcitajUslugeSO extends ApstraktnaGenerickaOperacija {

    List<FakultativnaUsluga> usluge;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        usluge = broker.getAll(param, null);

    }

    public List<FakultativnaUsluga> getUsluge() {
        return usluge;
    }

    public void setUsluge(List<FakultativnaUsluga> usluge) {
        this.usluge = usluge;
    }

}
