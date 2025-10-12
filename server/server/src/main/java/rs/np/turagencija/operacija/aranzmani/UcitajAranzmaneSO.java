/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.aranzmani;

import java.util.ArrayList;
import java.util.List;
import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author KORISNIK
 */
public class UcitajAranzmaneSO extends ApstraktnaGenerickaOperacija {

    List<Aranzman> listaAr = new ArrayList<>();

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaAr = broker.getAll(param, kljuc);

    }

    public List<Aranzman> getListaAr() {
        return listaAr;
    }

}
