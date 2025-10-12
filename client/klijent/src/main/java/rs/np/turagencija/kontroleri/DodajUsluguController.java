/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.kontroleri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import rs.np.turagencija.domen.FakultativnaUsluga;
import rs.np.turagencija.forme.DodajUsluguForma;
import rs.np.turagencija.komunikacija.Komunikacija;

/**
 *
 * @author KORISNIK
 */
public class DodajUsluguController {

    private final DodajUsluguForma duf;

    public DodajUsluguController(DodajUsluguForma duf) {
        this.duf = duf;

        duf.setLocationRelativeTo(null);
        addActionListener();
    }

    public void otvoriFormu() {
        duf.setVisible(true);
    }

    private void addActionListener() {

        duf.dodajActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                boolean greskaBroj = false;
                Double cena = 0.0;
                String naziv = duf.getjTextFieldNaziv().getText().trim();
                String opis = duf.getjTextAreaOpis().getText().trim();
                try {
                    cena = Double.parseDouble(duf.getjTextFieldCena().getText());
                } catch (NumberFormatException ex) {
                    greskaBroj = true;
                }
                if (naziv.isEmpty() || opis.isEmpty()) {
                    JOptionPane.showMessageDialog(duf, "Neuspesno dodavanje fakultativne usluge. Sva polja moraju biti popunjena.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (greskaBroj) {
                    JOptionPane.showMessageDialog(duf, "Neuspesno dodavanje fakultativne usluge. Cenu moraju ciniti iskljucivo cifre.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (cena <= 0) {
                    JOptionPane.showMessageDialog(duf, "Neuspesno dodavanje fakultativne usluge. Cena mora biti veca od nule.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                FakultativnaUsluga novaUsluga = new FakultativnaUsluga(-1, naziv, opis, cena);

                List<FakultativnaUsluga> sveUsluge = Komunikacija.getInstance().ucitajUsluge();
                for (FakultativnaUsluga u : sveUsluge) {
                    if (u.equals(novaUsluga)) {
                        JOptionPane.showMessageDialog(duf, "Neuspesno dodavanje fakultativne usluge. Uneta usluga vec postoji.", "Upozorenje.", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                try {
                    novaUsluga = Komunikacija.getInstance().dodajUslugu(novaUsluga);
                    if (novaUsluga == null) {

                        JOptionPane.showMessageDialog(duf, "Uspesno dodavanje fakultativne usluge.", "Uspeh.", JOptionPane.INFORMATION_MESSAGE);

                        duf.dispose();
                    } else {
                        JOptionPane.showMessageDialog(duf, "Neuspesno dodavanje fakultativne usluge.", "Greska.", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(duf, "Neuspesno dodavanje fakultativne usluge.", "Greska.", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        duf.odustaniActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                duf.dispose();
            }
        });

    }

}
