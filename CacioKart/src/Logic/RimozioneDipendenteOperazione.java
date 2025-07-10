package Logic;

import Enums.Query;
import Objects.Dipendente;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class RimozioneDipendenteOperazione implements OperazioneProprietario {
    private final DBConnector db;
    private final PHPResponseHandler responder;
    private final Dipendente dipendente;

    public RimozioneDipendenteOperazione(DBConnector db, PHPResponseHandler responder, Dipendente dipendente) {
        this.db = db;
        this.responder = responder;
        this.dipendente = dipendente;
    }

    @Override
    public void esegui(Socket clientSocket) {
        String deleteQuery = Query.RIMOZIONE_DIPENDENTE_PROPRIETARIO.getQuery(dipendente.getCf());
        System.out.println("[DEBUG] DELETE QUERY: " + deleteQuery);
        String risultato = db.executeUpdateQuery(deleteQuery);
        System.out.println("[DEBUG] executeUpdateQuery returned: " + risultato);
        responder.sendResponse(clientSocket, risultato);
    }
}
