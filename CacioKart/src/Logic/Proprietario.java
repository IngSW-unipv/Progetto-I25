package Logic;

import Enums.Query;
import Objects.Dipendente;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Proprietario {
    private DBConnector db;
    private PHPResponseHandler responder;
    private String INSERT, SELECT, DELETE;
    private String queryIndicator;
    private String[] INSERT_ITERATOR;
    private List<Map<String, Object>> result;

    public Proprietario() {

    }

    /** Metodo per mostrare i dipendenti.
     *
     * @param clientSocket Socket per inviare la risposta
     */
    public void mostraDipendenti(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = Query.MOSTRA_DIPENDENTI_PROPRIETARIO.getQuery();
        List<Map<String, Object>> dipendenti = db.executeReturnQuery(SELECT);

        if (dipendenti != null) {
            TableMaker maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(dipendenti, "dip", "nome", "cognome"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    /** Metodo per aggiungere i dipendenti.
     * Preso l'oggetto Dipendente in ingresso, si crea
     * la query da eseguire per effettuare la modifica del db.
     *
     * @param nuovoDip     Il dipendente da aggiungere
     * @param clientSocket Socket per inviare la risposta
     */
    public void aggiuntaDipendente(Dipendente nuovoDip, Socket clientSocket) {
        db = new DBConnector();
        //Gestire i diversi ruoli
        responder = new PHPResponseHandler();
        INSERT = Query.AGGIUNTA_DIPENDENTE_PROPRIETARIO.getQuery(
                nuovoDip.getCf(),
                nuovoDip.getNome(),
                nuovoDip.getCognome(),
                nuovoDip.getMail(),
                nuovoDip.getPassword(),
                nuovoDip.getDataNascita(),
                nuovoDip.getRuolo(),
                nuovoDip.getOreL(),
                nuovoDip.getStipendio());

        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryIndicator);
    }

    /**
     * Metodo per rimuovere i dipendenti.
     * Identico al metodo presente in Objects.Kart per rimuovere i kart.
     *
     * @param d            Il dipendente da rimuovere
     * @param clientSocket Socket per inviare la risposta
     */
    public void rimozioneDipendenti(Dipendente d, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        DELETE = Query.RIMOZIONE_DIPENDENTE_PROPRIETARIO.getQuery(d.getCf());
        queryIndicator = db.executeUpdateQuery(DELETE);
        responder.sendResponse(clientSocket, queryIndicator);
    }

    public void bilancio(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT_ITERATOR = new String[3];

        // Query per le entrate
        INSERT_ITERATOR[0] = Query.BILANCIO_ENTRATE_PROPRIETARIO.getQuery();

        // Query per le uscite
        INSERT_ITERATOR[1] = Query.BILANCIO_USCITE_PROPRIETARIO.getQuery();

        // Query per il saldo totale
        INSERT_ITERATOR[2] = Query.BILANCIO_SALDO_TOTALE.getQuery();

        for (String saldo : INSERT_ITERATOR) {
            result = db.executeReturnQuery(saldo);
            if (result.isEmpty()) {
                responder.sendResponse(clientSocket, "0");
                return;
            }
        }

        responder.sendResponse(clientSocket,
                result.toString()
                        .trim()
                        .replaceAll("[{}\\[\\],]", "")
                        .replaceAll("ENTRATE=", "")
                        .replaceAll("USCITE=", "")
                        .replaceAll("SALDO=", "")
                        .replaceAll("^\\s+|\\s+$", "")
                        .replaceAll("\\s+", " "));
    }
}

