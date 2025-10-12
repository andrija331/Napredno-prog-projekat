/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.kontroleri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import rs.np.turagencija.domen.Zaposleni;
import rs.np.turagencija.forme.LoginForma;
import rs.np.turagencija.komunikacija.Komunikacija;
import rs.np.turagencija.kordinator.Kordinator;

/**
 *
 * @author KORISNIK
 */
public class LoginController {

    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        lf.setLocationRelativeTo(null);
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginActListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String username = lf.getjTextFieldUsername().getText().trim();
                String password = String.valueOf(lf.getjPasswordField().getPassword());
                Zaposleni z = new Zaposleni();
                z.setUsername(username);
                z.setPassword(password);
                // Komunikacija.getInstance().konekcija();
                Zaposleni ulogovani = Komunikacija.getInstance().login(z);
                if (ulogovani == null) {
                    JOptionPane.showMessageDialog(lf, "Prijava nije uspela. Proverite korisnicko ime i lozinku. ", "GRESKA", JOptionPane.ERROR_MESSAGE);

                } else {

                    Kordinator.getInst().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Prijava na sistem je uspesna.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInst().otvoriGlavnuFormu();
                    lf.dispose();

                }

            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }

}
