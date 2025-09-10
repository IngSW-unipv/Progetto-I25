package Logic;

import Enums.Query;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

//Inserire in un DAO

public class Organizzatore {
    private DBConnector db;
    private PHPResponseHandler responder;
    private String SELECT;
    private List<Map<String, Object>> result;
    private TableMaker maker;

    public Organizzatore() {

    }

    /** Metodo per mostrare al client tutte le
     * gare secche ancora non appartenenti a un campionato.
     *
     * @param clientSocket Il socket di risposta
     */
    public void mostraGareInserimento(Socket clientSocket) {
        db = DBConnector.getInstance();
        responder = new PHPResponseHandler();
        SELECT = Query.MOSTRA_GARE_INSERIMENTO.getQuery();

        result = db.executeReturnQuery(SELECT);

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idGara", "ora"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

}

