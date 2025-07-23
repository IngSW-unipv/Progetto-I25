package Logic;

import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MostraKartUtenteCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        try {
            System.out.println("cf" + in); // Ricevi CF del socio
            Socio s = new Socio();
            s.setCf(in);
            s.mostraKartUtente(clientSocket);
        } catch (Exception e) {
            new PHPResponseHandler().sendResponse(clientSocket, "Errore mostra kart utente: " + e.getMessage());
        }
    }
}
