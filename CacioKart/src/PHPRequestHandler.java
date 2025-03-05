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
    private String[] messaggio;
    private String tipologia;
    private TipoComandi tipo;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private String query;

    public PHPRequestHandler() {
    }

    /**La classe riceve stringhe composte in questo modo: *parola singola* *dati*
     *La prima parola indica la richiesta del client da eseguire.
     *La seconda parte del messaggio cambia in base alla richiesta da eseguire,
     * potrebbe contenere 0 o più informazioni a seconda dei dati necessari.
     */
    public void handleRequests(Socket clientSocket) throws SQLException {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //Creo un oggetto per leggere i messaggi in arrivo
            messaggio = in.readLine().split(" ",2); //Divido il messaggio in due: il comando e i dati

            comando = messaggio[0]; //Il comando da gestire sarà la prima parte del messaggio
            if(messaggio.length == 2) { //Controllo se il messaggio contiene più di una parola
                info = messaggio[1]; //Le informazioni relative al resto del comando comporranno la seconda parte del messaggio
                System.out.println("Messaggio ricevuto: " + messaggio[0] + " " + messaggio[1]);
            }else{
                System.out.println("Messaggio ricevuto: " + messaggio[0]);
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
                    tipologia = "libera";
                    prenotationCase(tipologia,info,clientSocket);
                    break;

                case AGGIUNTA_KART_CONCESSIONARIA:
                    aggiuntaKartCaseConcessionaria(info, clientSocket);
                    break;

                case AGGIUNGI_KART_MECCANICO:
                    aggiuntaKartCaseMeccanico(info, clientSocket);
                    break;

                case MOSTRA_KART_AGGIUNTA:
                    mostraAggiuntaKartCase(clientSocket);
                    break;

                case MOSTRA_KART_RIMUOVI:
                    mostraRimuoviKartCase(clientSocket);
                    break;

                case MOSTRA_KART_MANUTENZIONE:
                    mostraManutenzioneKartCase(clientSocket);
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

                case ACQUISTA_KART:
                    acquistaKartCase(info, clientSocket);
                    break;

                case AGGIUNGI_BENZINA:
                    aggiungiBenzinaCase(info, clientSocket);
                    break;

                case CLASSIFICA_GENERALE:
                    classificaGenerale(clientSocket);
                    break;

                case CLASSIFICA_UTENTE:
                    classificaUtente(info,clientSocket);
                    break;

                case CLASSIFICA_ARBITRO:
                    classificaArbitro(clientSocket);
                    break;

                case MANUTENZIONE:
                    manutenzioneCase(info, clientSocket);
                    break;

                case MOSTRA_PEZZI:
                    mostraPezziCase(clientSocket);
                    break;

                /*case ACQUISTA_PEZZI:
                    acquistaPezzi(info,clientSocket);
                    break;*/

                case MOSTRA_GARA:
                    mostraGaraCase(info,clientSocket);
                    break;

                default:
                    break;
            }
            in.close();

        } catch (IOException e) {
            System.out.println("Errore nella lettura: " + e.getMessage());
        }

    }

    /**Metodo per gestire la logica di login.
     * Prendo il messaggio e lo divido nelle singole informazioni richieste.
     *
     * @param dati
     * @param clientSocket
     */
    private void loginCase(String dati, Socket clientSocket) throws SQLException {
        String[] loginData = dati.split(" ");
        Persona utente = new Persona();
        utente.setcF(loginData[0]);
        utente.setPassword(loginData[1]);
        utente.login(clientSocket);
    }

    /**Metodo per gestire la logica delle classifiche per l'arbitro.
     * L'arbitro è interessato a vedere tutte le classifiche delle
     * gare effettuate, senza duplicati.
     *
     * @param clientSocket
     * @throws SQLException
     */
    private void classificaArbitro(Socket clientSocket) throws SQLException {
        Classifica c = new Classifica();
        query =  "SELECT DISTINCT idGara FROM caciokart.classifica ORDER BY idGara";
        c.classificaArbitro(query, clientSocket);
    }

    /**Metodo per gestire la logica della classifica generale
     * visibile nella prima pagina del sito.
     * Seleziono dal db tutte le informazioni di una singola classifica
     * mettendola in Join con i soci per ottenere le informazioni di un singolo
     * socio.
     *
     * @param clientSocket
     * @throws SQLException
     */
    private void classificaGenerale(Socket clientSocket) throws SQLException {
        Classifica c = new Classifica();
        query = "SELECT c.idGara, s.nome, s.cognome, c.targa, c.bGiro, c.tempTot " +
                "FROM caciokart.classifica AS c " +
                "JOIN (SELECT idGara, MIN(tempTot) AS tempo_migliore FROM caciokart.classifica GROUP BY idGara) AS agg " +
                "ON c.idGara = agg.idGara AND c.tempTot = agg.tempo_migliore " +
                "JOIN caciokart.socio AS s ON c.socio = s.socio " +
                "ORDER BY c.tempTot DESC " +
                "LIMIT 10";
        c.classificaCompleta(query,clientSocket);
    }

    /**Metodo per gestire la classifica visibile da un singolo utente.
     * In ingresso il cf dell'utente ci permette di fare una query
     * di tutte le classifiche che appartengono solo a lui.
     *
     * @param cfPilota
     * @param clientSocket
     * @throws SQLException
     */
    private void classificaUtente(String cfPilota,Socket clientSocket) throws SQLException {
        Classifica c = new Classifica();
        query = "SELECT c.idGara, " +
                "       c.targa, " +
                "       c.bGiro, " +
                "       c.tempTot " +
                "FROM caciokart.classifica AS c " +
                "JOIN caciokart.socio AS s ON c.socio = s.socio " +
                "WHERE s.nome = '" + cfPilota + "' " +
                "ORDER BY c.tempTot DESC " +
                "LIMIT 10";
        c.classificaUtente(query,clientSocket);
    }

    /**Metodo per gestire la logica di registrazione.
     * Prendo il messaggio e lo divido nelle singole informazioni richieste.
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
    //usa DBConnector e PHPResponsehandle

    private void prenotationCase(String tipologia, String messaggio, Socket clientSocket) throws SQLException {

        String[] dataOrario = messaggio.split(" ");
        LocalDate dataG = LocalDate.parse(dataOrario[0], dateFormatter);
        String[] orari = dataOrario[1].split("-");
        LocalTime orarioI = LocalTime.parse(orari[0], timeFormatter);
        LocalTime orarioF = LocalTime.parse(orari[1], timeFormatter);
        Socio utente = new Socio(null, null, null, dataOrario[2], null, null);
        utente.richiestaP(tipologia, dataG, orarioI, orarioF, clientSocket);
    }

    /**Metodo di aggiunta kart.
     * Pressoché identico al metodo di registrazione.
     *
     * @param dati
     * @param clientSocket
     * @throws SQLException
     */
    private void aggiuntaKartCaseConcessionaria(String dati, Socket clientSocket) throws SQLException {
        String[] info = dati.split(" "); //Passare a kart
        Kart k = new Kart(info[0],Integer.parseInt(info[1]),20);
        int prezzo = Integer.parseInt(info[2]);
        Concessionaria c = new Concessionaria();
        c.inserimentoKart(k,prezzo,clientSocket);

    }

    /**Metodo per aggiungere i kart dalla vendita del concessionario
     * al noleggio del kartdromo a cura del meccanico.
     * La targa in ingresso ci permette di selezionare un kart specifico da rimuovere
     * dalla tabella concessionario.
     *
     * @param targa
     * @param clientSocket
     * @throws SQLException
     */
    private void aggiuntaKartCaseMeccanico(String targa, Socket clientSocket) throws SQLException {
        Meccanico m = new Meccanico();
        m.aggiuntaKart(targa, clientSocket);

    }

    /**Metodo per mostrare i kart disponibili a passare dalla vendita al noleggio.
     * Non richiede parametri oltre al socket per spedire la risposta.
     *
     * @param clientSocket
     * @throws SQLException
     */
    private void mostraAggiuntaKartCase(Socket clientSocket) throws SQLException {
        Meccanico m = new Meccanico();
        query = "SELECT * FROM caciokart.kart WHERE kart.targa NOT IN " +
                "(SELECT socio.targa FROM socio WHERE socio.targa IS NOT NULL)" +
                "AND kart.targa IN (SELECT concessionaria.tipol FROM concessionaria)";
        m.mostraKart(query,clientSocket);
    }

    /**Metodo per rimuovere i kart dal kartodromo.
     * Il client manda una targa da rimuovere dal db, il metodo
     * la utilizza per la query e risponde al client.
     *
     * @param targa
     * @param clientSocket
     * @throws SQLException
     */
    private void rimozioneKartCase(String targa, Socket clientSocket) throws SQLException {
        Meccanico m = new Meccanico();
        m.rimozioneKart(targa, clientSocket);
    }

    /**Metodo per mostrare i kart disponibili alla rimozione.
     * Non necessita di dati in ingresso, la query è sempre quella.
     *
     * @param clientSocket
     * @throws SQLException
     */
    private void mostraRimuoviKartCase(Socket clientSocket) throws SQLException {
        Meccanico m = new Meccanico();
        //Query per quando voglio rimuovere i kart dal noleggio
        query = "SELECT * FROM caciokart.kart WHERE targa NOT IN (" +
                "SELECT tipol FROM caciokart.concessionaria WHERE tipol IS NOT NULL) " +
                "AND targa NOT IN (SELECT targa FROM caciokart.socio WHERE targa IS NOT NULL)";
        m.mostraKart(query,clientSocket);
    }

    /**Metodo per mostrare i kart appartenenti ai clienti e
     * disponibili al noleggio per modificarne la manutenzione.
     * Non necessita di dati in ingresso, la query è sempre quella.
     *
     * @param clientSocket
     * @throws SQLException
     */
    private void mostraManutenzioneKartCase(Socket clientSocket) throws SQLException {
        Meccanico m = new Meccanico();
        //Query per quando voglio aggiungere i kart al noleggio
        query = "SELECT * FROM caciokart.kart WHERE kart.targa NOT IN " +
                "(SELECT concessionaria.tipol FROM concessionaria)";
        m.mostraKart(query,clientSocket);
        //KRT123 06/03/2023
        //DA FARE
    }

    /**Metodo per aggiungere dipendenti.
     * Dopo aver formattato le date tramite i formatter, le passo al costruttore
     * di Dipendente per utilizzare i metodi di get e set nel metodo
     * di aggiuntaDipendenti.
     *
     * @param dati
     * @param clientSocket
     * @throws SQLException
     */
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

    /**Metodo di rimozione dipendenti.
     * Pressoché identico al metodo di rimozione kart.
     *
     * @param dati
     * @param clientSocket
     * @throws SQLException
     */
    private void eliminaDipendenteCase(String dati, Socket clientSocket) throws SQLException {
        Proprietario p = new Proprietario();
        p.rimozioneDipendenti(dati,clientSocket);

    }

    /**Metodo per mostrare tutti i dipendenti.
     * Pressoché identico al metodo per mostrare i kart.
     *
     * @param clientSocket
     * @throws SQLException
     */
    private void mostraDipendentiCase(Socket clientSocket) throws SQLException {
        Proprietario p = new Proprietario();
        p.mostraDipendenti(clientSocket);
    }

    private void aggiungiBenzinaCase(String info, Socket clientSocket) throws SQLException {
        Meccanico m = new Meccanico();
        m.aggiuntaBenzina(info,clientSocket);
    }

    private void acquistaKartCase(String dati, Socket clientSocket) throws SQLException {
        Socio s = new Socio(null, null, null, null, null, null);
        s.compraKart(dati, clientSocket);
    }

    private void manutenzioneCase(String info, Socket clientSocket) throws SQLException {
        Meccanico m = new Meccanico();
        m.aggiornamentoManutenzione(info, clientSocket);
    }

    private void mostraPezziCase(Socket clientSocket) throws SQLException {
        Concessionaria c = new Concessionaria();
        c.mostraPezzo(clientSocket);
    }

    private void mostraGaraCase(String idGara, Socket clientSocket) throws SQLException {
        Classifica c = new Classifica();
        query = "SELECT * FROM caciokart.classifica WHERE idGara = '" + idGara + "'";
        c.classificaCompleta(query, clientSocket);
    }

}