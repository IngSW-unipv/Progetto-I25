package Logic;

import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class AggiungiGareCampCommand implements RequestCommand{
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();
        try {
            System.out.println("AggiungiGareCampCommand" + in);
            String[] info = in.split(" ");

            if (info.length < 2) {
                responder.sendResponse(clientSocket, "Formato dati non valido per aggiunta gara.");
                return;
            }

            String idGara = info[0];
            String idCamp = info[1];

            Organizzatore organizzatore = new Organizzatore();
            organizzatore.aggiungiGaraPartecipa(idGara, idCamp, clientSocket);

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore aggiunta gara a campionato: " + e.getMessage());
        }
    }
}
