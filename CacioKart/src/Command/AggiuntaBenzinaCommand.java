package Logic;

import Objects.Kart;
import WebTalker.PHPResponseHandler;
import java.net.Socket;

public class AggiuntaBenzinaCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();
        try {
            System.out.println("Input ricevuto: " + in);
            // Dividi la stringa e prendi SOLO la targa
            String[] parti = in.trim().split("\\s+");
            String targa = parti.length > 1 ? parti[1] : parti[0];

            Kart k = new Kart();
            k.setTarga(targa);
            Meccanico m = new Meccanico();
            m.effettuaPieno(k, clientSocket);

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore durante aggiunta benzina: " + e.getMessage());
        }
    }
}

