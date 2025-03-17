package Logic;

import Enums.Query;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalTime;

public class Arbitro {

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
        DBConnector db = new DBConnector();
        PHPResponseHandler responder = new PHPResponseHandler();
        String UPDATE = Query.INSERIMENTO_PENALITA_ARBITRO.getQuery(penalita, idGara, s.getCf());

        String queryIndicator = db.executeUpdateQuery(UPDATE);
        responder.sendResponse(clientSocket, queryIndicator);
    }
}
