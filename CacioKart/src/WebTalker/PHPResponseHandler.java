package WebTalker;

import Objects.Kart;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

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

    /**
     * Invia una lista di kart al client (ogni kart su una riga, con punto e virgola finale, poi "end").
     * Stampa sulla console server ogni riga inviata.
     */
    public void sendKartList(Socket clientSocket, List<Kart> lista) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            if (lista == null || lista.isEmpty()) {
                out.println("Nessun kart trovato");
                out.println("end");
                System.out.println("Nessun kart trovato");
                System.out.println("end");
                return;
            }
            for (Kart k : lista) {
                // Usa lo spazio come separatore!
                String riga = k.getTarga() + " " + k.getCilindrata() + " " + k.getSerbatoio();
                out.println(riga);
                System.out.println(riga); // Debug sulla console server
            }
            out.println("end");
            System.out.println("end");
        } catch (IOException e) {
            System.out.println("Errore nell'invio della lista kart: " + e.getMessage());
        }
    }

}
