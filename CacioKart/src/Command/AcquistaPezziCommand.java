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
             System.out.println("pezzi" + in);// es. "ABC123XYZ 45"
            String[] info = in.split(" ");

            if (info.length != 2) {
                responder.sendResponse(clientSocket, "Formato dati non valido");
                return;
            }

            Socio s = new Socio();
            s.setCf(info[0]);

            Pezzo p = new Pezzo();
            p.setIdProdotto(info[1]);

            s.acquistaPezzi(p, clientSocket);

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore acquisto pezzi: " + e.getMessage());
        }
    }

}
