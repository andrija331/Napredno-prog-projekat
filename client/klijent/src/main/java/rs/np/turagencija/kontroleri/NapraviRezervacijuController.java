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
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import rs.np.turagencija.domen.Aranzman;
import rs.np.turagencija.domen.FakultativnaUsluga;
import rs.np.turagencija.domen.Klijent;
import rs.np.turagencija.domen.Rezervacija;
import rs.np.turagencija.domen.StavkaRezervacije;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.forme.NapraviRezervacijuForma;
import rs.np.turagencija.forme.model.ModelTabeleAranzman;
import rs.np.turagencija.forme.model.ModelTabeleFakultativneUsluge;
import rs.np.turagencija.forme.model.ModelTabeleKlijent;
import rs.np.turagencija.komunikacija.Komunikacija;
import rs.np.turagencija.kordinator.Kordinator;

/**
 *
 * @author KORISNIK
 */
public class NapraviRezervacijuController {

    private final NapraviRezervacijuForma nrf;

    public NapraviRezervacijuController(NapraviRezervacijuForma nrf) {
        this.nrf = nrf;
        nrf.setLocationRelativeTo(null);

        addActionListener();

    }

    public void otvoriFormu() {
        pripremiFormu();
        nrf.setVisible(true);
    }

    private void pripremiFormu() {

        List<Aranzman> aranzmani = Komunikacija.getInstance().ucitajAranzmane();
        ModelTabeleAranzman mta = new ModelTabeleAranzman(aranzmani);
        nrf.getJTableAranzmani().setModel(mta);

        List<Klijent> klijenti = Komunikacija.getInstance().ucitajKlijente();
        ModelTabeleKlijent mtk = new ModelTabeleKlijent(klijenti);
        nrf.getjTableKlijenti().setModel(mtk);

        Zaposleni ulogovani = Kordinator.getInst().getUlogovani();
        String imePrezime = "Ulogovani: " + ulogovani.getIme() + " " + ulogovani.getPrezime();
        nrf.getjLabelUlogovani().setText(imePrezime);

        List<FakultativnaUsluga> prazna = new ArrayList<>();
        ModelTabeleFakultativneUsluge mtfu = new ModelTabeleFakultativneUsluge(prazna);
        nrf.getjTableUsluge().setModel(mtfu);

        List<FakultativnaUsluga> usluge = Komunikacija.getInstance().ucitajUsluge();
        nrf.getjComboBoxUsluge().removeAllItems();
        for (FakultativnaUsluga u : usluge) {
            nrf.getjComboBoxUsluge().addItem(u);

        }
    }

    private void addActionListener() {
        nrf.dodajUsluguActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FakultativnaUsluga izabranaUsluga = (FakultativnaUsluga) nrf.getjComboBoxUsluge().getSelectedItem();
                ModelTabeleFakultativneUsluge mtfa = (ModelTabeleFakultativneUsluge) nrf.getjTableUsluge().getModel();
                List<FakultativnaUsluga> dodateUsluge = mtfa.getLista();
                if (!dodateUsluge.contains(izabranaUsluga)) {
                    mtfa.dodajUslugu(izabranaUsluga);
                } else {
                    JOptionPane.showMessageDialog(nrf, "Izabrana usluga je vec dodata.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        nrf.izbrisiUsluguActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = nrf.getjTableUsluge().getSelectedRow();
                if (red != -1) {

                    ModelTabeleFakultativneUsluge mtfu = (ModelTabeleFakultativneUsluge) nrf.getjTableUsluge().getModel();
                    FakultativnaUsluga izabranaUsluga = mtfu.getLista().get(red);
                    mtfu.izbrisiUslugu(izabranaUsluga);
                } else {
                    JOptionPane.showMessageDialog(nrf, "Nije izabrana usluga.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        nrf.rezervisiActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selektovanAranzman = nrf.getJTableAranzmani().getSelectedRow();
                if (selektovanAranzman == -1) {
                    JOptionPane.showMessageDialog(nrf, "Neuspesno dodavanje rezervacije. Nije izabran aranzman.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleAranzman mta = (ModelTabeleAranzman) nrf.getJTableAranzmani().getModel();
                Aranzman aranzman = mta.getLista().get(selektovanAranzman);

                int selektovanKlijent = nrf.getjTableKlijenti().getSelectedRow();
                if (selektovanKlijent == -1) {
                    JOptionPane.showMessageDialog(nrf, "Neuspesno dodavanje rezervacije. Nije izabran klijent.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleKlijent mtk = (ModelTabeleKlijent) nrf.getjTableKlijenti().getModel();
                Klijent klijent = mtk.getLista().get(selektovanKlijent);

                Zaposleni zaposleni = Kordinator.getInst().getUlogovani();

                ModelTabeleFakultativneUsluge mtfu = (ModelTabeleFakultativneUsluge) nrf.getjTableUsluge().getModel();
                List<FakultativnaUsluga> usluge = mtfu.getLista();
                List<StavkaRezervacije> stavke = new ArrayList<>();
                int brojac = 1;
                for (FakultativnaUsluga u : usluge) {
                    StavkaRezervacije s = new StavkaRezervacije();
                    s.setUsluga(u);
                    s.setCena(u.getCena());
                    s.setRb(brojac++);
                    stavke.add(s);
                }

                Double ukupnaCena = 0.0;
                ukupnaCena += aranzman.getCena();
                for (FakultativnaUsluga u : usluge) {
                    ukupnaCena += u.getCena();

                }

                Date datum = new Date();
                Rezervacija novaRezervacija = new Rezervacija(-1, zaposleni, klijent, aranzman, stavke, datum, ukupnaCena);

                for (Rezervacija r : Kordinator.getInst().getSveRezervacije()) {
                    if (r.equals(novaRezervacija)) {
                        JOptionPane.showMessageDialog(nrf, "Neuspesno dodavanje rezervacije. Izabrani klijent je vec rezervisao izabrani aranzman.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                novaRezervacija = Komunikacija.getInstance().dodajRezervaciju(novaRezervacija);
                try {

                    if (novaRezervacija != null && novaRezervacija.getRezervacijaID() != -1) {
                        JOptionPane.showMessageDialog(nrf, "Uspesno dodavanje rezervacije.", "Uspeh.", JOptionPane.INFORMATION_MESSAGE);
                        Kordinator.getInst().osveziTabeluSaRezervacijama();
                        nrf.dispose();
                    } else {
                        JOptionPane.showMessageDialog(nrf, "Neuspenso dodavanje rezervacije.", "Greska.", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(nrf, "Neuspenso dodavanje rezervacije.", "Greska.", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        nrf.odustaniActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nrf.dispose();
            }
        });

    }

}
