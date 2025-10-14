/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.kontroleri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.List;
import javax.swing.JOptionPane;
import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.domen.TipAranzmana;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.forme.FormaAranzmani;
import rs.np.turagencija.forme.model.ModelTabeleAranzman;
import rs.np.turagencija.komunikacija.Komunikacija;
import rs.np.turagencija.kordinator.Kordinator;

/**
 *
 * @author KORISNIK
 */
public class AranzmaniController {

    private final FormaAranzmani fa;

    public AranzmaniController(FormaAranzmani fa) {
        this.fa = fa;

        fa.setLocationRelativeTo(null);

        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        fa.setVisible(true);

    }

    public void pripremiFormu() {

        List<Aranzman> aranzmani = Komunikacija.getInstance().ucitajAranzmane();
        System.out.println(aranzmani);
        Kordinator.getInst().setSviAranzmani(aranzmani);

        ModelTabeleAranzman mta = new ModelTabeleAranzman(aranzmani);
        fa.getjTableAranzmani().setModel(mta);

        Zaposleni ulogovani = Kordinator.getInst().getUlogovani();
        String imePrezime = "Ulogovani: " + ulogovani.getIme() + " " + ulogovani.getPrezime();
        fa.getjLabelUlogovani().setText(imePrezime);

        List<TipAranzmana> tipovi = Komunikacija.getInstance().ucitajTipove();

        fa.getjComboBoxTipovi().removeAllItems();
        for (TipAranzmana t : tipovi) {
            fa.getjComboBoxTipovi().addItem(t);

        }
    }

    private void addActionListeners() {
        fa.izmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = fa.getjTableAranzmani().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(fa, "Neuspesna izmena aranzmana. Nije izabran aranzman.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                } else {
                    ModelTabeleAranzman mta = (ModelTabeleAranzman) fa.getjTableAranzmani().getModel();
                    Aranzman a = mta.getLista().get(red);
                    Kordinator.getInst().dodajParam("aranzman", a);
                    Kordinator.getInst().otvoriIzmeniAranzmanFormu();
                }

            }

        });
        fa.dodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kordinator.getInst().otvoriDodajAngazmanFormu();
            }
        });
        fa.pretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipAranzmana tip = (TipAranzmana) fa.getjComboBoxTipovi().getSelectedItem();
                ModelTabeleAranzman mta = (ModelTabeleAranzman) fa.getjTableAranzmani().getModel();
                try {
                    mta.filtriraj(tip);
                } catch (Exception eaa) {
                    System.out.println("OVDEEE IPAK");
                }

            }
        });

        fa.resetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kordinator.getInst().osveziFormuZaRadSaAranzmanima();

            }
        });

    }

}
