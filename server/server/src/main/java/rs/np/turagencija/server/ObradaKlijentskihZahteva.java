/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.np.turagencija.controller.Controller;
import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.domen.FakultativnaUsluga;
import rs.np.turagencija.domen.Grad;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.domen.Rezervacija;
import rs.np.turagencija.domen.StavkaRezervacije;
import rs.np.turagencija.domen.TipAranzmana;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.komunikacija.Odgovor;
import rs.np.turagencija.komunikacija.Posiljalac;
import rs.np.turagencija.komunikacija.Primalac;
import rs.np.turagencija.komunikacija.Zahtev;

/**
 *
 * @author KORISNIK
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket s;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;
    Zaposleni ulogovaniZaposleni;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
        posiljalac = new Posiljalac(s);
        primalac = new Primalac(s);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();
                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        Zaposleni z = (Zaposleni) zahtev.getParam();
                        ulogovaniZaposleni = z;

                        z = Controller.getInst().login(z);

                        odgovor.setOdgovor(z);

                        break;
                    case UCITAJ_KLIJENTE:
                        List<Klijent> klijenti = Controller.getInst().ucitajKlijente();

                        odgovor.setOdgovor(klijenti);

                        break;
                    case UCITAJ_ARANZMANE:
                        List<Aranzman> aranzmani = Controller.getInst().ucitajAranzmane();

                        odgovor.setOdgovor(aranzmani);

                        break;
                    case DODAJ_KLIJENTA:
                        Klijent k = (Klijent) zahtev.getParam();

                        Controller.getInst().dodajKlijenta(k);
                        odgovor.setOdgovor(null);

                        break;
                    case DODAJ_USLUGU:
                        FakultativnaUsluga u = (FakultativnaUsluga) zahtev.getParam();

                        Controller.getInst().dodajUslugu(u);
                        odgovor.setOdgovor(null);

                        break;
                    case DODAJ_ARANZMAN:
                        Aranzman a = (Aranzman) zahtev.getParam();

                        Controller.getInst().dodajAranzman(a);
                        odgovor.setOdgovor(null);
                        break;
                    case IZMENI_ARANZMAN:
                        Aranzman azurAr = (Aranzman) zahtev.getParam();

                        boolean izmenjen = Controller.getInst().izmeniAranzman(azurAr);
                        odgovor.setOdgovor(izmenjen);
                        break;
                    case IZMENI_KLIJENTA:
                        Klijent azurKl = (Klijent) zahtev.getParam();

                        boolean izmenjenKl = Controller.getInst().izmeniKlijenta(azurKl);
                        odgovor.setOdgovor(izmenjenKl);
                        break;
                    case ODJAVA:
                        // System.out.println("Stigao je do ODJAVE U OKZ.");
                        Zaposleni zaposleni = (Zaposleni) zahtev.getParam();

                        Controller.getInst().odjavi(zaposleni);

                        prekini();
                        break;
                    case UCITAJ_TIPOVE_ARANZMANA:
                        List<TipAranzmana> tipovi = Controller.getInst().ucitajTipoveAr();

                        odgovor.setOdgovor(tipovi);

                        break;
                    case UCITAJ_GRADOVE:
                        List<Grad> gradovi = Controller.getInst().ucitajGradove();

                        odgovor.setOdgovor(gradovi);

                        break;
                    case UCITAJ_REZERVACIJE:
                        List<Rezervacija> rezervacije = Controller.getInst().ucitajRezervacije();
                        //System.out.println("OKZ SVE REZERVACIJE SA STAVKAMA: " + rezervacije);
                        odgovor.setOdgovor(rezervacije);

                        break;
                    case PRETRAZI_REZERVACIJE:
                        String email = (String) zahtev.getParam();
                        List<Rezervacija> nadjeneRez = Controller.getInst().pretraziRezervacije(email);
                        //System.out.println("OKZ SVE REZERVACIJE SA STAVKAMA: " + rezervacije);
                        odgovor.setOdgovor(nadjeneRez);

                        break;
                    case UCITAJ_STAVKE:
                        int rezID = (int) zahtev.getParam();
                        List<StavkaRezervacije> stavke = Controller.getInst().ucitajStavke(rezID);

                        odgovor.setOdgovor(stavke);
                        break;
                    case UCITAJ_USLUGE:
                        List<FakultativnaUsluga> usluge = Controller.getInst().ucitajUsluge();

                        odgovor.setOdgovor(usluge);
                        break;
                    case DODAJ_REZERVACIJU:
                        Rezervacija rezZaDodavanje = (Rezervacija) zahtev.getParam();

                        rezZaDodavanje = Controller.getInst().dodajRezervaciju(rezZaDodavanje);
                        odgovor.setOdgovor(rezZaDodavanje);
                        break;

                    case OBRISI_KLIJENTA:
                        Klijent klijent = (Klijent) zahtev.getParam();

                        boolean obrisan = Controller.getInst().obrisiKlijenta(klijent);
                        odgovor.setOdgovor(obrisan);
                        break;
                    case OBRISI_REZERVACIJU:
                        Rezervacija rezZaBrisanje = (Rezervacija) zahtev.getParam();

                        boolean obrisanaRez = Controller.getInst().obrisiRezervaciju(rezZaBrisanje);
                        odgovor.setOdgovor(obrisanaRez);
                        break;

                    case IZMENI_REZERVACIJU:
                        Rezervacija rezZaIzmenu = (Rezervacija) zahtev.getParam();

                        boolean izmenjenaRez = Controller.getInst().izmeniRezervaciju(rezZaIzmenu);
                        odgovor.setOdgovor(izmenjenaRez);
                        break;

                    default:
                        System.out.println("Greska, ta operacija ne postoji");
                }
                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                //System.out.println("PROBLEM U OKZ");

                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public Zaposleni getUlogovaniZaposleni() {
        return ulogovaniZaposleni;
    }

    public void setUlogovaniZaposleni(Zaposleni ulogovaniZaposleni) {
        this.ulogovaniZaposleni = ulogovaniZaposleni;
    }

    public void prekini() {

        kraj = true;
        try {

            s.close();
            interrupt();

        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
