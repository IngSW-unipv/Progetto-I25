package DAO.interfacce;

import java.util.List;
import java.util.Map;

/**
 * Interfaccia per la gestione e l'accesso ai dati delle classifiche e penalità.
 */
public interface ClassificaDAOInterface {

    /**
     * Restituisce la lista degli identificativi delle gare svolte.
     * @return lista degli idGara
     */
    List<String> mostraGareSvolte();

    /**
     * Inserisce una penalità aggiornando il tempo totale di un socio in una gara.
     * @param socio username del socio
     * @param idGara identificativo della gara
     * @param tempo tempo da aggiungere come penalità
     */
    void inserisciPenalita(String socio, String idGara, String tempo);

    /**
     * Mostra la classifica e le penalità di una gara specifica.
     * @param idGara identificativo della gara
     * @return lista di mappe con i dati della classifica e penalità
     */
    List<Map<String, Object>> mostraClassificaPenalita(String idGara);

    /**
     * Restituisce la classifica generale di tutti gli utenti.
     * @return lista di mappe con i dati della classifica generale
     */
    List<Map<String, Object>> getClassificaGenerale();

    /**
     * Restituisce la classifica relativa a uno specifico utente.
     * @param nome nome utente
     * @return lista di mappe con i dati della classifica per l'utente specificato
     */
    List<Map<String, Object>> getClassificaUtente(String nome);
}
