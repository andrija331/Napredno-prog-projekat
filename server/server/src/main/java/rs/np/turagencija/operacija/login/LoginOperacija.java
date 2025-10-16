/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija.login;

import java.util.List;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija koja omogucava prijavljivanje zaposlenog korisnika na
 * sistem.
 * <p>
 * Operacija proverava da li korisnik sa unetim kredencijalima (korisnicko ime i
 * lozinka) postoji u bazi podataka. Ukoliko postoji, vraca ga iz baze i
 * postavlja u atribut {@code zaposleni}.
 * </p>
 * Ako korisnik ne postoji ili su kredencijali pogresni, atribut
 * {@code zaposleni} ostaje {@code null}.
 *
 * @author Andrija Panovic
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    /**
     * Zaposleni u kog se postavlja odgovarajuci zapolseni iz baze, ako je
     * pronadjen.
     */
    Zaposleni zaposleni = null;

    /**
     * Proverava da li je prosledjeni parametar razlicit od null i da li je
     * odgovarajuce klase.
     *
     * @param param objekat koji treba da bude instanca klase {@link Zaposleni}
     * @throws Exception ako je parametar {@code null} ili nije instanca klase
     * {@code Zaposleni}
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Zaposleni)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zaposleni, ili je null.");
        }
    }

    /**
     * Ucitava sve zaposlene iz baze i proverava da li se neki od njih poklapa
     * sa prosledjenim kredencijalima (korisnicko ime i lozinka).
     *
     * Ako se pronadje odgovarajuci zaposleni, on se postavlja u atribut
     * {@code zaposleni}.
     *
     * @param param objekat tipa {@link Zaposleni} sa unetim kredencijalima
     * @param kljuc dodatni parametar (nije potreban za ovu operaciju, moze biti
     * null)
     * @throws Exception ako dodje do greske prilikom pristupa bazi
     */
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

    /**
     * Vraca zaposlenog koji je uspesno prijavljen na sistem.
     *
     * @return objekat tipa {@link Zaposleni}, ili {@code null} ako prijava nije
     * uspela
     */
    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    /**
     * Postavlja zaposlenog koji je prijavljen na sistem.
     *
     * @param zaposleni objekat tipa {@link Zaposleni}
     */
    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

}
