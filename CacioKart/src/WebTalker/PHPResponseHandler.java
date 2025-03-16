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
     * Il chiamante deve passare il socket di comunicazione con il client e
     * il messaggio da spedire.
     *
     * @param clientSocket
     * @param messaggio
     */
    public void sendResponse(Socket clientSocket, String messaggio) {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(messaggio);
            out.flush();    //Pulisco il buffer di dati per evitare problemi
            System.out.println("Il messaggio spedito è: " + messaggio + "\n");

        } catch (IOException e) {
            System.out.println("Non sono riuscito a spedire il messaggio: " + e.getMessage());

        }

    }
}
