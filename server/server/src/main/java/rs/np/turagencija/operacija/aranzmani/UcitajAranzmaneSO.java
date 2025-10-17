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
 * Sistemska operacija koja ucitava sve aranzmane iz baze podataka.
 * <p>
 * Aranzmani se ucitavaju zajedno sa pripadajucim tipovima aranzmana i gradovima
 * pomocu SQL JOIN upita.
 * </p>
 * Nakon uspesnog izvrsenja, lista aranzmana moze se preuzeti pomocu metode
 * {@link #getListaAr()}.
 *
 * @author Andrija Panovic
 */
public class UcitajAranzmaneSO extends ApstraktnaGenerickaOperacija {

    /**
     * Lista svih aranzmana ucitanih iz baze.
     */
    List<Aranzman> listaAr = new ArrayList<>();

    /**
     * Ova operacija nema definisane preduslove.
     *
     * @param param ne koristi se u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Iz baze ucitava sve aranzmane zajedno sa tipovima i gradovima.
     *
     * @param param instanca klase {@link Aranzman} (koristi se kao tip)
     * @param kljuc String u kom je upisan SQL JOIN upit za tabele grada i
     * tipAranzmana
     * @throws Exception ako dodje do greske prilikom citanja iz baze
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        System.out.println("Kljuc: " + kljuc);
        listaAr = broker.getAll(param, kljuc);

    }

    /**
     * Vraca listu svih aranzmana ucitanih iz baze.
     *
     * @return lista aranzmana
     */
    public List<Aranzman> getListaAr() {
        return listaAr;
    }

}
