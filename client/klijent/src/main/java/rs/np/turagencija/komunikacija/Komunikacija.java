/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.komunikacija;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.domen.FakultativnaUsluga;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.domen.Rezervacija;
import rs.np.turagencija.domen.StavkaRezervacije;
import rs.np.turagencija.domen.TipAranzmana;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.kordinator.Kordinator;

/**
 *
 * @author KORISNIK
 */
public class Komunikacija {

    private Socket soket;
    private static Komunikacija instance;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;

    }

    public void konekcija() {
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);

        } catch (IOException ex) {
            System.out.println("SERVER NIJE POVEZAN");
        }
    }

    public void zaustavi() {
        Zahtev zahtev = new Zahtev(Operacija.ODJAVA, Kordinator.getInst().getUlogovani());
        posiljalac.posalji(zahtev);
        try {
            soket.close();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Zaposleni login(Zaposleni z) {
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, z);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        z = (Zaposleni) odgovor.getOdgovor();
        return z;
    }

    public List<Klijent> ucitajKlijente() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KLIJENTE, null);
        List<Klijent> klijenti = new ArrayList<>();

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        klijenti = (List<Klijent>) odgovor.getOdgovor();

        return klijenti;
    }

    public Klijent dodajKlijenta(Klijent k) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KLIJENTA, k);

        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        /*  if (odgovor.getOdgovor() == null) {
            System.out.println("uspeh");
        } else {
            System.out.println("greska");
        }*/
        k = (Klijent) odgovor.getOdgovor();
        return k;

    }

    public List<Aranzman> ucitajAranzmane() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_ARANZMANE, null);
        List<Aranzman> aranzmani = new ArrayList<>();

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        aranzmani = (List<Aranzman>) odgovor.getOdgovor();

        return aranzmani;
    }

    public List<TipAranzmana> ucitajTipove() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TIPOVE_ARANZMANA, null);
        List<TipAranzmana> tipovi = new ArrayList<>();

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        tipovi = (List<TipAranzmana>) odgovor.getOdgovor();

        return tipovi;

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* public void dodajAranzman2(Aranzman a) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_ARANZMAN, a);

        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() == null) {
            System.out.println("uspeh");
        } else {
            System.out.println("greska");
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////
    }*/
    public Aranzman dodajAranzman(Aranzman a) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_ARANZMAN, a);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        a = (Aranzman) odgovor.getOdgovor();
        return a;
    }

    public boolean izmeniAranzman(Aranzman a) {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_ARANZMAN, a);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean izmenjen = (Boolean) odgovor.getOdgovor();
        return izmenjen;
    }

    public boolean izmeniKlijenta(Klijent k) {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_KLIJENTA, k);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean izmenjen = (Boolean) odgovor.getOdgovor();
        return izmenjen;

    }

    public List<Rezervacija> ucitajRezervacije() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_REZERVACIJE, null);
        List<Rezervacija> rezervacije = new ArrayList<>();

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        rezervacije = (List<Rezervacija>) odgovor.getOdgovor();

        return rezervacije;

    }

    public List<StavkaRezervacije> ucitajStavke(int rezervacijaID) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE, rezervacijaID);
        List<StavkaRezervacije> stavke = new ArrayList<>();

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        stavke = (List<StavkaRezervacije>) odgovor.getOdgovor();

        return stavke;

    }

    public List<FakultativnaUsluga> ucitajUsluge() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_USLUGE, null);
        List<FakultativnaUsluga> usluge = new ArrayList<>();

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        usluge = (List<FakultativnaUsluga>) odgovor.getOdgovor();

        return usluge;

    }

    public Rezervacija dodajRezervaciju(Rezervacija novaRezervacija) {

        Zahtev zahtev = new Zahtev(Operacija.DODAJ_REZERVACIJU, novaRezervacija);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        novaRezervacija = (Rezervacija) odgovor.getOdgovor();
        return novaRezervacija;
    }

    public boolean obrisiKlijenta(Klijent k) {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KLIJENTA, k);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean izmenjen = (Boolean) odgovor.getOdgovor();
        return izmenjen;
    }

    public boolean izbrisiRezervaciju(Rezervacija rez) {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_REZERVACIJU, rez);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean izmenjen = (Boolean) odgovor.getOdgovor();
        return izmenjen;
    }

    public boolean izmeniRezervaciju(Rezervacija rezervacija) {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_REZERVACIJU, rezervacija);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean izmenjeno = (Boolean) odgovor.getOdgovor();
        return izmenjeno;
    }

    public FakultativnaUsluga dodajUslugu(FakultativnaUsluga novaUsluga) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_USLUGU, novaUsluga);

        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        /*  if (odgovor.getOdgovor() == null) {
            System.out.println("uspeh");
        } else {
            System.out.println("greska");
        }*/
        novaUsluga = (FakultativnaUsluga) odgovor.getOdgovor();
        return novaUsluga;

    }

    public List<Rezervacija> pretraziRezervacije(String email) {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_REZERVACIJE, email);
        List<Rezervacija> rezervacije = new ArrayList<>();

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        rezervacije = (List<Rezervacija>) odgovor.getOdgovor();

        return rezervacije;
    }

}
