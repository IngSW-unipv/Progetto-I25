package Logic;

import Enums.Query;
import WebTalker.PHPResponseHandler;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class BilancioOperazione implements OperazioneProprietario {

    private final DBConnector db;
    private final PHPResponseHandler responder;

    public BilancioOperazione(DBConnector db, PHPResponseHandler responder) {
        this.db = db;
        this.responder = responder;
    }

    @Override
    public void esegui(Socket clientSocket) {
        String[] queries = {
                Query.BILANCIO_ENTRATE_PROPRIETARIO.getQuery(),
                Query.BILANCIO_USCITE_PROPRIETARIO.getQuery(),
                Query.BILANCIO_SALDO_TOTALE.getQuery()
        };

        StringBuilder response = new StringBuilder();
        for (String query : queries) {
            List<Map<String, Object>> result = db.executeReturnQuery(query);
            if (result == null || result.isEmpty()) {
                responder.sendResponse(clientSocket, "0");
                return;
            }
            // ---- CORREZIONE SICURA ----
            Object val = result.get(0).values().iterator().next();
            response.append(val == null ? "" : val.toString()).append(" ");
        }

        responder.sendResponse(clientSocket, response.toString().trim());
    }
}
