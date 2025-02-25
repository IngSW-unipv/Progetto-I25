import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PHPRequestHandler {
    private BufferedReader in;
    private String comando;
    private String info;
    private String messaggio[];
    private TipoComandi tipo;

    public PHPRequestHandler() {
    }

    /**La classe riceve stringhe composte in questo modo: *lettera singola* *dati*
     *La lettera indica la richiesta del client da eseguire.
     *
     * @param clientSocket
     */
    public void handleRequests(Socket clientSocket) throws SQLException {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //Creo un oggetto per leggere i messaggi in arrivo
            messaggio = in.readLine().split(" ",2); //Divido il messaggio in due
            comando = messaggio[0]; //Il comando da gestire sarà la prima parte del messaggio
            info = messaggio[1]; //Le informazioni relative al resto del comando comporranno la seconda parte del messaggio
            tipo = TipoComandi.requestedCommand(comando); //Controllo a quale ENUM corrisponde il comando
            System.out.println("Messaggio ricevuto: " + messaggio[0] + " "+ messaggio[1]);

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
                    //prenotationCase(info,clientSocket);
                    break;

                case AGGIUNTA_KART:
                    aggiuntaKartCase(info, clientSocket);
                    break;

                case MOSTRA_KART:
                    Meccanico m = new Meccanico();
                    m.mostraKart(clientSocket);

                    break;

                default:
                    break;
            }

        } catch (IOException e) {
            System.out.println("Errore nella lettura: " + e.getMessage());
        }

    }

    /**Metodo per gestire la logica di login
     * Prendo il messaggio e lo divido nelle singole informazioni richieste.
     *
     * @param dati
     * @param clientSocket
     */
    private void loginCase(String dati, Socket clientSocket) {
        String[] loginData = dati.split(" ");
        Persona utente = new Persona(/*null, null, null, null, null, null*/);
        utente.setcF(loginData[0]);
        utente.setPassword(loginData[1]);
        utente.login(clientSocket);
    }

    /**Metodo per gestire la logica di registrazione
     * Prendo il messaggio e lo divido nelle singole informazioni richieste
     * Utilizzo il DateTimeFormatter per far combaciare la data in ingresso con
     * il tipo Date presente nel database.
     *
     * @param dati
     * @param clientSocket
     */
    private void registerCase(String dati, Socket clientSocket) throws SQLException {
        String[] socio = dati.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataNascita = LocalDate.parse(socio[2], formatter);
        Socio nuovoUtente = new Socio(socio[0], socio[1], dataNascita, socio[3], socio[4], socio[5]);
        nuovoUtente.registrazione(clientSocket);
    }

    //riceve i dati della prenotazione dal sito
    //va in socio e crea una prenotazione
    //risponde al sito tramite phpresponsehandler
    //usa DBConnector e PHPResponsehandler
    /*
    private void prenotationCase(String messaggio, Socket clientSocket){
        String[] prenotazione = messaggio.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataP = LocalDate.parse(prenotazione[0], formatter);
        formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime orarioJava = LocalTime.parse(prenotazione[1], formatter);
        Socio utente = new Socio(null, null, null, null, null, null);
        utente.setcF(prenotazione[2]);

    }*/
}