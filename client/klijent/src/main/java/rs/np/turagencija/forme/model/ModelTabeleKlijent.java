/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.forme.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import rs.np.turagencija.domen.Klijent;

/**
 *
 * @author KORISNIK
 */
public class ModelTabeleKlijent extends AbstractTableModel {

    List<Klijent> lista;
    String[] kolone = {"ID", "Ime", "Prezime", "Email", "Broj telefona"};

    public ModelTabeleKlijent(List<Klijent> lista) {
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
        Klijent k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getKlijentID();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getEmail();
            case 4:
                return "0" + k.getBrojTelefona();

            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Klijent> getLista() {
        return lista;
    }

    public void pretrazi(String pretragaText) {
        List<Klijent> filtriranaLista = lista.stream()
                .filter(k -> k.getIme().toLowerCase().contains(pretragaText)
                || k.getPrezime().toLowerCase().contains(pretragaText)).collect(Collectors.toList());
        this.lista = filtriranaLista;
        fireTableDataChanged();

    }
    /*
      public void pretragaKorisnika(){
        String searchText=jTextFieldPretraga.getText().toLowerCase();
        List<Korisnik> filteredKorisnici=listaKorisnika.stream().filter(k->k.getIme().toLowerCase().contains(searchText) ||
                k.getPrezime().toLowerCase().contains(searchText)).collect(Collectors.toList());
        ModelTabeleKorisnici mtk=new ModelTabeleKorisnici(filteredKorisnici);
        jTableKorisnici.setModel(mtk);
        
    }*/

}
