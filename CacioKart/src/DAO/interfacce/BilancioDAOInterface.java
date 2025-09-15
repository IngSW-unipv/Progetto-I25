package DAO.interfacce;

/**
 * Interfaccia per la gestione del bilancio e l'accesso ai dati finanziari.
 */
public interface BilancioDAOInterface {

    /**
     * Restituisce il totale delle entrate.
     * @return valore delle entrate
     */
    double getEntrate();

    /**
     * Restituisce il totale delle uscite.
     * @return valore delle uscite
     */
    double getUscite();

    /**
     * Restituisce il saldo totale.
     * @return valore del saldo totale
     */
    double getSaldo();
}
