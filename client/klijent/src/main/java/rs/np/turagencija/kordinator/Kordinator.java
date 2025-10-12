/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.kordinator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.domen.Rezervacija;
import rs.np.turagencija.domen.TipAranzmana;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.forme.DodajIzmeniAranzmanForma;
import rs.np.turagencija.forme.DodajIzmeniKlijentaForma;
import rs.np.turagencija.forme.DodajUsluguForma;
import rs.np.turagencija.forme.FormaAranzmani;
import rs.np.turagencija.forme.FormaMod;
import rs.np.turagencija.forme.GlavnaForma;
import rs.np.turagencija.forme.IzmeniRezervacijuForma;
import rs.np.turagencija.forme.LoginForma;
import rs.np.turagencija.forme.NapraviRezervacijuForma;
import rs.np.turagencija.forme.PregledKlijenataForma;
import rs.np.turagencija.kontroleri.AranzmaniController;
import rs.np.turagencija.kontroleri.DodajIzmeniAranzmanController;
import rs.np.turagencija.kontroleri.DodajIzmeniKlijentaController;
import rs.np.turagencija.kontroleri.DodajUsluguController;
import rs.np.turagencija.kontroleri.GlavnaFormaController;
import rs.np.turagencija.kontroleri.IzmeniRezervacijuController;
import rs.np.turagencija.kontroleri.LoginController;
import rs.np.turagencija.kontroleri.NapraviRezervacijuController;
import rs.np.turagencija.kontroleri.PregledKlijenataController;

/**
 *
 * @author KORISNIK
 */
public class Kordinator {

    private LoginController loginController;
    private GlavnaFormaController gfController;
    private PregledKlijenataController pregledKlController;
    private DodajIzmeniKlijentaController dodajKlController;

    private AranzmaniController aranzmaniController;
    private DodajIzmeniAranzmanController dodajArController;
    private NapraviRezervacijuController napraviRezController;
    private IzmeniRezervacijuController izmeniRezController;
    private DodajUsluguController dodajUsluguController;

    private Map<String, Object> parametri;
    private List<TipAranzmana> tipoviAranzmana;
    private static Kordinator instance;
    private List<Rezervacija> sveRezervacije;
    private List<Aranzman> sviAranzmani;
    private List<Klijent> sviKlijenti;
    private Zaposleni ulogovani;

    private Kordinator() {
        parametri = new HashMap<>();

    }

    public static Kordinator getInst() {
        if (instance == null) {
            instance = new Kordinator();
        }
        return instance;
    }

    public List<Rezervacija> getSveRezervacije() {
        return sveRezervacije;
    }

    public void setSveRezervacije(List<Rezervacija> sveRezervacije) {
        this.sveRezervacije = sveRezervacije;
    }

    public List<Aranzman> getSviAranzmani() {
        return sviAranzmani;
    }

    public void setSviAranzmani(List<Aranzman> sviAranzmani) {
        this.sviAranzmani = sviAranzmani;
    }

    public List<Klijent> getSviKlijenti() {
        return sviKlijenti;
    }

    public void setSviKlijenti(List<Klijent> sviKlijenti) {
        this.sviKlijenti = sviKlijenti;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        gfController = new GlavnaFormaController(new GlavnaForma());
        gfController.otvoriFormu();
    }

    public void otvoriPregledKlijenataFormu() {
        pregledKlController = new PregledKlijenataController(new PregledKlijenataForma());
        pregledKlController.otvoriFormu();

    }

    public void otvoriFormuZaDodavanjeKlijenta() {
        dodajKlController = new DodajIzmeniKlijentaController(new DodajIzmeniKlijentaForma());
        dodajKlController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmenaKlijentaForma() {
        dodajKlController = new DodajIzmeniKlijentaController(new DodajIzmeniKlijentaForma());
        dodajKlController.otvoriFormu(FormaMod.IZMENI);

    }

    public void otvoriFormuZaRadSaAranzmanima() {

        aranzmaniController = new AranzmaniController(new FormaAranzmani());
        aranzmaniController.otvoriFormu();
    }

    public void osveziFormuZaRadSaAranzmanima() {
        aranzmaniController.pripremiFormu();
    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }

    public List<TipAranzmana> getTipoviAranzmana() {
        return tipoviAranzmana;
    }

    public void setTipoviAranzmana(List<TipAranzmana> tipoviAranzmana) {
        this.tipoviAranzmana = tipoviAranzmana;
    }

    public void otvoriDodajAngazmanFormu() {
        dodajArController = new DodajIzmeniAranzmanController(new DodajIzmeniAranzmanForma());
        dodajArController.otvoriFormu(FormaMod.DODAJ);

    }

    public void otvoriIzmeniAranzmanFormu() {
        dodajArController = new DodajIzmeniAranzmanController(new DodajIzmeniAranzmanForma());
        dodajArController.otvoriFormu(FormaMod.IZMENI);
    }

    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    public Object vratiParam(String s) {
        return parametri.get(s);
    }

    public void osveziFormuZaRadSaKlijentima() {
        pregledKlController.pripremiFormu();

    }

    public void otvoriNapraviRezervacijuFormu() {

        napraviRezController = new NapraviRezervacijuController(new NapraviRezervacijuForma());
        napraviRezController.otvoriFormu();

    }

    public void otvoriIzmeniRezervacijuFormu(Rezervacija rez) {
        izmeniRezController = new IzmeniRezervacijuController(new IzmeniRezervacijuForma());
        izmeniRezController.otvoriFormu(rez);

    }

    public void osveziTabeluSaRezervacijama() {
        gfController.popuniTabeluRezervacije();
    }

    public void otvoriDodajUsluguFormu() {
        dodajUsluguController = new DodajUsluguController(new DodajUsluguForma());
        dodajUsluguController.otvoriFormu();
    }

}
