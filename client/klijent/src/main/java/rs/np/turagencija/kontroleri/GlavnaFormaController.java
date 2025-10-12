/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.kontroleri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import rs.np.turagencija.domen.Rezervacija;
import rs.np.turagencija.domen.StavkaRezervacije;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.forme.GlavnaForma;
import rs.np.turagencija.forme.model.ModelTabeleRezervacije;
import rs.np.turagencija.forme.model.ModelTabeleStavkaRezervacije;
import rs.np.turagencija.komunikacija.Komunikacija;
import rs.np.turagencija.kordinator.Kordinator;

/**
 *
 * @author KORISNIK
 */
public class GlavnaFormaController {

    private GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;

        gf.setLocationRelativeTo(null);

        addActionListener();
        addMouseListener();

    }

    private void addActionListener() {

        gf.odjavaActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Komunikacija.getInstance().zaustavi();
                gf.ugasiFormu();
            }
        });

        gf.pretragaActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = gf.getjTextFieldEmail().getText().trim();
                List<Rezervacija> rezervacije = Komunikacija.getInstance().pretraziRezervacije(email);
                if (rezervacije.size() > 0) {

                    rezervacije.sort(Comparator.comparing(d -> d.getDatum()));
                    ModelTabeleRezervacije mtr = new ModelTabeleRezervacije(rezervacije);
                    gf.getjTableRezervacije().setModel(mtr);
                    JOptionPane.showMessageDialog(gf, "Sistem je nasao rezervacije po zadatoj vrednosti.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(gf, "Neuspesna pretraga rezervacija. Nema klijenata sa zadatim vrednostima.", "Upozorenje.", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        gf.resetujPretraguActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popuniTabeluRezervacije();
                gf.getjTextFieldEmail().setText("");
            }
        });

        gf.napraviRezervacijuActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kordinator.getInst().otvoriNapraviRezervacijuFormu();
            }
        });
        gf.izmeniRezervacijuActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = gf.getjTableRezervacije().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(gf, "Neuspesno ucitavanje rezervacije. Nije izabrana rezervacija.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    ModelTabeleRezervacije mtr = (ModelTabeleRezervacije) gf.getjTableRezervacije().getModel();
                    Rezervacija rez = mtr.getLista().get(selektovaniRed);
                    Kordinator.getInst().otvoriIzmeniRezervacijuFormu(rez);
                }

            }
        });

        gf.izbrisiRezervacijuActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = gf.getjTableRezervacije().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(gf, "Neuspesno otkazivanje rezervacije. Nije izabrana rezervacija.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    ModelTabeleRezervacije mtr = (ModelTabeleRezervacije) gf.getjTableRezervacije().getModel();
                    Rezervacija rez = mtr.getLista().get(selektovaniRed);
                    boolean izbrisan = Komunikacija.getInstance().izbrisiRezervaciju(rez);

                    if (izbrisan) {
                        JOptionPane.showMessageDialog(gf, "Uspesno otkazivanje rezervacije.", "Uspeh.", JOptionPane.INFORMATION_MESSAGE);
                        popuniTabeluRezervacije();

                        List<StavkaRezervacije> stavke = new ArrayList<>();
                        ModelTabeleStavkaRezervacije mtsr = new ModelTabeleStavkaRezervacije(stavke);
                        gf.getjTableStavkeRezervacije().setModel(mtsr);

                    } else {
                        JOptionPane.showMessageDialog(gf, "Neuspesno otkazivanje rezervacije.", "Greska.", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

    }

    public void otvoriFormu() {
        try {
            Zaposleni ulogovani = Kordinator.getInst().getUlogovani();
            String imePrezime = "Ulogovani: " + ulogovani.getIme() + " " + ulogovani.getPrezime();
            gf.setVisible(true);
            gf.getjLabelUlogovani().setText(imePrezime);
            popuniTabeluRezervacije();

            List<StavkaRezervacije> stavke = new ArrayList<>();
            ModelTabeleStavkaRezervacije mtsr = new ModelTabeleStavkaRezervacije(stavke);
            gf.getjTableStavkeRezervacije().setModel(mtsr);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void popuniTabeluRezervacije() {
        List<Rezervacija> rezervacije = Komunikacija.getInstance().ucitajRezervacije();
        rezervacije.sort(Comparator.comparing(d -> d.getDatum()));
        /*sortirano prema datumu*/
        Kordinator.getInst().setSveRezervacije(rezervacije);
        ModelTabeleRezervacije mtr = new ModelTabeleRezervacije(rezervacije);
        gf.getjTableRezervacije().setModel(mtr);
    }

    private void addMouseListener() {

        gf.getjTableRezervacije().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = gf.getjTableRezervacije().getSelectedRow();
                if (red != -1) {

                    ModelTabeleRezervacije mtr = (ModelTabeleRezervacije) gf.getjTableRezervacije().getModel();
                    Rezervacija rez = mtr.getLista().get(red);
                    List<StavkaRezervacije> stavke = Komunikacija.getInstance().ucitajStavke(rez.getRezervacijaID());
                    stavke.sort(Comparator.comparingInt(s -> s.getRb()));
                    /* sortiranje stavki */
                    ModelTabeleStavkaRezervacije mtsr = new ModelTabeleStavkaRezervacije(stavke);
                    gf.getjTableStavkeRezervacije().setModel(mtsr);

                }
            }

        });
        gf.getjTableStavkeRezervacije().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = gf.getjTableStavkeRezervacije().getSelectedRow();
                if (red != -1) {
                    ModelTabeleStavkaRezervacije mtsr = (ModelTabeleStavkaRezervacije) gf.getjTableStavkeRezervacije().getModel();
                    StavkaRezervacije stavka = mtsr.getLista().get(red);
                    String opis = "Opis usluge: " + stavka.getUsluga().getOpis();
                    JOptionPane.showMessageDialog(gf, opis, "Opis", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });
    }

}
