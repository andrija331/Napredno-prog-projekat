/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.forme.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.domen.TipAranzmana;

/**
 *
 * @author KORISNIK
 */
public class ModelTabeleAranzman extends AbstractTableModel {

    private List<Aranzman> lista;
    String[] kolone = {"ID aranzmana", "naziv", "datum", "broj nocenja", "cena", "tip aranzmana", "grad"};

    public ModelTabeleAranzman(List<Aranzman> lista) {
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
        Aranzman a = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return a.getAranzmanID();
            case 1:
                return a.getNaziv();
            case 2:
                return sdf.format(a.getDatum());
            case 3:
                return a.getBrojNocenja();
            case 4:
                return a.getCena() + "€";
            case 5:
                return a.getTipAranzmana().getNazivTipa();
            case 6:
                return a.getGrad().getNazivGrada();

            default:
                throw new AssertionError();
        }

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Aranzman> getLista() {
        return lista;
    }

    public void setLista(List<Aranzman> lista) {
        this.lista = lista;
    }

    public void filtriraj(TipAranzmana tip) {
        try {
            System.out.println(lista);
            List<Aranzman> filtriraniAr = lista.stream().filter(a -> a.getTipAranzmana().
                    equals(tip)).collect(Collectors.toList());
            this.lista = filtriraniAr;
            fireTableDataChanged();
        } catch (Exception e) {
            System.out.println("OVDE JE GRESKA!");
        }

        /*
        List<Klijent> filtriranaLista = lista.stream()
                .filter(k -> k.getIme().toLowerCase().contains(pretragaText)
                || k.getPrezime().toLowerCase().contains(pretragaText)).collect(Collectors.toList());
        this.lista = filtriranaLista;
        fireTableDataChanged();
         */
    }

}
