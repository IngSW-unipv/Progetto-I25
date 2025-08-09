package DAO.interfacce;

import java.util.List;
import java.util.Map;

/**
 * Interfaccia per la gestione degli articoli nella concessionaria.
 */
public interface ConcessionariaDAOInterface {

    /**
     * Restituisce il prossimo idProdotto disponibile (MAX + 1).
     * @return prossimo idProdotto come stringa
     */
    String getNextProductId();

    /**
     * Inserisce un nuovo articolo (kart o pezzo) nella tabella concessionaria.
     * @param idProdotto identificativo del prodotto
     * @param tipol tipo di prodotto
     * @param quantita quantità disponibile
     * @param prezzo prezzo del prodotto
     * @return true se l'inserimento ha avuto successo, false altrimenti
     */
    boolean insertConcessionariaItem(String idProdotto, String tipol, int quantita, int prezzo);

    /**
     * Restituisce tutti i pezzi presenti nella concessionaria (esclusi i kart).
     * @return lista di mappe contenenti i dati dei pezzi
     */
    List<Map<String, Object>> getPezzi();

    /**
     * Aggiunge una quantità di pezzi ad un idProdotto già presente.
     * @param idProdotto identificativo del prodotto
     * @param quantitaDaAggiungere quantità da aggiungere
     * @return true se l'operazione ha avuto successo, false altrimenti
     */
    boolean aggiungiPezzi(String idProdotto, int quantitaDaAggiungere);

    /**
     * Decrementa la quantità di un articolo in concessionaria.
     * @param tipol tipo dell'articolo
     * @return true se l'operazione ha avuto successo, false altrimenti
     */
    boolean decrementaQuantitaConcessionaria(String tipol);
}
