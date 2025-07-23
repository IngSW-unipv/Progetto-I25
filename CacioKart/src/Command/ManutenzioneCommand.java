package Logic;

import Objects.Kart;
import WebTalker.PHPResponseHandler;
import java.net.Socket;

public class ManutenzioneCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();

        try {
            System.out.println("frase " + in); // es. "manutenzione KRT228 4444 distruzione motore"
            String[] info = in.split(" ", 4); // Splitta al massimo in 4 parti

            if (info.length < 4) {
                responder.sendResponse(clientSocket, "Formato dati non valido");
                return;
            }

            String targa = info[1];
            double prezzo = Double.parseDouble(info[2]);
            String descrizione = info[3];

            Kart k = new Kart();
            k.setTarga(targa);

            Meccanico m = new Meccanico();
            m.aggiornaManutenzione(k, descrizione, prezzo, clientSocket);

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore manutenzione: " + e.getMessage());
        }
    }
}
