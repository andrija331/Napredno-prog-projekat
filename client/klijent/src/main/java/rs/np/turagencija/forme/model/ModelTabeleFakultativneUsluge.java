/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.np.turagencija.domen.FakultativnaUsluga;

/**
 *
 * @author KORISNIK
 */
public class ModelTabeleFakultativneUsluge extends AbstractTableModel {

    private List<FakultativnaUsluga> lista;
    String[] kolone = {"ID", "Naziv usluge", "Cena"};

    public ModelTabeleFakultativneUsluge(List<FakultativnaUsluga> lista) {
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
        FakultativnaUsluga usluga = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return usluga.getUslugaID();
            case 1:
                return usluga.getNaziv();

            case 2:
                return usluga.getCena() + "€";

            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<FakultativnaUsluga> getLista() {
        return lista;
    }

    public void setLista(List<FakultativnaUsluga> lista) {
        this.lista = lista;
    }

    public void dodajUslugu(FakultativnaUsluga izabranaUsluga) {
        lista.add(izabranaUsluga);
        fireTableDataChanged();
    }

    public void izbrisiUslugu(FakultativnaUsluga izabranaUsluga) {
        lista.remove(izabranaUsluga);
        fireTableDataChanged();
    }

}
