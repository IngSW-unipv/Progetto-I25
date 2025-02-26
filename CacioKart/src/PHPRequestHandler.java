import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
            if(messaggio.length == 2) {
                info = messaggio[1]; //Le informazioni relative al resto del comando comporranno la seconda parte del messaggio
                System.out.println("Messaggio ricevuto: " + messaggio[0] + " " + messaggio[1]);
            }
            tipo = TipoComandi.requestedCommand(comando); //Controllo a quale ENUM corrisponde il comando

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

                case AGGIUNTA_KART:
                    aggiuntaKartCase(info, clientSocket);
                    break;

                case MOSTRA_KART:
                    mostraKartCase(clientSocket);
                    break;

                case ELIMINAZIONE_KART:
                    rimozioneKartCase(info, clientSocket);
                    break;
                case 'p':
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
    private void prenotationCase(String messaggio, Socket clientSocket) throws SQLException {
        int res;
        String[] prenotazione = messaggio.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataG = LocalDate.parse(prenotazione[0], formatter);
        formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime orario= LocalTime.parse(prenotazione[1], formatter);
        Socio utente = new Socio(null, null, null, null, null, null);
        utente.setcF(prenotazione[2]);
        res=utente.prenotation(dataG,orario,clientSocket);
        if(res==0) {
            System.out.println("Raggiunta la massima capienza: \n" + messaggio);
        }else{
            System.out.println("prenotazione confermata\n");
        }
    }

    /**Metodo di aggiunta kart
     * Pressoché identico al metodo di registrazione.
     *
     * @param dati
     * @param clientSocket
     * @throws SQLException
     */
    private void aggiuntaKartCase(String dati, Socket clientSocket) throws SQLException {
        String[] kart = dati.split(" "); //Passare a kart
        Kart k = new Kart(kart[0],Integer.parseInt(kart[1]),Double.parseDouble(kart[2]));
        Concessionaria c = new Concessionaria();
        c.inserimentoKart(k,clientSocket);

    }

    private void mostraKartCase(Socket clientSocket) throws SQLException {
        Meccanico m = new Meccanico();
        m.mostraKart(clientSocket);
    }

    private void rimozioneKartCase(String dati, Socket clientSocket) throws SQLException {
        Meccanico m = new Meccanico();
        m.rimozioneKart(dati, clientSocket);
    }
}