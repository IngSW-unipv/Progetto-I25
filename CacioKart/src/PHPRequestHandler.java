import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PHPRequestHandler {

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
                    Persona utente = new Persona(null,null,null,null,null,null);
                    utente.setcF(loginData[1]);
                    utente.setPassword(loginData[2]);
                    utente.login(clientSocket);

                    break;

                case 'r':
                    //Mi aspetto 'r Nome Cognome Data CF mail password\n'
                    String[] socio = messaggio.split(" ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dataNascita = LocalDate.parse(socio[3], formatter);
                    Socio nuovoUtente = new Socio(socio[1], socio[2], dataNascita, socio[4], socio[5], socio[6]);
                    nuovoUtente.registrazione(clientSocket);
                    break;

                default:
            }

            System.out.println("Messaggio ricevuto: " + messaggio);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}