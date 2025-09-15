package Command;

import DAO.implementazioni.ConcessionariaDAO;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class AggiungiKartMeccanicoCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();

        try {
            // Dividi la stringa per spazio e prendi solo la targa!
            String[] parts = in.trim().split("\\s+");
            if (parts.length == 0) {
                responder.sendResponse(clientSocket, "Targa non valida");
                return;
            }

            // Se il comando è tipo "aggiungiKartMeccanico KRT111", prendi solo la targa
            String targa = parts.length == 1 ? parts[0] : parts[1];

            boolean aggiornata = ConcessionariaDAO.getInstance().decrementaQuantitaConcessionaria(targa);

            responder.sendResponse(clientSocket, aggiornata ? "OK" : "ERRORE");

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "ERRORE: " + e.getMessage());
        }
    }
}

