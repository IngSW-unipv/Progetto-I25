package Logic;

import Enums.Query;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Arbitro {
    private PHPResponseHandler responder;
    private DBConnector db;

    public Arbitro() {

    }

    /** Metodo dell'Arbitro per inserire penalità.
     * Ricevendo in ingresso il socio, la gara specifica e la penalità da aggiungere,
     * il metodo modifica il tempo di gara del socio nel db in modo da
     * riflettere il nuovo tempo con la penalità aggiunta.
     *
     * @param s Il socio a cui inserire la penalità
     * @param idGara La gara in cui inserire la penalità
     * @param penalita La quantità di tempo da sommare
     * @param clientSocket Il socket per mandare la risposta
     */
    public void inserimentoPenalita(Socio s, String idGara, LocalTime penalita, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        String UPDATE = Query.INSERIMENTO_PENALITA_ARBITRO.getQuery(penalita, idGara, s.getCf());

        String queryIndicator = db.executeUpdateQuery(UPDATE);
        responder.sendResponse(clientSocket, queryIndicator);
    }

    /** Metodo per mostrare all'Arbitro tutte le gare disputate su cui è possibile
     * inserire penalità.
     * Il metodo restituisce le gare senza duplicati.
     *
     * @param clientSocket Il socket di risposta
     */
    public void gareArbitro(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        String SELECT = Query.CLASSIFICA_ARBITRO.getQuery();
        List<Map<String, Object>> result;
        result = db.executeReturnQuery(SELECT);

        if (result != null) {
            TableMaker maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idGara"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

}
