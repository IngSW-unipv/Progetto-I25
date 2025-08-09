package DAO.interfacce;

import java.util.List;
import java.util.Map;
import Objects.Kart;

/**
 * Interfaccia per l'accesso ai dati relativi ai kart.
 */
public interface KartDAOInterface {

    /**
     * Restituisce la lista di tutti i kart.
     * @return lista di oggetti Kart presenti nel database
     */
    List<Kart> getAllKart();

    /**
     * Restituisce solo i kart disponibili per aggiunta.
     * @return lista di kart disponibili
     */
    List<Kart> getAllKartDisponibiliPerAggiunta();

    /**
     * Inserisce un nuovo kart nel database.
     * @param k il kart da inserire
     * @return true se l'inserimento ha avuto successo, false altrimenti
     */
    boolean insertKart(Kart k);

    /**
     * Elimina un kart dal database.
     * @param k il kart da eliminare
     * @return true se la cancellazione ha avuto successo, false altrimenti
     */
    boolean deleteKart(Kart k);

    /**
     * Esegue il pieno (serbatoio = 20) su un kart.
     * @param k il kart su cui effettuare il pieno
     * @return true se l'operazione ha avuto successo, false altrimenti
     */
    boolean refillKart(Kart k);

    /**
     * Restituisce la lista dei kart in manutenzione.
     * @return lista di mappe contenenti i dati dei kart in manutenzione
     */
    List<Map<String, Object>> getKartManutenzione();
}
