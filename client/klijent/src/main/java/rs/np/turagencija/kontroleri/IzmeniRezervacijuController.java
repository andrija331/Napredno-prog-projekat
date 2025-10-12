/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.kontroleri;

import com.sun.java.accessibility.util.AWTEventMonitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import rs.np.turagencija.domen.FakultativnaUsluga;
import rs.np.turagencija.domen.Rezervacija;
import rs.np.turagencija.domen.StavkaRezervacije;
import rs.np.turagencija.forme.IzmeniRezervacijuForma;
import rs.np.turagencija.forme.model.ModelTabeleFakultativneUsluge;
import rs.np.turagencija.komunikacija.Komunikacija;
import rs.np.turagencija.kordinator.Kordinator;

/**
 *
 * @author KORISNIK
 */
public class IzmeniRezervacijuController {

    private final IzmeniRezervacijuForma irf;
    private Rezervacija rezervacija;

    public IzmeniRezervacijuController(IzmeniRezervacijuForma irf) {
        this.irf = irf;

        irf.setLocationRelativeTo(null);
        addActionListener();

    }

    public void otvoriFormu(Rezervacija rez) {

        rezervacija = rez;

        pripremiFormu();
        irf.setVisible(true);

    }

    private void pripremiFormu() {

        List<FakultativnaUsluga> usluge = Komunikacija.getInstance().ucitajUsluge();
        irf.getjComboBoxUsluge().removeAllItems();
        for (FakultativnaUsluga u : usluge) {
            irf.getjComboBoxUsluge().addItem(u);

        }

        List<StavkaRezervacije> stavke = Komunikacija.getInstance().ucitajStavke(rezervacija.getRezervacijaID());
        List<FakultativnaUsluga> odabraneUsluge = new ArrayList<>();

        for (StavkaRezervacije s : stavke) {
            odabraneUsluge.add(s.getUsluga());
        }

        ModelTabeleFakultativneUsluge mtfu = new ModelTabeleFakultativneUsluge(odabraneUsluge);
        irf.getjTableUsluge().setModel(mtfu);
    }

    private void addActionListener() {
        irf.dodajActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FakultativnaUsluga izabranaUsluga = (FakultativnaUsluga) irf.getjComboBoxUsluge().getSelectedItem();
                ModelTabeleFakultativneUsluge mtfa = (ModelTabeleFakultativneUsluge) irf.getjTableUsluge().getModel();
                List<FakultativnaUsluga> dodateUsluge = mtfa.getLista();
                System.out.println("Vec dodate usloge: " + dodateUsluge);
                System.out.println("Izabrana usluga: " + izabranaUsluga);
                if (!dodateUsluge.contains(izabranaUsluga)) {
                    mtfa.dodajUslugu(izabranaUsluga);
                } else {
                    JOptionPane.showMessageDialog(irf, "Izabrana usluga je vec dodata.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        irf.izbrisiUsluguActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = irf.getjTableUsluge().getSelectedRow();
                if (red != -1) {

                    ModelTabeleFakultativneUsluge mtfu = (ModelTabeleFakultativneUsluge) irf.getjTableUsluge().getModel();
                    FakultativnaUsluga izabranaUsluga = mtfu.getLista().get(red);
                    mtfu.izbrisiUslugu(izabranaUsluga);
                } else {
                    JOptionPane.showMessageDialog(irf, "Nije izabrana usluga.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        irf.sacuvajRezervacijuActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ModelTabeleFakultativneUsluge mtfu = (ModelTabeleFakultativneUsluge) irf.getjTableUsluge().getModel();
                List<FakultativnaUsluga> usluge = mtfu.getLista();
                List<StavkaRezervacije> noveStavke = new ArrayList<>();

                Double ukupnaCena = 0.0;
                ukupnaCena += rezervacija.getAranzman().getCena();
                for (FakultativnaUsluga u : usluge) {
                    ukupnaCena += u.getCena();
                }
                rezervacija.setUkupnaCena(ukupnaCena);

                int brojac = 1;
                for (FakultativnaUsluga u : usluge) {
                    StavkaRezervacije s = new StavkaRezervacije();
                    s.setUsluga(u);
                    s.setCena(u.getCena());
                    s.setRb(brojac++);
                    s.setRezervacija(rezervacija);
                    noveStavke.add(s);
                }

                rezervacija.setStavke(noveStavke);
                boolean izmenjeno = Komunikacija.getInstance().izmeniRezervaciju(rezervacija);

                try {

                    if (izmenjeno) {
                        JOptionPane.showMessageDialog(irf, "Uspesna izmena rezervacije.", "Uspeh.", JOptionPane.INFORMATION_MESSAGE);
                        Kordinator.getInst().osveziTabeluSaRezervacijama();
                        irf.dispose();
                    } else {
                        JOptionPane.showMessageDialog(irf, "Neuspesna izmena rezervacije.", "Greska.", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(irf, "Neuspesna izmena rezervacije.", "Greska.", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        irf.odustaniActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                irf.dispose();
            }
        });
    }

}
