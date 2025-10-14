/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.controller;

import java.util.ArrayList;
import java.util.List;
import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.domen.FakultativnaUsluga;
import rs.np.turagencija.domen.Grad;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.domen.Rezervacija;
import rs.np.turagencija.domen.StavkaRezervacije;
import rs.np.turagencija.domen.TipAranzmana;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.forme.ServerskaForma;
import rs.np.turagencija.operacija.aranzmani.DodajAranzmanSO;
import rs.np.turagencija.operacija.aranzmani.IzmeniAranzmanSO;
import rs.np.turagencija.operacija.aranzmani.UcitajAranzmaneSO;
import rs.np.turagencija.operacija.aranzmani.UcitajTipoveAranzmanaSO;
import rs.np.turagencija.operacija.gradovi.UcitajGradoveSO;
import rs.np.turagencija.operacija.klijenti.DodajKlijentaSO;
import rs.np.turagencija.operacija.klijenti.IzmeniKlijentaSO;
import rs.np.turagencija.operacija.klijenti.ObrisiKlijentaSO;
import rs.np.turagencija.operacija.klijenti.UcitajKlijenteSO;
import rs.np.turagencija.operacija.login.LoginOperacija;
import rs.np.turagencija.operacija.rezervacije.DodajRezervacijuSO;
import rs.np.turagencija.operacija.rezervacije.IzmeniRezervacijuSO;
import rs.np.turagencija.operacija.rezervacije.ObrisiRezervacijuSO;
import rs.np.turagencija.operacija.rezervacije.PretraziRezervacijeSO;
import rs.np.turagencija.operacija.rezervacije.UcitajRezervacijeSO;
import rs.np.turagencija.operacija.rezervacije.stavke.UcitajStavkeSO;
import rs.np.turagencija.operacija.usluge.DodajUsluguSO;
import rs.np.turagencija.operacija.usluge.UcitajUslugeSO;
import rs.np.turagencija.server.ObradaKlijentskihZahteva;

/**
 *
 * @author KORISNIK
 */
public class Controller {

    private static Controller inst;
    private List<ObradaKlijentskihZahteva> klijenti = new ArrayList<>();
    private List<Zaposleni> ulogovani = new ArrayList<>();
    private ServerskaForma sf;

    private Controller() {
    }

    public static Controller getInst() {
        if (inst == null) {
            inst = new Controller();
        }
        return inst;
    }

    public List<ObradaKlijentskihZahteva> getKlijenti() {
        return klijenti;
    }

