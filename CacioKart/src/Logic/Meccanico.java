package Logic;

import Enums.Query;
import Objects.Kart;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Meccanico {
    private List<Map<String, Object>> result;
    private DBConnector db;
    private PHPResponseHandler responder;
    private String SELECT, DELETE, UPDATE, INSERT;
    private TableMaker maker;
    private String queryIndicator;

    public Meccanico() {

    }

    public void aggiornamentoManutenzione(String targa, String text, double prezzo, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        String idM;
        //kart = db.executeReturnQuery(SELECT);

        SELECT = Query.AGGIORNAMENTO_MANUTENZIONE_MAX_ID.getQuery();
        idM = db.executeReturnQuery(SELECT).get(0).get("max").toString();
        //System.out.println("Ecco l'id massimo delle manutenzioni: " + idM);
        idM = String.valueOf(Integer.parseInt(idM) + 1);

        INSERT = Query.AGGIORNAMENTO_MANUTENZIONE_TABELLA_MANUTENZIONE.getQuery(idM, text, prezzo, LocalDate.now(), targa);

        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryIndicator);

    }

    public void aggiuntaKart(String targa, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        DELETE = Query.INSERIMENTO_KART_MECCANICO.getQuery(targa);
        queryIndicator = db.executeUpdateQuery(DELETE);
        responder.sendResponse(clientSocket, queryIndicator);
    }

    public void aggiuntaBenzina(Kart k, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        UPDATE = Query.AGGIUNTA_BENZINA_MECCANICO.getQuery(k.getTarga());
        queryIndicator = db.executeUpdateQuery(UPDATE);
        responder.sendResponse(clientSocket, queryIndicator);
    }

    public void mostraKart(String query, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        result = db.executeReturnQuery(query);

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket,  maker.stringTableMaker(result, "targa", "cilindrata", "serbatoio"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void mostraKartManutenzione(String query, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        result = db.executeReturnQuery(query);

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result,"targa", "giorniDallaManutenzione"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void rimozioneKart(Kart k, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        DELETE = Query.RIMUOVI_KART_MECCANICO.getQuery(k.getTarga());
        queryIndicator = db.executeUpdateQuery(DELETE);
        responder.sendResponse(clientSocket, queryIndicator);
    }

}
