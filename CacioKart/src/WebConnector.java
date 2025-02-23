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

    public void createServer() {
        while(true) {
            try {
                System.out.println("Creating server...");
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
