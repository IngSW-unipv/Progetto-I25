package Logic;

import Enums.Query;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Prenotazione  {
    private String SELECT;
    private String[] INSERT_ITERATOR;
    private DBConnector db;
    private PHPResponseHandler responder;
    private String queryIndicator;
    private List<Map<String, Object>> result;
    private String idPrenotazione, prenotazioniConcorrenti;
    private double costo;
    private TableMaker maker;

    /** Metodo per gestire le prenotazioni.
     * Si effettua un controllo di prenotazioni concorrenti per vedere se
     * ci sono spazi in quella data e in quella fascia oraria.
     * In caso ci sia spazio, si identifica l'id massimo della prenotazione + 1 e
     * si passa al controllo della tipologia.
     * In caso la prenotazione sia per una gara libera si deve controllare se la persona
     * che sta prenotando sia un socio oppure un dipendente.
     * In caso sia un dipendente il cf di prenotazione viene impostato a NULL.
     * Dopodiché vengono aggiornate le tabelle prenota e prenotazione con i relativi dati.
     *
     * In caso la prenotazione sia per una gara secca si modifica solo la tabella prenotazione,
     * perché le prenotazioni secche le può fare soltanto l'organizzatore.
     *
     * @param cf Il cf dell'utente che sta prenotando
     * @param tipologia Libera o Secca
     * @param dataGara Il giorno in cui si è prenotata la gara
     * @param fasciaOraria La fascia oraria (11:00-12:00) in cui si è prenotata la gara
     * @param clientSocket Il socket di risposta
     */
    public void prenotazioneGara(String cf, String tipologia, LocalDate dataGara, LocalTime fasciaOraria, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        LocalDate dataO = LocalDate.now();

        // Controllo disponibilità posti
        SELECT = Query.PRENOTAZIONE_CONTEGGIO_POSTI_RIMASTI.getQuery(dataGara, fasciaOraria);
        result = db.executeReturnQuery(SELECT);
        prenotazioniConcorrenti = result.get(0).get("concurrent").toString();

        if (prenotazioniConcorrenti.equals("20")) {
            System.out.println("Nessun posto disponibile\n!");
            responder.sendResponse(clientSocket, "0");
            return;
        }

        // Genera nuovo ID prenotazione
        SELECT = Query.PRENOTAZIONE_MAX_ID.getQuery();
        result.clear();
        result = db.executeReturnQuery(SELECT);
        idPrenotazione = result.get(0).get("max").toString();
        idPrenotazione = (!idPrenotazione.equals("0")) ? String.valueOf(Integer.parseInt(idPrenotazione) + 1) : "1";

        // Applica la strategia corretta
        PrenotazioneStrategy strategy = PrenotazioneStrategyFactory.getStrategy(tipologia);

        if (strategy == null) {
            responder.sendResponse(clientSocket, "0");
            return;
        }

        strategy.eseguiPrenotazione(idPrenotazione, cf, dataGara, fasciaOraria, dataO, db, responder, clientSocket);
        }

    public void mostraPrenotazioni(VisualizzazionePrenotazioniStrategy strategy, Socket clientSocket) {
        PHPResponseHandler responder = new PHPResponseHandler();
        DBConnector db = new DBConnector();
        TableMaker maker = new TableMaker();

        List<Map<String, Object>> result = strategy.eseguiQuery(db);

        if (result != null && !result.isEmpty()) {
            String risposta = maker.stringTableMaker(result, strategy.getColonneDaMostrare());
            responder.sendResponse(clientSocket, risposta);
        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }
}

