package Logic;

import Objects.Kart;
import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ManutenzioneCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();

        try {
            System.out.println("frase" + in);// es. "AB123CD 29.99 freni rotti"
            String[] info = in.split(" ", 3);

            if (info.length < 3) {
                responder.sendResponse(clientSocket, "Formato dati non valido");
                return;
            }

            String targa = info[0];
            double prezzo = Double.parseDouble(info[1]);
            String descrizione = info[2];

            Kart k = new Kart();
            k.setTarga(targa);

            Meccanico m = new Meccanico();
            m.aggiornamentoManutenzione(k, descrizione, prezzo, clientSocket);

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore manutenzione: " + e.getMessage());
        }
    }
}
