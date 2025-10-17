/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.main;

import rs.np.turagencija.controller.Controller;
import rs.np.turagencija.forme.ServerskaForma;
import rs.np.turagencija.konfiguracija.Konfiguracija;

/**
 *
 * @author KORISNIK
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ServerskaForma sf = new ServerskaForma();
        sf.setLocationRelativeTo(null);
        sf.setVisible(true);

        Controller.getInst().ucitajRezervacijeUJsonFajl();

    }
}
