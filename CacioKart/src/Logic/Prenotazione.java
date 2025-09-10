package Logic;

import Strategy.VisualizzazionePrenotazioniStrategy;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

//Far diventare un DAO

public class Prenotazione  {

    public void mostraPrenotazioni(VisualizzazionePrenotazioniStrategy strategy, Socket clientSocket) {
        PHPResponseHandler responder = new PHPResponseHandler();
        DBConnector db = DBConnector.getInstance();
        TableMaker maker = new TableMaker();

        List<Map<String, Object>> result = strategy.eseguiQuery(db);

        if (result != null && !result.isEmpty()) {
            String risposta = maker.stringTableMaker(result, strategy.getColonneDaMostrare());
            responder.sendResponse(clientSocket, risposta);
        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }
}

