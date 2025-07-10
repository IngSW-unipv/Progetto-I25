package Logic;

import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MostraGaraCommand implements RequestCommand{
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();

        try {
            System.out.println("idGaraCommand" + in);
            String idGara = in;
            if (idGara == null || idGara.isBlank()) {
                responder.sendResponse(clientSocket, "ID gara non valido");
                return;
            }

            Classifica service = new Classifica();
            service.generaClassifica(new ClassificaPenalitaStrategy(idGara), clientSocket);

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore classifica penalità: " + e.getMessage());
        }
    }
}
