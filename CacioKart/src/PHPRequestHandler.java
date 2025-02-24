import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PHPRequestHandler {
    private BufferedReader in;
    private String comando;
    private String info;
    private String messaggio[];

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
            messaggio = in.readLine().split(" ",2);
            comando = messaggio[0];
            info = messaggio[1];
            TipoComandi tipo = TipoComandi.requestedCommand(comando);

            /**Switch per gestire i comandi
             *
             */
            switch (tipo) {

                case LOGIN:
                    loginCase(info, clientSocket);
                    break;

                case REGISTRAZIONE:
                    registerCase(info, clientSocket);
                    break;

                case CLASSIFICA_GENERALE:

                    break;
                case PRENOTAZIONE:
                    prenotationCase(info,clientSocket);
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

    //riceve i dati della prenotazione dal sito
    //va in socio e crea una prenotazione
    //risponde al sito tramite phpresponsehandler
    //usa DBConnector e PHPResponsehandler
    private void prenotationCase(String messaggio, Socket clientSocket){
        String[] prenotazione = messaggio.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataP = LocalDate.parse(prenotazione[0], formatter);
        formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime orarioJava = LocalTime.parse(prenotazione[1], formatter);
        Socio utente = new Socio(null, null, null, null, null, null);
        utente.setcF(prenotazione[2]);

    }
}