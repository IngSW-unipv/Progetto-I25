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
            System.out.println("Ricevuto input: " + in);
            String[] kartUtente = in.split(" ");

            if (kartUtente.length != 2) {
                responder.sendResponse(clientSocket, "Formato dati errato. Usa: CF TARGA");
                return;
            }

            String cf = kartUtente[0];
            String targa = kartUtente[1];

            Kart kart = new Kart();
            kart.setTarga(targa);

            Socio socio = new Socio();
            socio.setCf(cf);

            socio.compraKart(kart, clientSocket);

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore durante acquisto kart: " + e.getMessage());
        }
    }
}
