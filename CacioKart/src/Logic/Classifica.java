package Logic;

import Enums.Query;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Classifica {
    private DBConnector db;
    private PHPResponseHandler responder;
    private List<Map<String, Object>> result;
    private String SELECT;
    private TableMaker maker;

    public Classifica() {
    }



    /** Metodo per generare la classifica disponibile a tutti
     * i visitatori del sito.
     * Il metodo restituisce i 10 migliori giri in assoluto di
     * tutti gli utenti.
     *
     * @param clientSocket Il socket di risposta
     */
    public void classificaCompleta(Socket clientSocket) {
        // Inizializza il responder.
        responder = new PHPResponseHandler();
        SELECT = Query.CLASSIFICA_GENERALE.getQuery();
        // Esecuzione della query
        result = getClassifica(SELECT);

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idGara", "nome", "cognome", "targa", "bGiro", "tempTot"));
        } else {
            // Se la query non ha restituito risultati
            responder.sendResponse(clientSocket, "end");
        }
    }

    /** Metodo per generare la classifica delle ultime 10 gare
     * effettuate da uno specifico utente.
     *
     * @param s Il socio di cui vogliamo trovare le ultime 10 gare
     * @param clientSocket Il socket a cui mandare la risposta
     */
    public void classificaUtente(Socio s, Socket clientSocket) {
        responder = new PHPResponseHandler();
        // Esegui la query
        SELECT = Query.CLASSIFICA_UTENTE.getQuery(s.getCf());
        result = getClassifica(SELECT);

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idGara", "targa", "bGiro", "tempTot"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    /** Metodo per mostrare una classifica specifica all'arbitro
     * in modo da poter inserire penalità.
     *
     * @param idGara
     * @param clientSocket Il socket di risposta
     */
    public void classificaPenalita(String idGara, Socket clientSocket) {
        // Inizializza il responder.
        responder = new PHPResponseHandler();

        // Esecuzione della query
        result = getClassifica(Query.MOSTRA_CLASSIFICA_PENALITA.getQuery(idGara));

        if (result != null) {
            // Creo la stringa da rispondere a PHP
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idGara", "socio", "targa", "bGiro", "tempTot"));

        } else {
            // Se la query non ha restituito risultati
            responder.sendResponse(clientSocket, "end");

        }
    }

    /** Metodo per generare una classifica qualsiasi data una query.
     * È utilizzabile solo all'interno di questa classe, in quanto
     * tutti i metodi generano classiche, ma i dati da mostrare e
     * la query richiesta cambiano, creando la necessità di avere un metodo in comune
     *
     * @param SELECT La query da eseguire
     * @return La List<Map<String, Object>> che contiene la classifica richiesta
     */
    private List<Map<String, Object>> getClassifica(String SELECT) {
        db = new DBConnector();
        return db.executeReturnQuery(SELECT);

    }
}
