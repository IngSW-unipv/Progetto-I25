import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PHPRequestHandler {
    private BufferedReader in;

    public PHPRequestHandler() {
    }

    /**La classe riceve stringhe composte in questo modo: *lettera singola* *dati*
     *La lettera indica la richiesta del client da eseguire.
     *
     * @param clientSocket
     */
    public void handleRequests(Socket clientSocket) {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String messaggio = in.readLine();
            char comando = messaggio.charAt(0);
            messaggio = messaggio.substring(1);

            switch (comando) {
                case 'l':
                    loginCase(messaggio, clientSocket);
                    break;

                case 'r':
                    registerCase(messaggio, clientSocket);
                    break;

                case 'c':
                    break;

                default:
                    break;
            }

            System.out.println("Messaggio ricevuto: " + messaggio);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**Metodo per gestire la logica di login
     * Prendo il messaggio e lo divido nelle singole informazioni richieste.
     *
     * @param messaggio
     * @param clientSocket
     */
    private void loginCase(String messaggio, Socket clientSocket) {
        String[] loginData = messaggio.split(" ");
        Persona utente = new Persona(null, null, null, null, null, null);
        utente.setcF(loginData[1]);
        utente.setPassword(loginData[2]);
        utente.login(clientSocket);
    }

    /**Metodo per gestire la logica di registrazione
     * Prendo il messaggio e lo divido nelle singole informazioni richieste
     * Utilizzo il DateTimeFormatter per far combaciare la data in ingresso con
     * il tipo Date presente nel database.
     *
     * @param messaggio
     * @param clientSocket
     */
    private void registerCase(String messaggio, Socket clientSocket){
        String[] socio = messaggio.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataNascita = LocalDate.parse(socio[3], formatter);
        Socio nuovoUtente = new Socio(socio[1], socio[2], dataNascita, socio[4], socio[5], socio[6]);
        nuovoUtente.registrazione(clientSocket);
    }

}