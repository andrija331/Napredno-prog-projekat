/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.kontroleri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.forme.DodajIzmeniKlijentaForma;
import rs.np.turagencija.forme.FormaMod;
import rs.np.turagencija.komunikacija.Komunikacija;
import rs.np.turagencija.kordinator.Kordinator;

/**
 *
 * @author KORISNIK
 */
public class DodajIzmeniKlijentaController {

    private final DodajIzmeniKlijentaForma dkf;

    public DodajIzmeniKlijentaController(DodajIzmeniKlijentaForma dkf) {
        this.dkf = dkf;

        dkf.setLocationRelativeTo(null);
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
        dkf.setVisible(true);
    }

    private void addActionListener() {
        dkf.dodajActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                boolean greskaBroj = false;
                long brTel = 0;
                String ime = dkf.getjTextFieldIme().getText().trim();
                String prezime = dkf.getjTextFieldPrezime().getText().trim();
                String email = dkf.getjTextFieldEmail().getText().trim();
                try {
                    brTel = Long.parseLong(dkf.getjTextFieldBrojTelefona().getText());
                } catch (NumberFormatException ex) {
                    greskaBroj = true;
                }

                if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(dkf, "Neuspesno dodavanje klijenta. Sva polja moraju biti popunjena.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (greskaBroj) {
                    JOptionPane.showMessageDialog(dkf, "Neuspesno dodavanje klijenta. Broj telefona moraju ciniti iskljucivo cifre.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(dkf, "Neuspesno dodavanje klijenta. Email mora sadrzati '@' simbol.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (brTel < 600000000 || brTel > 699999999) {
                    JOptionPane.showMessageDialog(dkf, "Neuspesno dodavanje klijenta. Broj telefona mora da bude u adekvatnom formatu(0612345678 ili 612345678).", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                /*
                try {
                    Komunikacija.getInstance().dodajKlijenta(k);
                    JOptionPane.showMessageDialog(dkf, "Uspesno dodavanje klijenta.", "Uspeh.", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, "Klijent nije dodat.", "Greska.", JOptionPane.ERROR_MESSAGE);
                }*/

                Klijent noviKlijent = new Klijent(-1, ime, prezime, email, brTel);

                for (Klijent kl : Kordinator.getInst().getSviKlijenti()) {
                    if (kl.getBrojTelefona() == noviKlijent.getBrojTelefona()) {
                        JOptionPane.showMessageDialog(dkf, "Neuspesno dodavanje klijenta. Postoji klijent sa unetim brojem telefona.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (kl.getEmail().equals(noviKlijent.getEmail())) {
                        JOptionPane.showMessageDialog(dkf, "Neuspesno dodavanje klijenta. Postoji klijent sa unetom email adresom.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                try {
                    noviKlijent = Komunikacija.getInstance().dodajKlijenta(noviKlijent);
                    if (noviKlijent == null) {

                        JOptionPane.showMessageDialog(dkf, "Uspesno dodavanje klijenta.", "Uspeh.", JOptionPane.INFORMATION_MESSAGE);
                        Kordinator.getInst().osveziFormuZaRadSaKlijentima();
                        dkf.dispose();
                    } else {
                        JOptionPane.showMessageDialog(dkf, "Neuspesno dodavanje klijenta.", "Greska.", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, "Neuspesno dodavanje klijenta.", "Greska.", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        dkf.izmeniActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                boolean greskaBroj = false;
                long brTel = 0;
                String ime = dkf.getjTextFieldIme().getText().trim();
                String prezime = dkf.getjTextFieldPrezime().getText().trim();
                String email = dkf.getjTextFieldEmail().getText().trim();
                int id = Integer.parseInt(dkf.getjTextFieldID().getText().trim());
                try {
                    brTel = Long.parseLong(dkf.getjTextFieldBrojTelefona().getText());
                } catch (NumberFormatException ex) {
                    greskaBroj = true;
                }

                if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(dkf, "Neuspesna izmena klijenta. Sva polja moraju biti popunjena.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (greskaBroj) {
                    JOptionPane.showMessageDialog(dkf, "Neuspesna izmena klijenta. Broj telefona moraju ciniti iskljucivo cifre.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(dkf, "Neuspesna izmena klijenta. Email mora sadrzati '@' simbol.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (brTel < 600000000 || brTel > 699999999) {
                    JOptionPane.showMessageDialog(dkf, "Neuspesna izmena klijenta. Broj telefona mora da bude u adekvatnom formatu(0612345678 ili 612345678).", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Klijent k = new Klijent(id, ime, prezime, email, brTel);
                boolean izmenjen = false;
                try {
                    izmenjen = Komunikacija.getInstance().izmeniKlijenta(k);
                    if (izmenjen) {

                        JOptionPane.showMessageDialog(dkf, "Uspesna izmena klijenta.", "Uspeh.", JOptionPane.INFORMATION_MESSAGE);
                        Kordinator.getInst().osveziFormuZaRadSaKlijentima();
                        dkf.dispose();
                    } else {
                        JOptionPane.showMessageDialog(dkf, "Neuspesna izmena klijenta.", "Greska.", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, "Neuspesna izmena klijenta.", "Greska.", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        dkf.odustaniActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dkf.dispose();
            }
        });

    }

    private void pripremiFormuZaDodavanje() {
        dkf.getjLabelNaslov().setText("DODAVANJE KLIJENTA");
        dkf.getjButtonDodaj().setVisible(true);
        dkf.getjButtonIzmeni().setVisible(false);
        dkf.getjLabelID().setVisible(false);
        dkf.getjTextFieldID().setVisible(false);

    }

    private void pripremiFormuZaIzmenu() {
        dkf.getjLabelNaslov().setText("IZMENA KLIJENTA");
        dkf.getjButtonDodaj().setVisible(false);
        dkf.getjButtonIzmeni().setVisible(true);
        dkf.getjTextFieldID().setEnabled(false);

        //Aranzman a = (Aranzman) Kordinator.getInst().vratiParam("aranzman");
        Klijent k = (Klijent) Kordinator.getInst().vratiParam("klijent");
        dkf.getjTextFieldID().setText(k.getKlijentID() + "");
        dkf.getjTextFieldIme().setText(k.getIme());
        dkf.getjTextFieldPrezime().setText(k.getPrezime());
        dkf.getjTextFieldEmail().setText(k.getEmail());
        dkf.getjTextFieldBrojTelefona().setText(String.valueOf(0) + k.getBrojTelefona() + "");
    }

}
