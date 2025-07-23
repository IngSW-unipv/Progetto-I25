package Strategy;

import Enums.Query;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;

public class PrenotazioneSeccaStrategy implements PrenotazioneStrategy {
    @Override
    public int eseguiPrenotazione(String idPrenotazione, String cf, LocalDate dataGara, LocalTime fasciaOraria,
                                  LocalDate dataO, DBConnector db, PHPResponseHandler responder, Socket clientSocket) {
        int costo = 20;
        String insert = Query.PRENOTAZIONE_GENERICA_INSERIMENTO.getQuery(idPrenotazione, dataGara, fasciaOraria, "secca", costo);
        String result = db.executeUpdateQuery(insert);
        responder.sendResponse(clientSocket, result);
        if(result.equals("null"))
        return 0;
        else
        return 1;
    }
}
