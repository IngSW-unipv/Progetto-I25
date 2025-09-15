package DAO.interfacce;

import Objects.Kart;

/**
 * Interfaccia per l'accesso e la gestione delle manutenzioni sui kart.
 */
public interface ManutenzioneDAOInterface {

    /**
     * Inserisce una nuova manutenzione per un kart.
     * @param kart kart oggetto per cui registrare la manutenzione
     * @param tipoInt tipo di intervento effettuato
     * @param costo costo della manutenzione
     * @return true se l'inserimento ha avuto successo, false altrimenti
     */
    boolean insertManutenzione(Kart kart, String tipoInt, double costo);
}
