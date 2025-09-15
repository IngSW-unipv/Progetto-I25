package WebTalker;

import Enums.TipoComandi;
import Command.CommandFactory;
import Command.RequestCommand;

import java.net.Socket;

public class PHPRequestHandler {
        public void handleRequest(Socket clientSocket, TipoComandi tipoOggetto, String linea) {
            try {
                RequestCommand cmd = CommandFactory.getCommand(tipoOggetto);
                if (cmd != null) {
                    cmd.execute(linea, clientSocket);  // qui passi tutta la riga
                } else {
                    new PHPResponseHandler().sendResponse(clientSocket, "Tipo richiesta non gestito: " + tipoOggetto);
                }
            } catch (Exception e) {
                new PHPResponseHandler().sendResponse(clientSocket, "Errore: " + e.getMessage());
            }
        }
}



