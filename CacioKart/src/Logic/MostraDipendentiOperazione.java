package Logic;

import Enums.Query;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class MostraDipendentiOperazione implements OperazioneProprietario {
    private final DBConnector db;
    private final PHPResponseHandler responder;

    public MostraDipendentiOperazione(DBConnector db, PHPResponseHandler responder) {
        this.db = db;
        this.responder = responder;
    }

    @Override
    public void esegui(Socket clientSocket) {
        String SELECT = Query.MOSTRA_DIPENDENTI_PROPRIETARIO.getQuery();
        List<Map<String, Object>> dipendenti = db.executeReturnQuery(SELECT);
        if (dipendenti != null) {
            TableMaker maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(dipendenti, "dip", "nome", "cognome"));
        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }
}
