/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagencija.operacija;

import rs.np.turagencija.repository.Repository;
import rs.np.turagencija.repository.db.DbRepository;
import rs.np.turagencija.repository.db.impl.DbRepositoryGeneric;

/**
 * Apstraktna klasa koja definise sablon za izvrsavanje svih sistemskih
 * operacija.
 * <p>
 * Svaka konkretna sistemska operacija nasledjuje ovu klasu i implementira
 * metode preduslovi i izvrsiOperaciju.
 * </p>
 * Proces izvrsenja obuhvata:
 * <ol>
 * <li>Proveru preduslova</li>
 * <li>Povezivanje sa bazom i pocetak transakcije</li>
 * <li>Izvrsavanje konkretne operacije</li>
 * <li>Potvrdu (commit) transakcije, ili ponistavanje (rollback) u slucaju
 * greske</li>
 * </ol>
 *
 * @author Andrija Panovic
 */
public abstract class ApstraktnaGenerickaOperacija {

    /**
     * Repozitorijum putem koga se pristupa bazi podataka.
     */
    protected final Repository broker;

    /**
     * Kreira novu instancu apstraktne operacije i inicijalizuje brokerski
     * objekat.
     */
    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }

    /**
     * Metoda sablona koja definise tok izvrsavanja svake sistemske operacije.
     *
     * Redosled koraka:
     * <ol>
     * <li>Proverava preduslove pomocu
     * {@link #preduslovi(java.lang.Object)}</li>
     * <li>Povezuje se na bazu (otvara transakciju)</li>
     * <li>Poziva konkretnu implementaciju
     * {@link #izvrsiOperaciju(java.lang.Object, java.lang.String)}</li>
     * <li>Potvrdjuje transakciju ako je sve uspesno, ili je ponistava ako dodje
     * do greske</li>
     * </ol>
     *
     * @param objekat objekat nad kojim se vrsi sistemksa operacija
     * @param kljuc opcioni string koji moze sadrzati dodatne uslove (npr. WHERE
     * deo SQL upita, ili JOIN drugih tabela)
     * @throws Exception ako dodje do greske u preduslovima ili tokom izvrsenja
     * operacije
     */
    public final void izvrsi(Object objekat, String kljuc) throws Exception {
        try {
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat, kljuc);
            potvrdiTransakciju();

        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        }
    }

    /**
     * Proverava da li su ispunjeni svi preduslovi za izvrsenje sistemske
     * operacije.
     *
     * @param param Jedan on domenskih objekata nad kojima se izvrsavaju
     * sistemske operacije
     * @throws Exception ako preduslovi nisu ispunjeni
     */
    protected abstract void preduslovi(Object param) throws Exception;

    /**
     * Definise konkretnu logiku sistemske operacije.
     *
     * @param param ulazni objekat operacije
     * @param kljuc dodatni string parametar koji predstavlja uslov koji se
     * dodaje na sql upit
     * @throws Exception ako dodje do greske prilikom izvrsavanja
     */
    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;

    /**
     * Povezuje se sa bazom i zapocinje transakciju.
     *
     * @throws Exception ako nije moguce uspostaviti konekciju
     */
    private void zapocniTransakciju() throws Exception {
        ((DbRepository) broker).connect();
    }

    /**
     * Potvrdjuje sve promene nad bazom (commit).
     *
     * @throws Exception ako dodje do greske pri potvrdi transakcije
     */
    private void potvrdiTransakciju() throws Exception {
        ((DbRepository) broker).commit();
    }

    /**
     * Ponistava sve promene u bazi (rollback) u slucaju greske.
     *
     * @throws Exception ako dodje do greske pri ponistavanju transakcije
     */
    private void ponistiTransakciju() throws Exception {
        ((DbRepository) broker).rollback();
    }

    /**
     * Zatvara konekciju sa bazom podataka.
     *
     * @throws Exception ako dodje do greske pri zatvaranju konekcije
     */
    private void ugasiKonekciju() throws Exception {
        ((DbRepository) broker).disconnect();
    }

}
