/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.kontroleri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.forme.PregledKlijenataForma;
import rs.np.turagencija.forme.model.ModelTabeleKlijent;
import rs.np.turagencija.komunikacija.Komunikacija;
import rs.np.turagencija.kordinator.Kordinator;

/**
 *
 * @author KORISNIK
 */
public class PregledKlijenataController {

    private final PregledKlijenataForma pkf;

    public PregledKlijenataController(PregledKlijenataForma pkf) {
        this.pkf = pkf;

        pkf.setLocationRelativeTo(null);

        addActionListeners();

    }

    public void otvoriFormu() {
        pripremiFormu();
        pkf.setVisible(true);
        Zaposleni ulogovani = Kordinator.getInst().getUlogovani();
        String imePrezime = "Ulogovani: " + ulogovani.getIme() + " " + ulogovani.getPrezime();
        pkf.getjLabelUlogovani().setText(imePrezime);
    }

    public void pripremiFormu() {
        List<Klijent> klijenti = Komunikacija.getInstance().ucitajKlijente();
        Kordinator.getInst().setSviKlijenti(klijenti);
        ModelTabeleKlijent mtk = new ModelTabeleKlijent(klijenti);
        pkf.getjTableKlijenti().setModel(mtk);
    }

    private void addActionListeners() {
        pkf.izmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKlijenti().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Neuspesno ucitavanje klijenta. Klijent nije izabran.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                } else {
                    ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                    Klijent k = mtk.getLista().get(red);
                    Kordinator.getInst().dodajParam("klijent", k);
                    Kordinator.getInst().otvoriIzmenaKlijentaForma();
                }

            }

        });

        pkf.obrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKlijenti().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Neuspesno brisanje klijenta. Klijent nije izabran.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int potvrda = JOptionPane.showConfirmDialog(pkf, "Da li ste sigurni da zelite da izbrisete klijenta?", "Potvrda", JOptionPane.YES_NO_CANCEL_OPTION);
                if (potvrda == JOptionPane.YES_OPTION) {
                    ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                    Klijent k = mtk.getLista().get(red);
                    boolean obrisan = Komunikacija.getInstance().obrisiKlijenta(k);
                    if (obrisan) {
                        JOptionPane.showMessageDialog(pkf, "Uspesno brisanje klijenta.", "Uspeh.", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                        Kordinator.getInst().osveziTabeluSaRezervacijama();
                    } else {
                        JOptionPane.showMessageDialog(pkf, "Neuspesno brisanje klijenta.", "Greska.", JOptionPane.ERROR_MESSAGE);
                    }

                }
                /*ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                    Klijent k = mtk.getLista().get(red);
                    boolean obrisan = Komunikacija.getInstance().obrisiKlijenta(k);
                    if (obrisan) {
                        JOptionPane.showMessageDialog(pkf, "Uspesno brisanje klijenta.", "Uspeh.", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                        Kordinator.getInst().osveziTabeluSaRezervacijama();
                    } else {
                        JOptionPane.showMessageDialog(pkf, "Neuspesno brisanje klijenta.", "Greska.", JOptionPane.ERROR_MESSAGE);
                    }*/

            }

        });
        pkf.dodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kordinator.getInst().otvoriFormuZaDodavanjeKlijenta();
            }
        });
        pkf.pretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pretragaText = pkf.getjTextFieldPretraga().getText().toLowerCase().trim();
                ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                mtk.pretrazi(pretragaText);
            }
        });
        pkf.resetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kordinator.getInst().osveziFormuZaRadSaKlijentima();
                pkf.getjTextFieldPretraga().setText("");
            }
        });

        /*
         public void pretragaKorisnika(){
        String searchText=jTextFieldPretraga.getText().toLowerCase();
        List<Korisnik> filteredKorisnici=listaKorisnika.stream().filter(k->k.getIme().toLowerCase().contains(searchText) ||
                k.getPrezime().toLowerCase().contains(searchText)).collect(Collectors.toList());
        ModelTabeleKorisnici mtk=new ModelTabeleKorisnici(filteredKorisnici);
        jTableKorisnici.setModel(mtk);
        
    }
         */
    }

}
