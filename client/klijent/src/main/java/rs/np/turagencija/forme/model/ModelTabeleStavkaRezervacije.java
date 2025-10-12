/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.forme.model;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.np.turagencija.domen.StavkaRezervacije;

/**
 *
 * @author KORISNIK
 */
public class ModelTabeleStavkaRezervacije extends AbstractTableModel {

    private List<StavkaRezervacije> lista;
    String[] kolone = {"Redni broj", "Naziv usluge", "Opis usluge", "Cena"};

    public ModelTabeleStavkaRezervacije(List<StavkaRezervacije> lista) {
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
        StavkaRezervacije stavka = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRb();
            case 1:
                return stavka.getUsluga().getNaziv();
            case 2:
                return stavka.getUsluga().getOpis();
            case 3:
                return stavka.getCena() + "€";

            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<StavkaRezervacije> getLista() {
        return lista;
    }

    public void setLista(List<StavkaRezervacije> lista) {
        this.lista = lista;
    }

}
