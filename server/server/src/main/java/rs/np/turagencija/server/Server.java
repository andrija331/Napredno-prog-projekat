/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.np.turagencija.controller.Controller;

/**
 *
 * @author KORISNIK
 */
public class Server extends Thread {

    boolean kraj = false;
    ServerSocket serverskiSoket;

    public Server() {

    }

    @Override
    public void run() {
        System.out.println("Server je pokrenut.");
        try {
            serverskiSoket = new ServerSocket(9000);
            while (!kraj) {
                Socket s = serverskiSoket.accept();
                System.out.println("Klijent je povezan");

                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                Controller.getInst().getKlijenti().add(okz);
                okz.start();

            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zaustaviServer() {
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva k : Controller.getInst().getKlijenti()) {
                k.prekini();

            }

            serverskiSoket.close();
            interrupt();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
