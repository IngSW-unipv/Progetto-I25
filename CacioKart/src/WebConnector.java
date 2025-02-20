import java.io.*;
import java.net.*;
public class WebConnector {
    int porta = 50000;

    public WebConnector() {
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Server in ascolto sulla porta " + porta);

            Socket socket = serverSocket.accept(); // Attende connessioni
            System.out.println("Client connesso!");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("1 2");
            /*
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String messaggio = in.readLine(); // Legge il messaggio dal client
            System.out.println("Messaggio ricevuto: " + messaggio);
            */
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
