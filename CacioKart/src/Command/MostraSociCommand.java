package Command;

import Enums.Query;
import Logic.Organizzatore;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class MostraSociCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        try {
            Organizzatore o = new Organizzatore();
            String query = Query.SELEZIONA_SOCIO_AGGIUNTA_PRENOTAZIONE.getQuery();
            o.mostraSociInserimento(query, clientSocket);
        } catch (Exception e) {
            new PHPResponseHandler().sendResponse(clientSocket, "Errore nella selezione soci: " + e.getMessage());
        }
    }
}
