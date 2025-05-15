package WebTalker;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class PHPResponseHandler {
    private PrintWriter out;

    public PHPResponseHandler() {
    }

    /**
     * Metodo per inviare risposte al client.
     * Tramite la classe PrintWriter utilizzo lo stream di comunicazione
     * verso il client per spedire messaggi e fare il flush del buffer.
     *
     * @param clientSocket Il socket attraverso il cui mandare la risposta
     * @param messaggio Il messaggio da spedire
     */
    public void sendResponse(Socket clientSocket, String messaggio) {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(messaggio);
            System.out.println("Il messaggio spedito è: " + messaggio + "\n");

        } catch (IOException e) {
            System.out.println("Non sono riuscito a spedire il messaggio: " + e.getMessage());

        }

    }
}
