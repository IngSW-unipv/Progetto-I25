package Command;

import Logic.Socio;
import Objects.Pezzo;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class AcquistaPezziCommand implements RequestCommand {
        @Override
        public void execute(String in, Socket clientSocket) throws Exception {
            PHPResponseHandler responder = new PHPResponseHandler();

            try {
                System.out.println("pezzi: " + in); // es. "ABC123XYZ 45"
                String[] info = in.split(" ");

                if (info.length != 2) {
                    responder.sendResponse(clientSocket, "Formato dati non valido. Usa: CF ID_PRODOTTO");
                    return;
                }

                String cf = info[0];
                String idProdotto = info[1];

                Pezzo pezzo = new Pezzo();
                pezzo.setIdProdotto(idProdotto);

                Socio socio = new Socio();
                socio.setCf(cf);

                socio.acquistaPezzi(pezzo, clientSocket);

            } catch (Exception e) {
                responder.sendResponse(clientSocket, "Errore acquisto pezzi: " + e.getMessage());
            }
        }
    }

