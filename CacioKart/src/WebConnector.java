import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class WebConnector {
    int porta = 50000;
    private PHPRequestHandler requestHandler;
    private Socket clientSocket;
    private ServerSocket serverSocket;

    /**Costruttore della classe.
     * Alla costruzione della classe per gestire l'apertura della connessione,
     * viene anche creato un oggetto per gestire le possibili richieste.
     *
     */
    public WebConnector() {
        requestHandler = new PHPRequestHandler();
    }

    /**Metodo utilizzato dal main per creare il server.
     * Il metodo crea un socket per le connessioni e va in listen per qualsiasi client si voglia connettere.
     * Una volta che riceve una connessione, va nella classe per gestirle e dopo aver risolto,
     * chiude il socket e ne riapre un altro.
     *
     */
    public void createServer() throws SQLException {
        while(true) {
            try {
                System.out.println("Creazione server...");
                serverSocket = new ServerSocket(porta);
                System.out.println("Server in ascolto sulla porta " + porta);
                clientSocket = serverSocket.accept(); // Attende connessioni
                System.out.println("Client connesso!\n");
                requestHandler.handleRequests(getClientSocket());
                socketCloser();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**Metodo privato per chiudere i socket.
     * Non necessario, ma presente per facilità di lettura.
     *
     */
    private void socketCloser(){
        try {
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
