/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.np.turagencija.domen.Zaposleni;

/**
 *
 * @author KORISNIK
 */
public class ModelTabeleZaposleni extends AbstractTableModel {

    List<Zaposleni> lista;
    String[] kolone = {"id", "username", "ime", "prezime"};

    public ModelTabeleZaposleni(List<Zaposleni> lista) {
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
        Zaposleni z = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return z.getZaposleniID();
            case 1:
                return z.getUsername();
            case 2:
                return z.getIme();
            case 3:
                return z.getPrezime();

            default:
                throw new AssertionError();
        }

    }

    public List<Zaposleni> getLista() {
        return lista;
    }

    public void setLista(List<Zaposleni> lista) {
        this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

}
