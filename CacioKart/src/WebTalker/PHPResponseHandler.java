package WebTalker;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class PHPResponseHandler {

    public PHPResponseHandler() {
    }

    /**
     * Metodo per inviare risposte al client.
     * Tramite la classe PrintWriter utilizzo lo stream di comunicazione
     * verso il client per spedire messaggi e fare il flush del buffer.
     *
     * @param clientSocket Il socket attraverso il cui mandare la risposta
     */
    public void sendResponse(Socket clientSocket, String response) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(response);
            System.out.println("Risposta inviata: " + response);
        } catch (IOException e) {
            System.out.println("Errore nell'invio della risposta: " + e.getMessage());
        }
    }
}
