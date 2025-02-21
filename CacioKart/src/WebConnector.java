import java.io.*;
import java.net.*;
public class WebConnector {
    int porta = 50000;

    public WebConnector() {
        while(true) {
            try (ServerSocket serverSocket = new ServerSocket(porta)) {
                System.out.println("Server in ascolto sulla porta " + porta);

                Socket socket = serverSocket.accept(); // Attende connessioni
                System.out.println("Client connesso!\n");

                //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                //out.println("1 0");
                //System.out.println("Messaggio inviato\n");

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String messaggio = in.readLine();
                char firstChar = messaggio.charAt(0);
                //spacchettamento stringa
                switch (firstChar) {
                    case 'l':
                        System.out.println("Sono nel case");
                        System.out.println("Pare che il messaggio fosse: " + messaggio.substring(1));
                        break;

                    default:
                }

                System.out.println("Messaggio ricevuto: " + messaggio);

                socket.close();
                System.out.println("Connessione chiusa!\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
