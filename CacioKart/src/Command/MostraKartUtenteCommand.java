package Command;

import Logic.Socio;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class MostraKartUtenteCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();
        try {
            System.out.println("cf: " + in); // Ricevi CF del socio
            String cf = in.replace("richiestaKartUsr ", "").trim();
            Socio socio = new Socio();
            socio.setCf(cf);

            socio.mostraKartUtente(clientSocket); // Questo ora usa il DAO internamente

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore mostra kart utente: " + e.getMessage());
        }
    }
}
