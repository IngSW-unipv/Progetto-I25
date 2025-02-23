import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebConnector {
    int porta = 50000;
    private PHPRequestHandler requestHandler;
    Socket clientSocket;
    ServerSocket serverSocket;

    public WebConnector() {
        requestHandler = new PHPRequestHandler();
    }

    /**Metodo utilizzato dal main per creare il server.
     * Il metodo crea un socket per le connessioni e va in listen per qualsiasi client si voglia connettere.
     * Una volta che riceve una connessione, va nella classe per gestirle e dopo aver risolto,
     * chiude il socket e ne riapre un altro.
     *
     */
    public void createServer() {
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

    private void socketCloser(){
        try {
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
