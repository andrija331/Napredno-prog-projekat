/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.repository.db.impl;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import rs.np.turagencija.domen.ApstraktniDomenskiObjekat;
import rs.np.turagencija.repository.db.DbConnectionFactory;
import rs.np.turagencija.repository.db.DbRepository;

/**
 *
 * @author KORISNIK
 */
public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if (uslov != null) {
            upit += uslov;
        }
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInst().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);
        rs.close();
        st.close();

        return lista;
    }

    @Override
    public int add(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje() + " ) VALUES ( "
                + param.vratiVrednostiZaUbacivanje() + " )";
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInst().getConnection().createStatement();
        st.executeUpdate(upit, st.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        int id = -1;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        st.close();
        return id;
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostZaIzmenu() + " WHERE "
                + param.vratiPrimarniKljuc();
        Statement st = DbConnectionFactory.getInst().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();

        // String upit = "UPDATE "+param.vratiNazivTabele()+" SET "+ 
        // param.vratiVrednostiZaIzmenu() + " WHERE "+param.vratiPrimarniKljuc();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();

        Statement st = DbConnectionFactory.getInst().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {

        return null;

    }

}
