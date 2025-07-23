package Command;
import Logic.Socio;
import Objects.Kart;
import WebTalker.PHPResponseHandler;


import java.net.Socket;

public class AcquistaKartCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();
        try {
            System.out.println("targa" + in);
            String[] kartUtente = in.split(" ");

            if (kartUtente.length != 2) {
                responder.sendResponse(clientSocket, "Formato dati errato");
                return;
            }

            Socio socio = new Socio();
            socio.setCf(kartUtente[0]);

            Kart k = new Kart();
            k.setTarga(kartUtente[1]);

             socio.compraKart(k, clientSocket);
        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore durante acquisto kart: " + e.getMessage());
        }
    }
}