    public void setKlijenti(List<ObradaKlijentskihZahteva> klijenti) {
        this.klijenti = klijenti;
    }

    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    public List<Zaposleni> getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(List<Zaposleni> ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Zaposleni login(Zaposleni z) throws Exception {

        /* LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(z, null);
        System.out.println("Klasa CONTROLLER :" + operacija.getZaposleni());
        if (operacija.getZaposleni() != null) {
            if (!ulogovani.contains(operacija.getZaposleni())) {
                ulogovani.add(operacija.getZaposleni());
                sf.popuniTabeluZaposlenih();
                return operacija.getZaposleni();
            }
        }
         */
        if (!ulogovani.contains(z)) {
            LoginOperacija operacija = new LoginOperacija();
            operacija.izvrsi(z, null);
            System.out.println("Klasa CONTROLLER :" + operacija.getZaposleni());
            if (operacija.getZaposleni() != null) {
                ulogovani.add(operacija.getZaposleni());
                sf.popuniTabeluZaposlenih();
                return operacija.getZaposleni();
            }
        }

        return null;

    }

    public List<Klijent> ucitajKlijente() throws Exception {
        UcitajKlijenteSO operacija = new UcitajKlijenteSO();
        operacija.izvrsi(new Klijent(), null);
        System.out.println("Klasa CONTROLLER :" + operacija.getKlijenti());
        return operacija.getKlijenti();

    }

    public void dodajKlijenta(Klijent k) throws Exception {
        DodajKlijentaSO operacija = new DodajKlijentaSO();
        operacija.izvrsi(k, null);

    }

    public List<Aranzman> ucitajAranzmane() throws Exception {
        UcitajAranzmaneSO ucitajAr = new UcitajAranzmaneSO();
        ucitajAr.izvrsi(new Aranzman(), null/*" join tiparanzmana on aranzman.tiparanzmana=tiparanzmana.tipid"*/);
        System.out.println("Klasa CONTROLLER :" + ucitajAr.getListaAr());
        return ucitajAr.getListaAr();
    }
    //"SELECT * FROM PORUKA P JOIN KORISNIK K ON P.POSILJALAC=K.ID 

    public List<TipAranzmana> ucitajTipoveAr() throws Exception {
        UcitajTipoveAranzmanaSO tipoviOp = new UcitajTipoveAranzmanaSO();
        tipoviOp.izvrsi(new TipAranzmana(), null);
        System.out.println("Klasa CONTROLLER :" + tipoviOp.getTipovi());
        return tipoviOp.getTipovi();
    }

    public void dodajAranzman(Aranzman a) throws Exception {
        DodajAranzmanSO dodajAr = new DodajAranzmanSO();
        dodajAr.izvrsi(a, null);

    }

    public boolean izmeniAranzman(Aranzman azurAr) throws Exception {
        IzmeniAranzmanSO izmeniAr = new IzmeniAranzmanSO();
        izmeniAr.izvrsi(azurAr, null);
        return izmeniAr.izmenjen();
    }

    public boolean izmeniKlijenta(Klijent azurKl) throws Exception {
        IzmeniKlijentaSO izmeniKl = new IzmeniKlijentaSO();
        izmeniKl.izvrsi(azurKl, null);
        return izmeniKl.izmenjen();
    }

    public void odjavi(Zaposleni zaposleni) {
        for (Zaposleni z : ulogovani) {
            if (z.equals(zaposleni)) {
                ulogovani.remove(z);
                sf.popuniTabeluZaposlenih();
                return;
            }

        }
    }

    public List<Rezervacija> ucitajRezervacije() throws Exception {
        UcitajRezervacijeSO ucitajRez = new UcitajRezervacijeSO();
        ucitajRez.izvrsi(new Rezervacija(), null);

        //System.out.println("Klasa CONTROLLER :" + ucitajRez.getLista());
        return ucitajRez.getLista();

    }

    public List<StavkaRezervacije> ucitajStavke(int rezID) throws Exception {
        UcitajStavkeSO ucitajSt = new UcitajStavkeSO();
        ucitajSt.izvrsi(rezID, null);
        System.out.println("Klasa CONTROLLER :" + ucitajSt.getStavke());
        return ucitajSt.getStavke();
    }

    public List<FakultativnaUsluga> ucitajUsluge() throws Exception {
        UcitajUslugeSO ucitajUsl = new UcitajUslugeSO();
        ucitajUsl.izvrsi(new FakultativnaUsluga(), null);

        System.out.println("Klasa CONTROLLER :" + ucitajUsl.getUsluge());
        return ucitajUsl.getUsluge();
    }

    public Rezervacija dodajRezervaciju(Rezervacija rez) throws Exception {
        DodajRezervacijuSO dodajRez = new DodajRezervacijuSO();
        dodajRez.izvrsi(rez, null);
        rez = dodajRez.getRezervacija();
        return rez;

    }

    public boolean obrisiKlijenta(Klijent klijent) throws Exception {
        ObrisiKlijentaSO obrisiKl = new ObrisiKlijentaSO();
        obrisiKl.izvrsi(klijent, null);
        return obrisiKl.obrisan();
    }

    public boolean obrisiRezervaciju(Rezervacija rezZaBrisanje) throws Exception {
        ObrisiRezervacijuSO obrisiRez = new ObrisiRezervacijuSO();
        obrisiRez.izvrsi(rezZaBrisanje, null);
        return obrisiRez.obrisana();

    }

    public boolean izmeniRezervaciju(Rezervacija rezZaIzmenu) throws Exception {
        IzmeniRezervacijuSO izmeniRez = new IzmeniRezervacijuSO();
        izmeniRez.izvrsi(rezZaIzmenu, null);
        return izmeniRez.izmenjeno();
    }

    public void dodajUslugu(FakultativnaUsluga u) throws Exception {
        DodajUsluguSO operacija = new DodajUsluguSO();
        operacija.izvrsi(u, null);
    }

    public List<Rezervacija> pretraziRezervacije(String email) throws Exception {
        PretraziRezervacijeSO pretraziRez = new PretraziRezervacijeSO();
        pretraziRez.izvrsi(new Rezervacija(), email);

        System.out.println("Klasa CONTROLLER :" + pretraziRez.getLista());
        return pretraziRez.getLista();
    }

    /* public void izbaciOkz(Zaposleni zaposleni) {
        for (ObradaKlijentskihZahteva okz : klijenti) {
            if (okz.getZaposleniUlogovani().equals(zaposleni)) {
                klijenti.remove(okz);
            }

        }
    }*/
    public void izbaciOkz(Zaposleni ulogovaniZaposleni) {
        for (ObradaKlijentskihZahteva okz : klijenti) {
            if (okz.getUlogovaniZaposleni().equals(ulogovaniZaposleni)) {
                klijenti.remove(okz);
            }

        }
    }

    public List<Grad> ucitajGradove() throws Exception {

        UcitajGradoveSO gradoviOp = new UcitajGradoveSO();
        gradoviOp.izvrsi(new Grad(), null);
        System.out.println("Klasa CONTROLLER :" + gradoviOp.getGradovi());
        return gradoviOp.getGradovi();

    }
}
