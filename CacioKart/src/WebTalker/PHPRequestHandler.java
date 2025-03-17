package WebTalker;

import Enums.Query;
import Enums.TipoComandi;
import Logic.*;
import Objects.*;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PHPRequestHandler {
    private BufferedReader in;
    private String comando, info, tipologia;
    private String[] messaggio;
    private TipoComandi tipo;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private String query;

    public PHPRequestHandler() {

    }

    /**
     * La classe riceve stringhe composte in questo modo: *parola singola* *dati*
     * La prima parola indica la richiesta del client da eseguire.
     * La seconda parte del messaggio cambia in base alla richiesta da eseguire,
     * potrebbe contenere 0 o più informazioni a seconda dei dati necessari.
     */
    public void handleRequests(Socket clientSocket) {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //Creo un oggetto per leggere i messaggi in arrivo
            messaggio = in.readLine().split(" ", 2); //Divido il messaggio in due: il comando e i dati

            comando = messaggio[0]; //Il comando da gestire sarà la prima parte del messaggio
            if (messaggio.length == 2) { //Controllo se il messaggio contiene più di una parola
                info = messaggio[1]; //Le informazioni relative al resto del comando comporranno la seconda parte del messaggio
                System.out.println("Messaggio ricevuto: " + messaggio[0] + " " + messaggio[1]);
            } else {
                System.out.println("Messaggio ricevuto: " + messaggio[0]); //In alternativa esistono dei comandi che non necessitano di ulteriori informazioni
            }

            tipo = TipoComandi.requestedCommand(comando); //Controllo a quale ENUM corrisponde il comando


            switch (tipo) { //Switch per gestire i comandi in ingresso

                case LOGIN:
                    loginCase(info, clientSocket);
                    break;

                case REGISTRAZIONE:
                    registrazioneSocioCase(info, clientSocket);
                    break;

                case PRENOTAZIONE_LIBERA:
                    tipologia = "libera";
                    prenotazioneCase(tipologia, info, clientSocket);
                    break;

                case PRENOTAZIONE_SECCA:
                    tipologia = "secca";
                    prenotazioneCase(tipologia, info, clientSocket);
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
                    eliminaDipendenteCase(info, clientSocket);
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
                    classificaUtente(info, clientSocket);
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
                    mostraGaraPenalitaCase(info, clientSocket);
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
                    creazioneTeamCase(info, clientSocket);
                    break;

                case RICHIESTA_CAMPIONATO:
                    mostraCampionato(clientSocket);
                    break;

                case SELEZIONE_GARE_CAMPIONATO:
                    selezionaGaraCampionato(clientSocket);
                    break;

                case AGGIUNGI_GARE_CAMPIONATO:
                    aggiungiGaraCampionato(info, clientSocket);
                    break;

                case RICHIESTA_GARA_SECCA:
                    mostraPrenotazione(clientSocket);
                    break;

                case MOSTRA_SOCI:
                    selezionaSocio(clientSocket);
                    break;

                case INSERIMENTO_SOCI_GARA:
                    aggiornamentoPrenota(info, clientSocket);
                    break;
                case MOSTRA_BILANCIO:
                    mostraBilancioCase(clientSocket);
                    break;

                default:
                    break;
            }

            in.close();

        } catch (IOException e) {
            System.out.println("Errore nella lettura: " + e.getMessage());
        }

    }

    /**
     * Metodo per gestire la logica di login.
     * I dati in ingresso sono il CF (chiave nel db) e la password.
     *
     * @param dati CF, pwd.
     * @param clientSocket Socket per la risposta. È presente in tutti i case.
     */
    private void loginCase(String dati, Socket clientSocket) {
        String[] loginData = dati.split(" "); //Divido i dati in ingresso
        Persona utente = new Persona(); //Utilizzo un oggetto di tipo persona per manipolare i dati con i setter
        utente.setCf(loginData[0]);
        utente.setPassword(loginData[1]);
        utente.login(clientSocket);
    }

    /**
     * Metodo per gestire la logica delle classifiche per l'arbitro.
     * L'arbitro è interessato a vedere tutte le classifiche delle
     * gare effettuate, senza duplicati.
     *
     * @param clientSocket
     */
    private void classificaArbitro(Socket clientSocket) {
        Classifica c = new Classifica();
        c.classificaArbitro(clientSocket);
    }

    /**
     * Metodo per gestire la logica della classifica generale
     * visibile nella prima pagina del sito.
     * Seleziono dal db tutte le informazioni di una singola classifica
     * mettendola in Join con i soci per ottenere le informazioni di un singolo
     * socio.
     *
     * @param clientSocket
     * @throws SQLException
     */
    private void classificaGenerale(Socket clientSocket) {
        Classifica c = new Classifica();
        c.classificaCompleta(clientSocket);
    }

    /**
     * Metodo per gestire la classifica visibile da un singolo utente.
     * In ingresso il cf dell'utente ci permette di fare una query
     * di tutte le classifiche che appartengono solo a lui.
     *
     * @param cfPilota
     * @param clientSocket
     * @throws SQLException
     */
    private void classificaUtente(String cfPilota, Socket clientSocket) {
        Classifica c = new Classifica();
        Socio s = new Socio();
        s.setCf(cfPilota);
        c.classificaUtente(s, clientSocket);
    }

    /**
     * Metodo per gestire la logica di registrazione.
     * Prendo il messaggio e lo divido nelle singole informazioni richieste.
     * Utilizzo il DateTimeFormatter per far combaciare la data in ingresso con
     * il tipo Date presente nel database.
     *
     * @param dati
     * @param clientSocket
     */
    private void registrazioneSocioCase(String dati, Socket clientSocket) {
        String[] socio = dati.split(" ");
        LocalDate dataNascita = LocalDate.parse(socio[2], dateFormatter);
        Socio nuovoUtente = new Socio(socio[0], socio[1], dataNascita, socio[3], socio[4], socio[5]);
        nuovoUtente.registrazione(clientSocket);
    }

    //riceve i dati della prenotazione dal sito
    //va in socio e crea una prenotazione
    //risponde al sito tramite phpresponsehandler
    //usa Logic.DBConnector e PHPResponsehandle

    private void prenotazioneCase(String tipologia, String messaggio, Socket clientSocket) {

        //data fasciaOraria username
        String[] info = messaggio.split(" ");
        LocalDate dataG;
        LocalTime orarioI;
        Persona pers = new Persona();
        dataG = LocalDate.parse(info[0], dateFormatter);
        String[] orari = info[1].split("-");
        orarioI = LocalTime.parse(orari[1], timeFormatter);

        switch (tipologia) {

            case "libera":
                pers.setCf(info[2]);
                break;

            case "secca":
                pers.setCf(null);
                break;

            default:
                break;
        }

        Prenotazione p = new Prenotazione();
        p.prenotazione(pers.getCf(), tipologia, dataG, orarioI, clientSocket);
    }

    /**
     * Metodo di aggiunta kart.
     * Pressoché identico al metodo di registrazione.
     *
     * @param dati
     * @param clientSocket
     * @throws SQLException
     */
    private void aggiuntaKartCaseConcessionaria(String dati, Socket clientSocket) {
        String[] info = dati.split(" "); //Passare a kart
        Kart k = new Kart(info[0], Integer.parseInt(info[1]), 20);
        int prezzo = Integer.parseInt(info[2]);
        Concessionaria c = new Concessionaria();
        c.inserimentoKart(k, prezzo, clientSocket);

    }

    /**
     * Metodo per aggiungere i kart dalla vendita del concessionario
     * al noleggio del kartdromo a cura del meccanico.
     * La targa in ingresso ci permette di selezionare un kart specifico da rimuovere
     * dalla tabella concessionario.
     *
     * @param targa
     * @param clientSocket
     * @throws SQLException
     */
    private void aggiuntaKartCaseMeccanico(String targa, Socket clientSocket) {
        Meccanico m = new Meccanico();
        m.aggiuntaKart(targa, clientSocket);

    }

    /**
     * Metodo per mostrare i kart disponibili a passare dalla vendita al noleggio.
     * Non richiede parametri oltre al socket per spedire la risposta.
     *
     * @param clientSocket
     * @throws SQLException
     */
    private void mostraAggiuntaKartCase(Socket clientSocket) {
        Meccanico m = new Meccanico();
        query = Query.MOSTRA_AGGIUNTA_KART_MECCANICO_SOCIO.getQuery();
        m.mostraKart(query, clientSocket);
    }

    /**
     * Metodo per rimuovere i kart dal kartodromo.
     * Il client manda una targa da rimuovere dal db, il metodo
     * la utilizza per la query e risponde al client.
     *
     * @param targa
     * @param clientSocket
     * @throws SQLException
     */
    private void rimozioneKartCase(String targa, Socket clientSocket) {
        Meccanico m = new Meccanico();
        Kart k = new Kart();
        k.setTarga(targa);
        m.rimozioneKart(k, clientSocket);
    }

    /**
     * Metodo per mostrare i kart disponibili alla rimozione.
     * Non necessita di dati in ingresso, la query è sempre quella.
     *
     * @param clientSocket
     * @throws SQLException
     */
    private void mostraRimuoviKartCase(Socket clientSocket) {
        Meccanico m = new Meccanico();
        query = Query.MOSTRA_RIMUOVI_KART_MECCANICO.getQuery();
        m.mostraKart(query, clientSocket);
    }

    /**
     * Metodo per mostrare i kart appartenenti ai clienti e
     * disponibili al noleggio per modificarne la manutenzione.
     * Non necessita di dati in ingresso, la query è sempre quella.
     *
     * @param clientSocket
     * @throws SQLException
     */
    private void mostraManutenzioneKartCase(Socket clientSocket) {
        Meccanico m = new Meccanico();
        query = Query.MOSTRA_KART_MANUTENZIONE.getQuery();
        m.mostraKartManutenzione(query, clientSocket);
    }

    /**
     * Metodo per aggiungere dipendenti.
     * Dopo aver formattato le date tramite i formatter, le passo al costruttore
     * di Objects.Dipendente per utilizzare i metodi di get e set nel metodo
     * di aggiuntaDipendenti.
     *
     * @param dati
     * @param clientSocket
     * @throws SQLException
     */
    private void aggiungiDipendenteCase(String dati, Socket clientSocket) {
        String[] dipendente = dati.split(" ");
        LocalDate dataN = LocalDate.parse(dipendente[2], dateFormatter);
        LocalTime oreL = LocalTime.parse(dipendente[8], timeFormatter);
        Proprietario p = new Proprietario();
        Dipendente d = new Dipendente(dipendente[0], dipendente[1], dataN, dipendente[3], dipendente[4], dipendente[5], Double.parseDouble(dipendente[6]), dipendente[7], oreL);
        p.aggiuntaDipendente(d, clientSocket);
    }

    /**
     * Metodo di rimozione dipendenti.
     * Pressoché identico al metodo di rimozione kart.
     *
     * @param dati
     * @param clientSocket
     * @throws SQLException
     */
    private void eliminaDipendenteCase(String dati, Socket clientSocket) {
        Proprietario p = new Proprietario();
        Dipendente d = new Dipendente();
        d.setCf(dati);
        p.rimozioneDipendenti(d, clientSocket);
    }

    /**
     * Metodo per mostrare tutti i dipendenti.
     * Pressoché identico al metodo per mostrare i kart.
     *
     * @param clientSocket
     * @throws SQLException
     */
    private void mostraDipendentiCase(Socket clientSocket) {
        Proprietario p = new Proprietario();
        p.mostraDipendenti(clientSocket);
    }

    private void aggiungiBenzinaCase(String targa, Socket clientSocket) {
        Meccanico m = new Meccanico();
        Kart k = new Kart();
        k.setTarga(targa);
        m.aggiuntaBenzina(k, clientSocket);
    }


    //DA QUA IN POI
    private void acquistaKartCase(String dati, Socket clientSocket) {
        Socio s = new Socio();
        s.compraKart(dati, clientSocket);
    }

    private void manutenzioneCase(String info, Socket clientSocket) {
        Meccanico m = new Meccanico();
        String[] mex = info.split(" ", 3);
        String targa = mex[0];
        String text = mex[2];
        double prezzo = Double.parseDouble(mex[1]);
        m.aggiornamentoManutenzione(targa, text, prezzo, clientSocket);
    }

    private void mostraPezziCase(Socket clientSocket) {
        Concessionaria c = new Concessionaria();
        c.mostraPezzo(clientSocket);
    }

    private void mostraGaraPenalitaCase(String idGara, Socket clientSocket) {
        Classifica c = new Classifica();
        c.classificaPenalita(idGara, clientSocket);
    }

    private void aggiungiPenalitaCase(String messaggio, Socket clientSocket) {
        //socio idgara tempo
        String[] info = messaggio.split(" ");
        Socio s = new Socio();
        s.setCf(info[0]);
        String idGara = info[1];
        LocalTime penalita = LocalTime.parse(info[2], DateTimeFormatter.ofPattern("HH:mm:ss"));
        //Metodo in arbitro per inserire le penalità
        Arbitro a = new Arbitro();
        a.inserimentoPenalita(s, idGara, penalita, clientSocket);
    }

    private void aggiungiPezziCase(String messaggio, Socket clientSocket) {
        //idPezzo quantità
        String[] info = messaggio.split(" ");
        Pezzo p = new Pezzo();
        p.setIdProdotto(info[0]);
        p.setQuantita(Integer.parseInt(info[1]));
        Concessionaria c = new Concessionaria();
        c.inserimentoPezzo(p, clientSocket);
    }

    private void mostraSociCampionatoCase(Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        query = Query.MOSTRA_SOCI_INSERIMENTO_CAMPIONATO.getQuery();
        o.mostraSociInserimento(query, clientSocket);
    }

    private void acquistaPezziCase(String messaggio, Socket clientSocket) {
        //cf pezzo
        String[] info = messaggio.split(" ");
        Socio s = new Socio();
        s.setCf(info[0]);
        Pezzo p = new Pezzo();
        p.setIdProdotto(info[1]);
        s.acquistaPezzi(p, clientSocket);
    }

    private void mostraCampionato(Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        query = Query.MOSTRA_CAMPIONATI.getQuery();
        o.mostraCampionato(query, clientSocket);
    }

    private void selezionaGaraCampionato(Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        o.mostraGareInserimento(clientSocket);
    }

    private void creazioneTeamCase(String messaggio, Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        //nome colore cf1 cf2
        String[] team = messaggio.split(" ");
        Team t = new Team(team[0], team[1], team[2], team[3]);
        o.creaTeam(t, clientSocket);
    }

    private void aggiungiGaraCampionato(String messaggio, Socket clientSocket) {
        String[] info = messaggio.split(" ");
        String idGara = info[0];
        String idCamp = info[1];

        Organizzatore o = new Organizzatore();
        o.aggiungiGaraPartecipa(idGara, idCamp, clientSocket);
    }

    private void mostraPrenotazione(Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        query = Query.MOSTRA_PRENOTAZIONE.getQuery();
        o.mostraPrenotazioni(query, clientSocket);
    }

    private void selezionaSocio(Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        query = Query.SELEZIONA_SOCIO.getQuery();
        o.mostraSociInserimento(query, clientSocket);
    }

    private void aggiornamentoPrenota(String messaggio, Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        String[] info = messaggio.split(" ");
        String idP = info[0];
        String socio = info[1];
        o.aggiornaPrenota(idP, socio, clientSocket);
    }

    private void mostraBilancioCase(Socket clientSocket) {
        Proprietario p = new Proprietario();
        p.bilancio(clientSocket);
    }
}