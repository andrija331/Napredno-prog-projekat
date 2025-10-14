/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.kontroleri;

import com.sun.java.accessibility.util.AWTEventMonitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.domen.Grad;
import rs.np.turagencija.domen.TipAranzmana;
import rs.np.turagencija.forme.DodajIzmeniAranzmanForma;
import rs.np.turagencija.forme.FormaMod;
import rs.np.turagencija.komunikacija.Komunikacija;
import rs.np.turagencija.kordinator.Kordinator;

/**
 *
 * @author KORISNIK
 */
public class DodajIzmeniAranzmanController {

    private final DodajIzmeniAranzmanForma daf;

    public DodajIzmeniAranzmanController(DodajIzmeniAranzmanForma daf) {
        this.daf = daf;

        daf.setLocationRelativeTo(null);
        addActionListener();
    }

    public void otvoriFormu(FormaMod formaMod) {
        switch (formaMod) {
            case DODAJ:
                pripremiFormuZaDodavanje();
                break;
            case IZMENI:
                pripremiFormuZaIzmenu();
                break;
            default:
                throw new AssertionError();
        }
        daf.setVisible(true);
    }

    private void pripremiFormuZaDodavanje() {
        daf.getjLabelNaslov().setText("DODAVANJE ARANZMANA");
        daf.getjButtonIzmeni().setVisible(false);
        daf.getjButtonDodaj().setVisible(true);
        daf.getjLabelID().setVisible(false);
        daf.getjTextFieldID().setVisible(false);

        List<TipAranzmana> tipovi = Komunikacija.getInstance().ucitajTipove();
        daf.getjComboBoxTipovi().removeAllItems();
        for (TipAranzmana t : tipovi) {
            daf.getjComboBoxTipovi().addItem(t);

        }

        List<Grad> gradovi = Komunikacija.getInstance().ucitajGradove();
        daf.getjComboBoxGradovi().removeAllItems();

        for (Grad g : gradovi) {
            daf.getjComboBoxGradovi().addItem(g);
        }

    }

    private void pripremiFormuZaIzmenu() {
        daf.getjLabelNaslov().setText("IZMENA ARANZMANA");
        daf.getjButtonIzmeni().setVisible(true);
        daf.getjButtonDodaj().setVisible(false);
        daf.getjTextFieldID().setEnabled(false);
        Aranzman a = (Aranzman) Kordinator.getInst().vratiParam("aranzman");
        daf.getjTextFieldNaziv().setText(a.getNaziv());
        daf.getjTextFieldID().setText(a.getAranzmanID() + "");
        daf.getjTextFieldBrojNocenja().setText(a.getBrojNocenja() + "");
        daf.getjTextFieldCena().setText(a.getCena() + "");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String datumString = sdf.format(a.getDatum());
        daf.getjTextFieldDatum().setText(datumString);

        List<TipAranzmana> tipovi = Komunikacija.getInstance().ucitajTipove();
        daf.getjComboBoxTipovi().removeAllItems();
        for (TipAranzmana t : tipovi) {
            daf.getjComboBoxTipovi().addItem(t);
        }
        for (TipAranzmana t : tipovi) {
            if (t.equals(a.getTipAranzmana())) {
                daf.getjComboBoxTipovi().setSelectedItem(t);
            }
        }

        List<Grad> gradovi = Komunikacija.getInstance().ucitajGradove();
        daf.getjComboBoxGradovi().removeAllItems();

        for (Grad g : gradovi) {
            daf.getjComboBoxGradovi().addItem(g);
        }

        for (Grad g : gradovi) {
            if (g.equals(a.getGrad())) {
                daf.getjComboBoxGradovi().setSelectedItem(g);
            }
        }

    }

