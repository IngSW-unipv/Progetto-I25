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

            //comando informazioni

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
                    prenotazioneCase(tipologia,info,clientSocket);
                    break;

                case PRENOTAZIONE_SECCA:
                    tipologia = "secca";
                    prenotazioneCase(tipologia,info,clientSocket);
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

                case ACQUISTA_PEZZI:
                    acquistaPezziCase(info, clientSocket);
                    break;

                case MOSTRA_GARA:
                    mostraGaraCase(info,clientSocket);
                    break;

                case AGGIUNGI_PENALITA:
                    aggiungiPenalitaCase(info, clientSocket);
                    break;

                case AGGIUNGI_PEZZI:
                    aggiungiPezziCase(info, clientSocket);
                    break;

                case MOSTRA_SOCI_CAMPIONATO:
                    mostraSociCampionatoCase(clientSocket);
                    break;

                case CREAZIONE_TEAM:
                    Organizzatore o = new Organizzatore();
                    //nome colore cf1 cf2
                    String[] team = info.split(" ");
                    //Usare oggetto di tipo team
                    Team t = new Team(team[0], team[1], team[2], team[3]);
                    o.creaTeam(t, clientSocket);
                    break;

                case RICHIESTA_CAMPIONATO:
                    mostraCampionato(clientSocket);
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
        utente.setCf(loginData[0]);
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

    private void prenotazioneCase(String tipologia, String messaggio, Socket clientSocket) throws SQLException {

        //data fasciaOraria username
        String[] info = messaggio.split(" ");
        LocalDate dataG = LocalDate.parse(info[0], dateFormatter);
        String[] orari = info[1].split("-");
        LocalTime orarioI = LocalTime.parse(orari[0], timeFormatter);
        String cf = info[2];

        Prenotazione p = new Prenotazione();
        p.prenotazione(cf,tipologia, dataG, orarioI,clientSocket);
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
        query =  "SELECT " +
                "    k.*, " +
                "    COALESCE( " +
                "        CASE " +
                "            WHEN MAX(m.dataM) IS NULL THEN 'MAI_FATTA' " +
                "            ELSE CAST(DATEDIFF(CURRENT_DATE, MAX(m.dataM)) AS CHAR) " +
                "        END, 'MAI_FATTA' " +
                "    ) AS giorniDallaManutenzione, " +
                "    MAX(m.dataM) AS ultimaManutenzione " +
                "FROM caciokart.kart k " +
                "LEFT JOIN caciokart.manutenzione m ON k.targa = m.targa " +
                "GROUP BY k.targa;";

        m.mostraKartManutenzione(query,clientSocket);
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
        String[] dipendente = dati.split(" ");
        LocalDate dataN = LocalDate.parse(dipendente[2], dateFormatter);
        LocalTime oreL = LocalTime.parse(dipendente[8], timeFormatter);
        Proprietario p = new Proprietario();
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
        Socio s = new Socio();
        s.compraKart(dati, clientSocket);
    }

    private void manutenzioneCase(String info, Socket clientSocket) throws SQLException {
        Meccanico m = new Meccanico();
        String[] mex = info.split(" ", 3);
        String targa = mex[0];
        String text = mex[2];
        double prezzo = Double.parseDouble(mex[1]);
        LocalDate today = LocalDate.now();
        /* query = "SELECT " +
                "    m.idM, " +
                "    e.targa, " +
                "    m.tipoInt, " +
                "    m.costo, " +
                "    MAX(m.dataM) AS dataManutenzione " +
                "FROM caciokart.manutenzione m " +
                "RIGHT JOIN caciokart.kart e ON m.targa = e.targa " +
                "WHERE m.dataM IS NULL OR DATEDIFF('" + today + "', m.dataM) > 180 " +
                "GROUP BY m.idM, e.targa, m.tipoInt, m.costo;";*/
        m.aggiornamentoManutenzione(targa,text,prezzo, clientSocket);
    }

    private void mostraPezziCase(Socket clientSocket) throws SQLException {
        Concessionaria c = new Concessionaria();
        c.mostraPezzo(clientSocket);
    }

    private void mostraGaraCase(String idGara, Socket clientSocket) throws SQLException {
        Classifica c = new Classifica();
        query = "SELECT * FROM caciokart.classifica WHERE idGara = '" + idGara + "'";
        c.classificaPenalità(query, clientSocket);
    }

    private void aggiungiPenalitaCase(String messaggio, Socket clientSocket) throws SQLException {
        //socio idgara tempo
        String[] info = messaggio.split(" ");
        String cf = info[0];
        String idGara = info[1];
        LocalTime penalità = LocalTime.parse(info[2], DateTimeFormatter.ofPattern("HH:mm:ss"));
        //Metodo in arbitro per inserire le penalità
        Arbitro a = new Arbitro();
        a.inserimentoPenalita(cf, idGara, penalità, clientSocket);
    }

    private void aggiungiPezziCase(String messaggio, Socket clientSocket) throws SQLException {
        //idPezzo quantità
        String[] info = messaggio.split(" ");
        String idPezzo = info[0];
        String quantità = info[1];
        Concessionaria c = new Concessionaria();
        c.inserimentoPezzo(idPezzo, quantità, clientSocket);
    }

    private void mostraSociCampionatoCase(Socket clientSocket) throws SQLException {
        Organizzatore o = new Organizzatore();
        o.mostraSociInserimento(clientSocket);
    }

    private void acquistaPezziCase(String info, Socket clientSocket) throws SQLException {
        Socio s = new Socio();
        s.acquistaPezzi(info,clientSocket);
    }
    private void mostraCampionato(Socket clientSocket) throws SQLException {
        Organizzatore o = new Organizzatore();
        query =  "SELECT idCampionato FROM caciokart.campionato";
        o.mostraCamp(query, clientSocket);
    }

}