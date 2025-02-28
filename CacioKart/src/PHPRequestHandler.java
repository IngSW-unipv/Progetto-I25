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
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

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

                case PRENOTAZIONE_LIBERA:
                    String tipologia = "libera";
                    prenotationCase(tipologia,info,clientSocket);
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

                case REGISTRAZIONE_DIPENDENTE:
                    aggiungiDipendenteCase(info, clientSocket);
                    break;

                case RICHIESTA_DIPENDENTE:
                    mostraDipendentiCase(clientSocket);
                    break;

                case ELIMINA_DIPENDENTE:
                    eliminaDipendenteCase(info,clientSocket);
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
    private void loginCase(String dati, Socket clientSocket) throws SQLException {
        String[] loginData = dati.split(" ");
        Persona utente = new Persona(null, null, null, null, null, null);
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
        LocalDate dataNascita = LocalDate.parse(socio[2], dateFormatter);
        Socio nuovoUtente = new Socio(socio[0], socio[1], dataNascita, socio[3], socio[4], socio[5]);
        nuovoUtente.registrazione(clientSocket);
    }

    //riceve i dati della prenotazione dal sito
    //va in socio e crea una prenotazione
    //risponde al sito tramite phpresponsehandler
    //usa DBConnector e PHPResponsehandler

    private void prenotationCase(String tipologia, String messaggio, Socket clientSocket) throws SQLException {
        //2025-03-26 11:00-12:00
        //int lastDashIndex = messaggio.lastIndexOf("-");
        String[] dataOrario = messaggio.split(" ");
        /*
        if (lastDashIndex == -1) {
            System.out.println("Errore: il messaggio non contiene '-' per la divisione.");
            return;
        }*/

        LocalDate dataG = LocalDate.parse(dataOrario[0], dateFormatter);
        String[] orari = dataOrario[1].split("-");
        LocalTime orarioI = LocalTime.parse(orari[0], timeFormatter);
        LocalTime orarioF = LocalTime.parse(orari[1], timeFormatter);
        Socio utente = new Socio(null, null, null, dataOrario[2], null, null);
        utente.richiestaP(tipologia, dataG, orarioI, orarioF, clientSocket);
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
        Kart k = new Kart(kart[0],Integer.parseInt(kart[1]),20);
        Concessionaria c = new Concessionaria();
        c.inserimentoKart(k,clientSocket);

    }

    /**Metodo per mostrare i kart
     * Non richiede parametri oltre al socket per spedire la risposta.
     *
     * @param clientSocket
     * @throws SQLException
     */
    private void mostraKartCase(Socket clientSocket) throws SQLException {
        Meccanico m = new Meccanico();
        m.mostraKart(clientSocket);
    }

    /**Metodo per rimuovere i kart
     * Chiamato dal client dopo aver visto i kart presenti nel db.
     * Non richiede parametri oltre al socket per spedire la risposta.
     *
     * @param dati
     * @param clientSocket
     * @throws SQLException
     */
    private void rimozioneKartCase(String dati, Socket clientSocket) throws SQLException {
        Meccanico m = new Meccanico();
        m.rimozioneKart(dati, clientSocket);
    }

    private void aggiungiDipendenteCase(String dati, Socket clientSocket) throws SQLException {
        /*Creo enum secondo questa logica:
        Meccanico 1
        Gestore 2
        Arbitro 3
        Organizzatore 4
        Proprietario 5
        */
        String[] dipendente = dati.split(" ");
        //Array che va da 0 a 8
        LocalDate dataN = LocalDate.parse(dipendente[2], dateFormatter);
        LocalTime oreL = LocalTime.parse(dipendente[8], timeFormatter);
        Proprietario p = new Proprietario();
        //dip nome cognome mail passw dataN stipendio ruolo oreL
        Dipendente d = new Dipendente(dipendente[0], dipendente[1], dataN, dipendente[3], dipendente[4], dipendente[5], Double.parseDouble(dipendente[6]), dipendente[7], oreL);
        p.aggiuntaDipendenti(d,clientSocket);
    }

    private void eliminaDipendenteCase(String dati, Socket clientSocket) throws SQLException {
        Proprietario p = new Proprietario();
        p.rimozioneDipendenti(dati,clientSocket);

    }

    private void mostraDipendentiCase(Socket clientSocket) throws SQLException {
        Proprietario p = new Proprietario();
        p.mostraDipendenti(clientSocket);
    }
}