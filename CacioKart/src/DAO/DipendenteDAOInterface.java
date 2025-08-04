package DAO;

import Objects.Dipendente;
import java.util.List;

/**
 * Interfaccia per la gestione dei dipendenti.
 */
public interface DipendenteDAOInterface {

    /**
     * Inserisce un nuovo dipendente nel database.
     * @param d oggetto Dipendente da inserire
     */
    void inserisciDipendente(Dipendente d);

    /**
     * Rimuove un dipendente tramite codice fiscale.
     * @param cf codice fiscale del dipendente da rimuovere
     */
    void rimuoviDipendente(String cf);

    /**
     * Mostra la lista dei dipendenti presenti.
     * @return lista di oggetti Dipendente
     */
    List<Dipendente> mostraDipendenti();
}
