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

    public void classificaArbitro( Socket clientSocket) {
        responder = new PHPResponseHandler();
        SELECT = Query.CLASSIFICA_ARBITRO.getQuery();
        result = getClassifica(SELECT);

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idGara"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

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

    public void classificaPenalità(String idGara, Socket clientSocket) {
        // Inizializza il responder.
        responder = new PHPResponseHandler();

        // Esecuzione della query
        result = getClassifica(Query.MOSTRA_CLASSIFICA_PENALITA.getQuery(idGara));

        if (result != null) {

            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idGara", "socio", "targa", "bGiro", "tempTot"));
        } else {
            // Se la query non ha restituito risultati
            responder.sendResponse(clientSocket, "end");
        }
    }

    public List<Map<String, Object>> getClassifica(String SELECT) {
        db = new DBConnector();
        return db.executeReturnQuery(SELECT);

    }
}
