package DAO.interfacce;

import java.util.List;
import java.util.Map;

/**
 * Interfaccia per l'accesso ai dati e le operazioni dell'organizzatore.
 */
public interface OrganizzatoreDAOInterface {

    /**
     * Mostra i soci che non appartengono a nessun team.
     * @return lista di mappe con i dati dei soci.
     */
    List<Map<String, Object>> mostraSociInserimentoCampionato();

    /**
     * Aggiunge un nuovo team e associa i soci specificati.
     * @param nomeTeam nome del team
     * @param colore colore del team
     * @param soci lista di username dei soci
     */
    void aggiungiTeam(String nomeTeam, String colore, List<String> soci);

    /**
     * Mostra la lista degli identificativi dei campionati disponibili.
     * @return lista degli idCampionato
     */
    List<String> mostraCampionati();

    /**
     * Mostra la lista delle gare disponibili per l'inserimento.
     * @return lista di mappe con i dati delle gare.
     */
    List<Map<String, Object>> mostraGareInserimento();

    /**
     * Aggiunge una gara a un campionato.
     * @param idGara identificativo della gara
     * @param idCampionato identificativo del campionato
     * @return risultato dell'operazione (es. "OK" o messaggio di errore)
     */
    String aggiungiGaraACampionato(String idGara, String idCampionato);

    /**
     * Mostra tutte le prenotazioni gestite dall'organizzatore.
     * @return lista di mappe con i dati delle prenotazioni.
     */
    List<Map<String, Object>> mostraPrenotazioniOrganizzatore();

    /**
     * Mostra i soci che si possono aggiungere a una prenotazione.
     * @return lista di mappe con i dati dei soci.
     */
    List<Map<String, Object>> mostraSociAggiuntaPrenotazione();

    /**
     * Inserisce un socio in una prenotazione di gara.
     * @param idP identificativo della prenotazione
     * @param socio username del socio
     * @param data data della prenotazione
     * @return risultato dell'operazione (es. "OK" o messaggio di errore)
     */
    String inserisciSocioInPrenotazione(String idP, String socio, String data);
}
