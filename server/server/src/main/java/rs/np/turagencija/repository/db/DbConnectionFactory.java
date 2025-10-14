/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.repository.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.np.turagencija.konfiguracija.Konfiguracija;

/**
 *
 * @author KORISNIK
 */
public class DbConnectionFactory {

    private static DbConnectionFactory instance;
    private Connection connection;

    private DbConnectionFactory() {
        try {
            if (connection == null || connection.isClosed()) {
                // String url = Konfiguracija.getInstance().getProperty("url");
                String url2 = "jdbc:mysql://localhost:3306/tur_agencija";
                String dbName = System.getProperty("DB_NAME", "turisticka_agencija");
                String url = "jdbc:mysql://localhost:3306/" + dbName;
                //String username = Konfiguracija.getInstance().getProperty("username");
                //String password = Konfiguracija.getInstance().getProperty("password");
                connection = DriverManager.getConnection(url, "root", "");
                connection.setAutoCommit(false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DbConnectionFactory getInst() {
        if (instance == null) {
            instance = new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
