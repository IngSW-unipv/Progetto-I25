package Command;

import Logic.Organizzatore;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class SelectGareCampCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        try {
            Organizzatore organizzatore = new Organizzatore();
            organizzatore.mostraGareInserimento(clientSocket);
        } catch (Exception e) {
            PHPResponseHandler responder = new PHPResponseHandler();
            responder.sendResponse(clientSocket, "Errore durante la selezione delle gare: " + e.getMessage());
        }
    }
}
