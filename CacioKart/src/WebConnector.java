import java.io.*;
import java.net.*;
public class WebConnector {
    int porta = 50000;

    public WebConnector() {
        while(true) {
            try (ServerSocket serverSocket = new ServerSocket(porta)) {
                System.out.println("Server in ascolto sulla porta " + porta);

                Socket socket = serverSocket.accept(); // Attende connessioni
                System.out.println("Client connesso!");

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("1");
                System.out.println("Messaggio inviato");
                /*BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String messaggio = in.readLine();

                switch (messaggio) {
                    case "login ":
                        System.out.println("Sono nel case");
                        String username = in.readLine();
                        String pwd = in.readLine();
                        System.out.println("Utente ricevuto: " + username + "\nPassword ricevuta: " + pwd);
                        break;

                    default:
                }

                System.out.println("Messaggio ricevuto: " + messaggio);
                */
                socket.close();
                System.out.println("Connessione chiusa!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
