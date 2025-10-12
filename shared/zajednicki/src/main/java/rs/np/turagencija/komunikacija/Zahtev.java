/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.komunikacija;

import java.io.Serializable;

/**
 *
 * @author KORISNIK
 */
public class Zahtev implements Serializable {

    private Operacija operacija;
    private Object param;

    public Zahtev() {
    }

    public Zahtev(Operacija operacija, Object param) {
        this.operacija = operacija;
        this.param = param;
    }

    public Operacija getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

}
