import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WebConnector {
    int porta = 50000;
    PHPResponseHandler responder;
    ResultSet queryResult;
    String response;

    public WebConnector() {
        while(true) {
            try (ServerSocket serverSocket = new ServerSocket(porta)) {
                System.out.println("Server in ascolto sulla porta " + porta);

                Socket socket = serverSocket.accept(); // Attende connessioni
                System.out.println("Client connesso!\n");

                /**La classe si aspetta in entrata una stringa che inizia con un valore ENUM dichiarato
                 * sopra in modo da interpretare la richiesta e decidere a che classe dare la responsabilità
                 * di gestirla
                 */
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String messaggio = in.readLine();
                System.out.println(messaggio);
                char comando = messaggio.charAt(0);
                messaggio = messaggio.substring(1);

                 switch (comando) {
                    case 'l':
                        //Mi aspetto 'l CF password\n'
                        String[] loginData = messaggio.split(" ");
                        Persona utente = null;
                        utente.setcF(loginData[1]);
                        utente.setPassword(loginData[2]);
                        queryResult = utente.login();
                        response = queryResult.getString("")
                        responder.sendResponse(socket,);
                        break;

                    case 'r':
                        //Mi aspetto 'r Nome Cognome Data CF mail password\n'
                        String[] socio = messaggio.split(" ");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate dataNascita = LocalDate.parse(socio[3], formatter);
                        Socio nuovoUtente = new Socio(socio[1], socio[2], dataNascita, socio[4], socio[5], socio[6]);
                        nuovoUtente.registrazione();
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
