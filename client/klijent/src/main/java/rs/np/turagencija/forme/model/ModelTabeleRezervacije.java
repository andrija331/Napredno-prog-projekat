/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.forme.model;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.np.turagencija.domen.Rezervacija;

/**
 *
 * @author KORISNIK
 */
public class ModelTabeleRezervacije extends AbstractTableModel {

    private List<Rezervacija> lista;
    String[] kolone = {"ID", "Zaposleni", "Klijent", "Aranzman", "Datum", "Konacna cena"};

    public ModelTabeleRezervacije(List<Rezervacija> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rezervacija r = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return r.getRezervacijaID();
            case 1:
                String zaposleni = r.getZaposleni().getIme() + " " + r.getZaposleni().getPrezime();
                return zaposleni;
            case 2:
                // String klijent = r.getKlijent().getIme() + " " + r.getKlijent().getPrezime();
                return r.getKlijent().getEmail();
            case 3:
                return r.getAranzman().getNaziv();
            case 4:
                return sdf.format(r.getDatum());
            case 5:
                return r.getUkupnaCena() + "€";

            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Rezervacija> getLista() {
        return lista;
    }

    public void setLista(List<Rezervacija> lista) {
        this.lista = lista;
    }

}
