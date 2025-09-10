package WebTalker;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class PHPResponseHandler {

    public PHPResponseHandler() {}

    /**
     * Invia una risposta semplice (singola riga) al client.
     */
    public void sendResponse(Socket clientSocket, String response) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(response);
            System.out.println(response); // Stampa la risposta sulla console server
        } catch (IOException e) {
            System.out.println("Errore nell'invio della risposta: " + e.getMessage());
        }
    }
}