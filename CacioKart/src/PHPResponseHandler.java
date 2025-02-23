import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class PHPResponseHandler {
    PrintWriter out;

    public PHPResponseHandler() {
    }

    public void sendResponse(Socket clientSocket, String messaggio){
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(messaggio);
            System.out.println("Il messaggio spedito è: " + messaggio);
            out.flush();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
