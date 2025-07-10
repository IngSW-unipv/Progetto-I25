package WebTalker;

import Enums.TipoComandi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebConnector {
    private final int porta = 50000;
    private final PHPRequestHandler requestHandler;

    /**
     * Costruttore della classe.
     * Alla costruzione della classe per gestire l'apertura della connessione,
     * viene anche creato un oggetto per gestire le possibili richieste.
     */
    public WebConnector() {
        requestHandler = new PHPRequestHandler();
    }

    /**
     * Metodo utilizzato dal main per creare il server.
     * Il metodo crea un socket per le connessioni e va in listen per qualsiasi client si voglia connettere.
     * Una volta che riceve una connessione, va nella classe per gestirle e dopo aver risolto,
     * chiude il socket e ne riapre un altro.
     */
    public void createServer() {
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Server in ascolto sulla porta " + porta + "\n");

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connesso!");

                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    // Legge l'intera riga: tipo comando + argomenti
                    String linea = reader.readLine();
                    if (linea == null || linea.isBlank()) {
                        System.out.println("Nessun comando ricevuto.");
                        continue;
                    }

                    System.out.println("Richiesta ricevuta: " + linea);

                    String[] tokens = linea.trim().split(" ");
                    String tipoComandoStr = tokens[0];  // es: "login"

                    TipoComandi tipoComando = TipoComandi.requestedCommand(tipoComandoStr);
                    if (tipoComando != null) {
                        requestHandler.handleRequest(clientSocket, tipoComando, linea);
                    } else {
                        new PHPResponseHandler().sendResponse(clientSocket, "Tipo richiesta non valido: " + tipoComandoStr);
                    }

                } catch (IOException e) {
                    System.out.println("Errore connessione client: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Errore nel server: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}