package WebTalker;

import Enums.Query;
import Enums.TipoComandi;
import Logic.*;
import Objects.*;

import java.io.*;
import java.net.*;
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

    /**La classe riceve stringhe composte in questo modo: *parola singola* *dati*
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
                    classificaGeneraleCase(clientSocket);
                    break;

                case CLASSIFICA_UTENTE:
                    classificaUtenteCase(info, clientSocket);
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
                    mostraCampionatoCase(clientSocket);
                    break;

                case SELEZIONE_GARE_CAMPIONATO:
                    selezionaGaraCampionatoCase(clientSocket);
                    break;

                case AGGIUNGI_GARE_CAMPIONATO:
                    aggiungiGaraCampionatoCase(info, clientSocket);
                    break;

                case RICHIESTA_GARA_SECCA:
                    mostraPrenotazioniOrganizzatoreCase(clientSocket);
                    break;

                case MOSTRA_SOCI:
                    selezionaSocioCase(clientSocket);
                    break;

                case INSERIMENTO_SOCI_GARA:
                    aggiornamentoPrenotaCase(info, clientSocket);
                    break;

                case MOSTRA_BILANCIO:
                    mostraBilancioCase(clientSocket);
                    break;

                case MOSTRA_PRENOTAZIONI_UTENTE:
                    mostraPrenotazioniSocioCase(info, clientSocket);
                    break;

                case MOSTRA_KART_UTENTE:
                    mostraKartUtenteCase(info, clientSocket);
                    break;

                case MOSTRA_PEZZI_POSSEDUTI_UTENTE:
                    mostraPezziUtenteCase(info, clientSocket);
                    break;

                default:
                    break;
            }

            in.close();

        } catch (IOException e) {
            System.out.println("Errore nella lettura: " + e.getMessage());
        }

    }

    /** Metodo per gestire la logica di login.
     * I dati in ingresso vengono suddivisi tramite il metodo split.
     * Si utilizza poi il costruttore vuoto di persona per istanziare solo
     * gli attributi che ci servono.
     *
     * @param dati CF, pwd
     * @param clientSocket Socket per la risposta
     */
    private void loginCase(String dati, Socket clientSocket) {
        String[] loginData = dati.split(" "); //Divido i dati in ingresso
        Persona utente = new Persona(); //Utilizzo un oggetto di tipo persona per manipolare i dati con i setter
        utente.setCf(loginData[0]);
        utente.setPassword(loginData[1]);
        utente.login(clientSocket);
    }

    /** Metodo per gestire la logica delle classifiche per l'arbitro.
     *
     * @param clientSocket Socket per la risposta
     */
    private void classificaArbitro(Socket clientSocket) {
        Classifica c = new Classifica();
        c.classificaArbitro(clientSocket);
    }

    /** Metodo per gestire la logica della classifica generale
     * visibile nella prima pagina del sito.
     *
     * @param clientSocket Socket per la risposta
     */
    private void classificaGeneraleCase(Socket clientSocket) {
        Classifica c = new Classifica();
        c.classificaCompleta(clientSocket);
    }

    /** Metodo per gestire le classifiche visibili da un singolo utente.
     *
     * @param cfPilota Il cf del pilota di cui vogliamo sapere le classifiche
     * @param clientSocket Socket per la risposta
     */
    private void classificaUtenteCase(String cfPilota, Socket clientSocket) {
        Classifica c = new Classifica();
        Socio s = new Socio();
        s.setCf(cfPilota);
        c.classificaUtente(s, clientSocket);
    }

    /** Metodo per gestire la logica di registrazione.
     * Utilizzo il metodo split per suddividere le informazioni in ingresso,
     * dopodiché creo un Socio e chiamo il metodo per la registrazione.
     *
     * @param dati In ordine: nome, cognome, data di nascita, cf, mail, password
     * @param clientSocket Socket per la risposta
     */
    private void registrazioneSocioCase(String dati, Socket clientSocket) {
        String[] socio = dati.split(" ");
        LocalDate dataNascita = LocalDate.parse(socio[2], dateFormatter);
        Socio nuovoUtente = new Socio(socio[0], socio[1], dataNascita, socio[3], socio[4], socio[5]);
        nuovoUtente.registrazione(clientSocket);
    }

    /** Metodo per gestire la logica di prenotazione.
     * Tramite gli split si prende la stringa dati e la si suddivide nelle informazioni
     * necessarie per la prenotazione: il cf, la fascia oraria e il giorno.
     *
     * @param tipologia Gara secca o Gara Libera
     * @param dati In ordine: data della gara, fascia oraria, cf.
     * @param clientSocket Il socket di risposta
     */
    private void prenotazioneCase(String tipologia, String dati, Socket clientSocket) {

        String[] info = dati.split(" ");
        LocalDate dataG;
        LocalTime orarioI;
        Persona pers = new Persona();
        dataG = LocalDate.parse(info[0], dateFormatter);
        String[] orari = info[1].split("-");
        orarioI = LocalTime.parse(orari[0], timeFormatter);
        pers.setCf(info[2]);

        Prenotazione p = new Prenotazione();
        p.prenotazione(pers.getCf(), tipologia, dataG, orarioI, clientSocket);

    }

    /** Metodo di aggiunta kart.
     * Suddivido i dati in ingresso tramite il metodo split,
     * dopodiché creo un Kart e associo i dati in input.
     * Creo una Concessionaria e utilizzo il metodo per inserire
     * il nuovo kart nel db.
     *
     * @param dati In ordine: targa, cilindrata, prezzo
     * @param clientSocket Socket per la risposta
     */
    private void aggiuntaKartCaseConcessionaria(String dati, Socket clientSocket) {
        String[] info = dati.split(" "); //Passare a kart
        Kart k = new Kart(info[0], Integer.parseInt(info[1]), 20);
        int prezzo = Integer.parseInt(info[2]);
        Concessionaria c = new Concessionaria();
        c.inserimentoKart(k, prezzo, clientSocket);

    }

    /** Metodo per aggiungere i kart dalla vendita del concessionario
     * al noleggio del kartdromo a cura del meccanico.
     * La targa in ingresso ci permette di selezionare un kart specifico da rimuovere
     * dai kart noleggiabili (tabella concessionaria).
     *
     * @param targa Il kart da aggiungere al noleggio
     * @param clientSocket Socket per la risposta
     */
    private void aggiuntaKartCaseMeccanico(String targa, Socket clientSocket) {
        Meccanico m = new Meccanico();
        Kart k = new Kart();
        k.setTarga(targa);
        m.aggiuntaKart(k, clientSocket);

    }

    /** Metodo per mostrare i kart disponibili a passare dalla vendita al noleggio.
     * La query va specificata in questo metodo perché lo stesso
     * metodo della classe meccanico viene riutilizzato.
     *
     * @param clientSocket Socket per la risposta
     */
    private void mostraAggiuntaKartCase(Socket clientSocket) {
        Meccanico m = new Meccanico();
        query = Query.MOSTRA_AGGIUNTA_KART_MECCANICO.getQuery();
        m.mostraKart(query, clientSocket);
    }

    /** Metodo per rimuovere i kart dal kartodromo.
     *
     * @param targa La targa del kart da rimuovere
     * @param clientSocket Socket per la risposta
     */
    private void rimozioneKartCase(String targa, Socket clientSocket) {
        Meccanico m = new Meccanico();
        Kart k = new Kart();
        k.setTarga(targa);
        m.rimozioneKart(k, clientSocket);
    }

    /** Metodo per mostrare i kart disponibili alla rimozione.
     * La query va specificata in questo metodo perché lo stesso
     * metodo della classe meccanico viene riutilizzato.
     *
     * @param clientSocket Socket per la risposta
     */
    private void mostraRimuoviKartCase(Socket clientSocket) {
        Meccanico m = new Meccanico();
        query = Query.MOSTRA_RIMUOVI_KART_MECCANICO.getQuery();
        m.mostraKart(query, clientSocket);
    }

    /** Metodo per mostrare i kart dei clienti e
     * del noleggio per modificarne la manutenzione.
     *
     * @param clientSocket Socket per la risposta
     */
    private void mostraManutenzioneKartCase(Socket clientSocket) {
        Meccanico m = new Meccanico();
        m.mostraKartManutenzione(clientSocket);
    }

    /** Metodo per aggiungere dipendenti.
     * Dopo aver formattato le date tramite i formatter, le passo al costruttore
     * di Dipendente per utilizzare i metodi di get e set nel metodo
     * di aggiuntaDipendenti.
     *
     * @param dati In ordine: nome, cognome, data di nascita, cf, mail, password, stipendio, ruolo, ore lavorative
     * @param clientSocket Socket per la risposta
     */
    private void aggiungiDipendenteCase(String dati, Socket clientSocket) {
        String[] dipendente = dati.split(" ");
        LocalDate dataN = LocalDate.parse(dipendente[2], dateFormatter);
        LocalTime oreL = LocalTime.parse(dipendente[8], timeFormatter);
        Proprietario p = new Proprietario();
        Dipendente d = new Dipendente(dipendente[0], dipendente[1], dataN, dipendente[3], dipendente[4], dipendente[5], Double.parseDouble(dipendente[6]), dipendente[7], oreL);
        p.aggiuntaDipendente(d, clientSocket);
    }

    /** Metodo di rimozione dipendenti.
     *
     * @param cf Il cf del dipendente da rimuovere
     * @param clientSocket Socket per la risposta
     */
    private void eliminaDipendenteCase(String cf, Socket clientSocket) {
        Proprietario p = new Proprietario();
        Dipendente d = new Dipendente();
        d.setCf(cf);
        p.rimozioneDipendenti(d, clientSocket);
    }

    /** Metodo per mostrare tutti i dipendenti.
     *
     * @param clientSocket Socket per la risposta
     */
    private void mostraDipendentiCase(Socket clientSocket) {
        Proprietario p = new Proprietario();
        p.mostraDipendenti(clientSocket);
    }

    /** Metodo per aggiungere la benzina a un kart.
     *
     * @param targa La targa del kart a cui fare il pieno
     * @param clientSocket Socket per la risposta
     */
    private void aggiungiBenzinaCase(String targa, Socket clientSocket) {
        Meccanico m = new Meccanico();
        Kart k = new Kart();
        k.setTarga(targa);
        m.aggiuntaBenzina(k, clientSocket);
    }


    /** Metodo per l'acquisto di un kart da parte dell'utente.
     * Ricevo il cf dell'utente e la targa del kart da assegnargli.
     *
     * @param dati In ordine: cf, targa del kart acquistato
     * @param clientSocket Socket per la risposta
     */
    private void acquistaKartCase(String dati, Socket clientSocket) {
        Socio s = new Socio();
        String[] kartUtente = dati.split(" ");
        s.setCf(kartUtente[0]);
        Kart k = new Kart();
        k.setTarga(kartUtente[1]);
        s.compraKart(k, clientSocket);
    }

    /** Metodo per effettuare la manutenzione di un kart.
     * Ricevo la targa del kart e la descrizione della manutenzione effettuata.
     *
     * @param dati In ordine: targa, costo della manutenzione, descrizione della manutenzione
     * @param clientSocket Socket per la risposta
     */
    private void manutenzioneCase(String dati, Socket clientSocket) {
        Meccanico m = new Meccanico();
        String[] info = dati.split(" ", 3);
        Kart k = new Kart();
        k.setTarga(info[0]);
        String text = info[2];
        double prezzo = Double.parseDouble(info[1]);
        m.aggiornamentoManutenzione(k, text, prezzo, clientSocket);
    }

    /** Metodo per mostrare i pezzi di ricambio al client.
     *
     * @param clientSocket Socket per la risposta
     */
    private void mostraPezziCase(Socket clientSocket) {
        Concessionaria c = new Concessionaria();
        c.mostraPezzo(clientSocket);
    }

    /** Metodo per mostrare i partecipanti di una gara all'arbitro per
     * inserire le penalità.
     *
     * @param idGara L'id della gara da mostrare
     * @param clientSocket Socket per la risposta
     */
    private void mostraGaraPenalitaCase(String idGara, Socket clientSocket) {
        Classifica c = new Classifica();
        c.classificaPenalita(idGara, clientSocket);
    }

    /** Metodo per inserire le penalità.
     * Dato il cf, idgara e il tempo di penalità, inserisco una penalità
     * a un determinato pilota.
     *
     * @param dati In ordine: cf del socio da penalizzare, l'id della gara che sto penalizzando,
     *             la quantità di tempo da penalizzare
     * @param clientSocket Socket per la risposta
     */
    private void aggiungiPenalitaCase(String dati, Socket clientSocket) {
        String[] info = dati.split(" ");
        Socio s = new Socio();
        s.setCf(info[0]);
        String idGara = info[1];
        LocalTime penalita = LocalTime.parse(info[2], DateTimeFormatter.ofPattern("HH:mm:ss"));
        Arbitro a = new Arbitro();
        a.inserimentoPenalita(s, idGara, penalita, clientSocket);
    }

    /** Metodo per aggiungere pezzi all'inventario della concessionaria.
     *
     * @param dati In ordine: id del prodotto da aggiungere, quantità da aggiungere
     * @param clientSocket Socket per la risposta
     */
    private void aggiungiPezziCase(String dati, Socket clientSocket) {
        String[] info = dati.split(" ");
        Pezzo p = new Pezzo();
        p.setIdProdotto(info[0]);
        p.setQuantita(Integer.parseInt(info[1]));
        Concessionaria c = new Concessionaria();
        c.inserimentoPezzo(p, clientSocket);
    }

    /** Metodo per mostrare i soci disponibili a essere inseriti all'interno
     * di un nuovo team.
     * Dato che il metodo in Organizzatore è utilizzato da più case,
     * la query è passata come parametro.
     *
     * @param clientSocket Socket per la risposta
     */
    private void mostraSociCampionatoCase(Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        query = Query.MOSTRA_SOCI_INSERIMENTO_CAMPIONATO.getQuery();
        o.mostraSociInserimento(query, clientSocket);
    }

    /** Metodo per l'acquisto pezzi da parte di un socio.
     * Preso il cf e l'id del pezzo, posso associare a un utente
     * il pezzo acquistato.
     *
     * @param dati In ordine: il cf del socio che acquista, l'id del pezzo acquistato
     * @param clientSocket Socket per la risposta
     */
    private void acquistaPezziCase(String dati, Socket clientSocket) {
        //cf pezzo
        String[] info = dati.split(" ");
        Socio s = new Socio();
        s.setCf(info[0]);
        Pezzo p = new Pezzo();
        p.setIdProdotto(info[1]);
        s.acquistaPezzi(p, clientSocket);
    }

    /** Metodo per mostrare tutti i campionati correnti all'organizzatore.
     *
     * @param clientSocket Socket per la risposta
     */
    private void mostraCampionatoCase(Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        query = Query.MOSTRA_CAMPIONATI.getQuery();
        o.mostraCampionato(clientSocket);
    }

    /** Metodo per mostrare le gare disponibili a essere associate a un campionato.
     *
     * @param clientSocket Socket per la risposta
     */
    private void selezionaGaraCampionatoCase(Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        o.mostraGareInserimento(clientSocket);
    }

    /** Metodo per l'organizzatore per creare team.
     * Viene creato un oggetto di tipo Team per gestire il tutto con i costruttori
     * e i metodi appositi.
     *
     * @param dati nome, colore, cf1, cf2.
     * @param clientSocket Socket per la risposta
     */
    private void creazioneTeamCase(String dati, Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        String[] team = dati.split(" ");
        Team t = new Team(team[0], team[1], team[2], team[3]);
        o.creaTeam(t, clientSocket);
    }

    /** Metodo per associare una determinata gara a un campionato.
     *
     * @param dati idGara, idCampionato
     * @param clientSocket Socket per la risposta
     */
    private void aggiungiGaraCampionatoCase(String dati, Socket clientSocket) {
        String[] info = dati.split(" ");
        String idGara = info[0];
        String idCamp = info[1];

        Organizzatore o = new Organizzatore();
        o.aggiungiGaraPartecipa(idGara, idCamp, clientSocket);
    }

    /**
     *
     * @param clientSocket Socket per la risposta
     */
    private void mostraPrenotazioniOrganizzatoreCase(Socket clientSocket) {
        Prenotazione p = new Prenotazione();
        p.mostraPrenotazioniOrganizzatore(clientSocket);
    }

    /**
     *
     * @param dati
     * @param clientSocket
     */
    private void mostraPrenotazioniSocioCase(String dati, Socket clientSocket) {
        Prenotazione p = new Prenotazione();
        Socio s = new Socio();
        s.setCf(dati);
        p.mostraPrenotazioniSocio(s, clientSocket);
    }

    /** Metodo per mostrare all'organizzatore i soci disponibili a essere associati
     * a una determinata prenotazione.
     * Dato che il metodo in Organizzatore è utilizzato da più case,
     * la query è passata come parametro.
     *
     * @param clientSocket Socket per la risposta
     */
    private void selezionaSocioCase(Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        query = Query.SELEZIONA_SOCIO_AGGIUNTA_PRENOTAZIONE.getQuery();
        o.mostraSociInserimento(query, clientSocket);
    }

    /** Metodo per associare un socio a una determinata prenotazione.
     *
     * @param dati IdPrenotazione, cf
     * @param clientSocket Socket per la risposta
     */
    private void aggiornamentoPrenotaCase(String dati, Socket clientSocket) {
        Organizzatore o = new Organizzatore();
        String[] info = dati.split(" ");
        String idP = info[0];
        Socio s = new Socio();
        s.setCf(info[1]);
        o.aggiornaPrenota(idP, s, clientSocket);
    }

    /** Metodo per mostrare il bilancio al proprietario.
     *
     * @param clientSocket Socket per la risposta
     */
    private void mostraBilancioCase(Socket clientSocket) {
        Proprietario p = new Proprietario();
        p.bilancio(clientSocket);
    }

    /**
     *
     * @param dati
     * @param clientSocket
     */
    private void mostraKartUtenteCase(String dati, Socket clientSocket) {
        Socio s = new Socio();
        s.setCf(dati);
        s.mostraKartUtente(clientSocket);
    }

    /**
     *
     * @param dati
     * @param clientSocket
     */
    private void mostraPezziUtenteCase(String dati, Socket clientSocket) {
        Socio s = new Socio();
        s.setCf(dati);
        s.mostraPezziUtente(clientSocket);
    }
}