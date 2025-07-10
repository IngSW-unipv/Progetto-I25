package Logic;

import Objects.Kart;
import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class AggiuntaBenzinaCommand implements RequestCommand{
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();
        try {
            System.out.println("targa" + in);
            String targa = in;  // riceve solo la targa

            if (targa == null || targa.isBlank()) {
                responder.sendResponse(clientSocket, "Targa non valida");
                return;
            }

            Meccanico m = new Meccanico();
            Kart k = new Kart();
            k.setTarga(targa);
            m.aggiuntaBenzina(k, clientSocket);

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore durante aggiunta benzina: " + e.getMessage());
        }
    }
}
