import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PHPRequestHandler {
    ResultSet queryResult;
    PrintWriter out;

    public PHPRequestHandler() {
    }

    public void handleRequests(Socket clientSocket) {
        try {
            /**La classe si aspetta in entrata una stringa che inizia con un valore ENUM dichiarato
             * sopra in modo da interpretare la richiesta e decidere a che classe dare la responsabilità
             * di gestirla
             */
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
                    //response = queryResult.getString("")
                    //responder.sendResponse(socket,);
                    break;

                case 'r':
                    //Mi aspetto 'r Nome Cognome Data CF mail password\n'
                    String[] socio = messaggio.split(" ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dataNascita = LocalDate.parse(socio[3], formatter);
                    Socio nuovoUtente = new Socio(socio[1], socio[2], dataNascita, socio[4], socio[5], socio[6]);
                    nuovoUtente.registrazione(clientSocket);
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("1");
                    out.flush();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                default:
            }

            System.out.println("Messaggio ricevuto: " + messaggio);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}