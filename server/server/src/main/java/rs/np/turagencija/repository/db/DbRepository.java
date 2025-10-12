/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.np.turagencija.repository.db;

import rs.np.turagencija.repository.Repository;

/**
 *
 * @author KORISNIK
 */
public interface DbRepository<T> extends Repository<T> {

    default public void connect() throws Exception {
        DbConnectionFactory.getInst().getConnection();
    }

    default public void disconnect() throws Exception {
        DbConnectionFactory.getInst().getConnection().close();
    }

    default public void commit() throws Exception {
        DbConnectionFactory.getInst().getConnection().commit();
    }

    default public void rollback() throws Exception {
        DbConnectionFactory.getInst().getConnection().rollback();
    }

}
