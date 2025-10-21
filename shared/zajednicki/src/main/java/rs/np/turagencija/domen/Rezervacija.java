/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja rezervaciju turistickog aranzmana koju klijent pravi preko
 * zaposlenog u turistickoj agenciji.
 *
 * <p>
 * Klasa implementira interfejs {@link ApstraktniDomenskiObjekat} i omogucava
 * mapiranje objekta na odgovarajucu tabelu u bazi podataka ("rezervacija").
 * Svaka rezervacija povezuje klijenta, aranzman, zaposlenog koji je evidentira,
 * kao i listu fakultativnih usluga koje su ukljucene u rezervaciju.</p>
 *
 * <p>
 * Klasa se koristi za dodavanje, otkazivanje, azuriranje i prikaz rezervacija u
 * sistemu turisticke agencije.</p>
 *
 * @author Andrija Panovic
 */
public class Rezervacija implements ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator rezervacije u bazi podataka.
     */
    private int rezervacijaID;

    /**
     * Zaposleni koji je evidentirao rezervaciju.
     */
    private Zaposleni zaposleni;

    /**
     * Klijent koji je kreirao rezervaciju.
     */
    private Klijent klijent;

    /**
     * Aranzman koji je rezervisan.
     */
    private Aranzman aranzman;

    /**
     * Lista stavki rezervacije koje predstavljaju dodatne fakultativne usluge.
     */
    private List<StavkaRezervacije> stavke;

    /**
     * Datum kada je rezervacija kreirana.
     */
    private Date datum;

    /**
     * Ukupna cena rezervacije (ukljucujuci aranzman i fakultativne usluge).
     */
    private Double ukupnaCena;

    /**
     * Podrazumevani konstruktor koji inicijalizuje objekat klase
     * {@code Rezervacija} sa atributima koji imaju default vrednosti.
     */
    public Rezervacija() {
    }

    /**
     * Konstruktor koji kreira objekat klase {@code Rezervacija} sa svim
     * atributima.
     *
     * @param rezervacijaID jedinstveni identifikator rezervacije
     * @param zaposleni zaposleni koji je uneo rezervaciju
     * @param klijent klijent koji je napravio rezervaciju
     * @param aranzman aranzman koji je rezervisan
     * @param stavke lista stavki rezervacije (fakultativne usluge)
     * @param datum datum kreiranja rezervacije
     * @param ukupnaCena ukupna cena rezervacije
     */
    public Rezervacija(int rezervacijaID, Zaposleni zaposleni, Klijent klijent, Aranzman aranzman, List<StavkaRezervacije> stavke, Date datum, Double ukupnaCena) {
        setRezervacijaID(rezervacijaID);
        setZaposleni(zaposleni);
        setKlijent(klijent);
        setAranzman(aranzman);
        setStavke(stavke);
        setDatum(datum);
        setUkupnaCena(ukupnaCena);
    }

    /**
     * Vraca jedinstveni identifikator rezervacije.
     *
     * @return rezervacijaID kao Integer vrednost
     */
    public int getRezervacijaID() {
        return rezervacijaID;
    }

    /**
     * Postavlja jedinstveni identifikator rezervacije.
     *
     * @param rezervacijaID rezervacijaID kao Integer vrednost
     */
    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    /**
     * Vraca zaposlenog koji je evidentirao rezervaciju.
     *
     * @return zaposleni kao objekat klase {@code Zaposleni}
     */
    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    /**
     * Postavlja zaposlenog koji je evidentirao rezervaciju.
     *
     * @param zaposleni zaposleni kao objekat klase {@code Zaposleni}
     */
    public void setZaposleni(Zaposleni zaposleni) {
        if (zaposleni == null) {
            throw new NullPointerException("Zaposleni ne sme biti null");
        }
        this.zaposleni = zaposleni;
    }

    /**
     * Vraca klijenta koji je kreirao rezervaciju.
     *
     * @return klijent kao objekat klase {@code Klijent}
     */
    public Klijent getKlijent() {
        return klijent;
    }

    /**
     * Postavlja klijenta koji je kreirao rezervaciju.
     *
     * @param klijent klijent kao objekat klase {@code Klijent}
     */
    public void setKlijent(Klijent klijent) {
        if (klijent == null) {
            throw new NullPointerException("Klijent ne sme biti null");
        }
        this.klijent = klijent;
    }

    /**
     * Vraca aranzman koji je rezervisan.
     *
     * @return aranzman kao objekat klase {@code Aranzman}
     */
    public Aranzman getAranzman() {
        return aranzman;
    }

    /**
     * Postavlja aranzman koji je rezervisan.
     *
     * @param aranzman aranzman kao objekat klase {@code Aranzman}
     */
    public void setAranzman(Aranzman aranzman) {
        if (aranzman == null) {
            throw new NullPointerException("Aranzman ne sme biti null");
        }
        this.aranzman = aranzman;
    }

    /**
     * Vraca listu stavki rezervacije (fakultativne usluge).
     *
     * @return lista stavki rezervacije kao {@code List<StavkaRezervacije>}
     */
    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    /**
     * Postavlja listu stavki rezervacije (fakultativne usluge).
     *
     * @param stavke lista stavki rezervacije kao
     * {@code List<StavkaRezervacije>}
     */
    public void setStavke(List<StavkaRezervacije> stavke) {
        if (stavke == null) {
            throw new NullPointerException("Lista stavki ne sme biti null");
        }
        this.stavke = stavke;
    }

    /**
     * Vraca datum kada je rezervacija kreirana.
     *
     * @return datum rezervacije kao objekat klase {@code Date}
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * Postavlja datum kada je rezervacija kreirana.
     *
     * @param datum datum rezervacije kao objekat klase {@code Date}
     */
    public void setDatum(Date datum) {
        if (datum == null) {
            throw new NullPointerException("Datum rezervacije ne sme biti null");
        }
        Date danas = new Date();
        if (datum.before(new Date(danas.getTime() - 86400000))) { // 86400000 ms = 1 dan
            throw new IllegalArgumentException("Datum rezervacije ne sme biti u prošlosti");
        }
        this.datum = datum;
    }

    /**
     * Vraca ukupnu cenu rezervacije.
     *
     * @return ukupna cena rezervacije kao Double vrednost
     */
    public Double getUkupnaCena() {
        return ukupnaCena;
    }

    /**
     * Postavlja ukupnu cenu rezervacije.
     *
     * @param ukupnaCena ukupna cena rezervacije kao Double vrednost
     */
    public void setUkupnaCena(Double ukupnaCena) {
        if (ukupnaCena == null) {
            throw new NullPointerException("Ukupna cena ne sme biti null");
        }
        if (ukupnaCena <= 0) {
            throw new IllegalArgumentException("Ukupna cena mora biti veća od nule");
        }
        this.ukupnaCena = ukupnaCena;
    }

    /**
     * Vraca string reprezentaciju objekta klase {@code Rezervacija}.
     *
     * @return String u formatu: "Rezervacija{rezervacijaID=..., stavke=[...]}"
     */
    @Override
    public String toString() {
        return "Rezervacija{" + "rezervacijaID=" + rezervacijaID + ", stavke=" + stavke + '}';
    }

    /**
     * Racuna hash kod objekta na osnovu default vrednosti.
     *
     * @return hash vrednost objekta
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Poredi dva objekta klase {@code Rezervacija} na osnovu klijenta i
     * aranzmana.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako je uneti objekat razlicit od null, ako je klase
     * Rezervacija i ako su klijent i aranzman isti, u suprotnom {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rezervacija other = (Rezervacija) obj;
        if (!Objects.equals(this.klijent, other.klijent)) {
            return false;
        }
        return Objects.equals(this.aranzman, other.aranzman);
    }
    
    @Override
    public String vratiNazivTabele() {
        return "rezervacija";
    }
    
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Rezervacija rez = new Rezervacija();
            rez.setRezervacijaID(rs.getInt("rezervacijaID"));
            rez.setUkupnaCena(rs.getDouble("ukupnaCena"));
            rez.setDatum(rs.getDate("rezervacija.datum"));
            
            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setZaposleniID(rs.getInt("zaposleniID"));
            zaposleni.setIme(rs.getString("zaposleni.ime"));
            zaposleni.setPrezime(rs.getString("zaposleni.prezime"));
            zaposleni.setUsername(rs.getString("zaposleni.username"));
            zaposleni.setPassword(rs.getString("zaposleni.password"));
            
            Klijent klijent = new Klijent();
            klijent.setKlijentID(rs.getInt("klijentID"));
            klijent.setIme(rs.getString("klijent.ime"));
            klijent.setPrezime(rs.getString("klijent.prezime"));
            klijent.setEmail(rs.getString("klijent.email"));
            klijent.setBrojTelefona(rs.getLong("klijent.brojTelefona"));
            
            TipAranzmana tip = new TipAranzmana();
            tip.setTipID(rs.getInt("tipID"));
            tip.setNazivTipa(rs.getString("tipAranzmana.nazivTipa"));
            
            Grad grad = new Grad();
            grad.setGradID(rs.getInt("gradID"));
            grad.setDrzava(rs.getString("grad.drzava"));
            grad.setNazivGrada(rs.getString("grad.imeGrada"));
            grad.setOpis(rs.getString("grad.opis"));
            
            System.out.println("Grad: " + grad);
            
            Aranzman aranzman1 = new Aranzman();
            aranzman.setAranzmanID(rs.getInt("aranzmanID"));
            aranzman.setNaziv(rs.getString("aranzman.naziv"));
            aranzman.setBrojNocenja(rs.getInt("aranzman.brojNocenja"));
            aranzman.setCena(rs.getDouble("aranzman.cena"));
            aranzman.setDatum(rs.getDate("aranzman.datum"));
            aranzman.setTipAranzmana(tip);
            aranzman.setGrad(grad);
            
            rez.setZaposleni(zaposleni);
            rez.setKlijent(klijent);
            rez.setAranzman(aranzman1);
            rez.setStavke(new ArrayList<>());
            
            lista.add(rez);
        }
        return lista;
        
    }
    
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum,ukupnacena,zaposleni,klijent,aranzman";
    }
    
    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + new java.sql.Date(datum.getTime()) + "'," + ukupnaCena + "," + zaposleni.getZaposleniID() + "," + klijent.getKlijentID() + "," + aranzman.getAranzmanID();
    }
    
    @Override
    public String vratiPrimarniKljuc() {
        return "rezervacijaID=" + rezervacijaID;
    }
    
    @Override
    public String vratiVrednostZaIzmenu() {
        return "ukupnaCena=" + ukupnaCena + ", datum='" + new java.sql.Date(datum.getTime()) + "', aranzman=" + aranzman.getAranzmanID()
                + ", klijent=" + klijent.getKlijentID() + ", zaposleni=" + zaposleni.getZaposleniID();
    }
    
}