    private void addActionListener() {
        daf.dodajActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dodaj(e);
                } catch (ParseException ex) {
                    Logger.getLogger(DodajIzmeniAranzmanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void dodaj(ActionEvent e) throws ParseException {
                int brojNocenja;
                double cena;
                Date datum;
                String naziv = daf.getjTextFieldNaziv().getText().trim();
                String datumStr = daf.getjTextFieldDatum().getText().trim();
                TipAranzmana tip = (TipAranzmana) daf.getjComboBoxTipovi().getSelectedItem();
                Grad grad = (Grad) daf.getjComboBoxGradovi().getSelectedItem();

                if (naziv.isEmpty() || datumStr.isEmpty()) {
                    JOptionPane.showMessageDialog(daf, "Neuspesno dodavanje aranzmana. Sva polja moraju biti popunjena.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    brojNocenja = Integer.parseInt(daf.getjTextFieldBrojNocenja().getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(daf, "Neuspsno dodavanje aranzmana. Polje broj nocenja moraju ciniti iskljucivo cifre.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    cena = Double.parseDouble(daf.getjTextFieldCena().getText().trim());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(daf, "Neuspesno dodavanje aranzmana. Polje cena moraju ciniti iskljucivo cifre.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    datum = (new SimpleDateFormat("dd.MM.yyyy")).parse(datumStr);

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(daf, "Neuspesno dodavanje aranzmana. Datum nije unet u adekvatnom formatu.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (datum.before(new Date())) {
                    JOptionPane.showMessageDialog(daf, "Neuspesno dodavanje aranzmana. Datum mora biti u buducnosti.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Aranzman noviAranzman = new Aranzman(-1, naziv, datum, brojNocenja, cena, tip, grad);

                for (Aranzman a : Kordinator.getInst().getSviAranzmani()) {
                    if (a.equals(noviAranzman)) {
                        JOptionPane.showMessageDialog(daf, "Neuspesno dodavanje aranzmana. Uneti aranzman vec postoji.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                try {
                    noviAranzman = Komunikacija.getInstance().dodajAranzman(noviAranzman);
                    if (noviAranzman == null) {
                        JOptionPane.showMessageDialog(daf, "Uspesno dodavanje aranzmana.", "Uspeh.", JOptionPane.INFORMATION_MESSAGE);
                        Kordinator.getInst().osveziFormuZaRadSaAranzmanima();
                        daf.dispose();
                    } else {
                        JOptionPane.showMessageDialog(daf, "Neuspesno dodavanje aranzmana.", "Greska.", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(daf, "Neuspesno dodavanje aranzmana.", "Greska.", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        daf.izmeniActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    izmeni(e);
                } catch (ParseException ex) {
                    Logger.getLogger(DodajIzmeniAranzmanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void izmeni(ActionEvent e) throws ParseException {
                /*  String naziv = daf.getjTextFieldNaziv().getText().trim();
                int brojNocenja = Integer.parseInt(daf.getjTextFieldBrojNocenja().getText().trim());
                double cena = Double.parseDouble(daf.getjTextFieldCena().getText().trim());
                String datumStr = daf.getjTextFieldDatum().getText().trim();
                Date datum = (new SimpleDateFormat("dd.MM.yyyy")).parse(datumStr);
                TipAranzmana tip = (TipAranzmana) daf.getjComboBoxTipovi().getSelectedItem();*/
                int id = Integer.parseInt(daf.getjTextFieldID().getText().trim());
                int brojNocenja;
                double cena;
                Date datum;
                String naziv = daf.getjTextFieldNaziv().getText().trim();
                String datumStr = daf.getjTextFieldDatum().getText().trim();
                TipAranzmana tip = (TipAranzmana) daf.getjComboBoxTipovi().getSelectedItem();
                Grad grad = (Grad) daf.getjComboBoxGradovi().getSelectedItem();

                if (naziv.isEmpty() || datumStr.isEmpty()) {
                    JOptionPane.showMessageDialog(daf, "Neuspesna izmena aranzmana. Sva polja moraju biti popunjena.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    brojNocenja = Integer.parseInt(daf.getjTextFieldBrojNocenja().getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(daf, "Neuspesna izmena aranzmana. Polje broj nocenja moraju ciniti iskljucivo cifre.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    cena = Double.parseDouble(daf.getjTextFieldCena().getText().trim());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(daf, "Neuspesna izmena aranzmana. Polje cena moraju ciniti iskljucivo cifre.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    datum = (new SimpleDateFormat("dd.MM.yyyy")).parse(datumStr);

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(daf, "Neuspesna izmena aranzmana. Datum nije unet u adekvatnom formatu.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (datum.before(new Date())) {
                    JOptionPane.showMessageDialog(daf, "Neuspesno izmena aranzmana. Datum mora biti u buducnosti.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Aranzman a = new Aranzman(id, naziv, datum, brojNocenja, cena, tip, grad);
                boolean izmenjen = false;
                try {
                    izmenjen = Komunikacija.getInstance().izmeniAranzman(a);
                    if (izmenjen) {
                        JOptionPane.showMessageDialog(daf, "Uspesna izmena aranzmana.", "Uspeh.", JOptionPane.INFORMATION_MESSAGE);
                        Kordinator.getInst().osveziFormuZaRadSaAranzmanima();
                        daf.dispose();
                    } else {
                        JOptionPane.showMessageDialog(daf, "Neuspesna izmena aranzmana.", "Greska.", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(daf, "Neuspesna izmena aranzmana.", "Greska.", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        daf.odustaniActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                daf.dispose();
            }
        });

    }

}
