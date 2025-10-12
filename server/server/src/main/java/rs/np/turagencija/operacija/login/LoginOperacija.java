/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.login;

import java.util.List;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author KORISNIK
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Zaposleni zaposleni = null;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Zaposleni)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zaposleni, ili je null.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Zaposleni> sviZaposleni = broker.getAll((Zaposleni) param, null);
        System.out.println("KLASA LoginOperacija SO" + sviZaposleni);

        for (Zaposleni z : sviZaposleni) {
            if (z.equals((Zaposleni) param)) {
                zaposleni = z;
                return;
            }
        }
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

}
