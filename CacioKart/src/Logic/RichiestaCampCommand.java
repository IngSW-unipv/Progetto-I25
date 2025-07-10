package Logic;

import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class RichiestaCampCommand implements  RequestCommand{

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        try {
            Organizzatore o = new Organizzatore();
            o.mostraGareInserimento(clientSocket);
        } catch (Exception e) {
            PHPResponseHandler responder = new PHPResponseHandler();
            responder.sendResponse(clientSocket, "Errore selezione gare: " + e.getMessage());
        }
    }
}
