package Logic;

import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MostraPezziUtenteCommand implements RequestCommand{

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        try {
            System.out.println("cf" + in ); // Ricevi il codice fiscale dell'utente
            Socio s = new Socio();
            s.setCf(in);
            s.mostraPezziUtente(clientSocket);
        } catch (Exception e) {
            new PHPResponseHandler().sendResponse(clientSocket, "Errore mostra pezzi utente: " + e.getMessage());
        }
    }
}
