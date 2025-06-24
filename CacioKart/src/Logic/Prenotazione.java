package Logic;

import Enums.Query;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Prenotazione {
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
    public void prenotazione(String cf, String tipologia, LocalDate dataGara, LocalTime fasciaOraria, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        LocalDate dataO = LocalDate.now();

        SELECT = Query.PRENOTAZIONE_CONTEGGIO_POSTI_RIMASTI.getQuery(dataGara, fasciaOraria);
        result = db.executeReturnQuery(SELECT);
        prenotazioniConcorrenti = result.get(0).get("concurrent").toString();

        if (prenotazioniConcorrenti.equals("20")) {
            System.out.println("Nessun posto disponibile\n!");
            responder.sendResponse(clientSocket, "0");
            return;
        }

        SELECT = Query.PRENOTAZIONE_MAX_ID.getQuery();
        result.clear();
        result = db.executeReturnQuery(SELECT);
        idPrenotazione = result.get(0).get("max").toString();

        if (!idPrenotazione.equals("0")) {
            idPrenotazione = String.valueOf(Integer.parseInt(idPrenotazione) + 1);

        } else {
            idPrenotazione = "1"; // Se non ci sono prenotazioni, partiamo da 1
        }

        INSERT_ITERATOR = new String[2];

        switch (tipologia) {

            case "libera":

                costo = 15;
                SELECT = Query.SELEZIONA_DIPENDENTE_PRENOTAZIONE.getQuery(cf);
                result.clear();

                result = db.executeReturnQuery(SELECT);
                INSERT_ITERATOR[0] = Query.PRENOTAZIONE_GENERICA_INSERIMENTO.getQuery(idPrenotazione, dataGara, fasciaOraria, tipologia, costo);

                if (!result.isEmpty() && result.get(0).get("dip").equals(cf)) { //se il cf è nei dipendenti, allora non associamo alla prenotazione nessun cf
                    INSERT_ITERATOR[1] = Query.PRENOTAZIONE_LIBERA_INSERIMENTO_NULL.getQuery(idPrenotazione, dataO);

                } else {
                    INSERT_ITERATOR[1] = Query.PRENOTAZIONE_LIBERA_INSERIMENTO.getQuery(idPrenotazione, cf, dataO);

                }

                for (String prenotazione : INSERT_ITERATOR) {
                    queryIndicator = db.executeUpdateQuery(prenotazione);
                    if (queryIndicator == "0") {
                        responder.sendResponse(clientSocket, queryIndicator);
                        return;
                    }
                }
                responder.sendResponse(clientSocket, queryIndicator);
                break;

            case "secca":

                costo = 20;
                String INSERT = Query.PRENOTAZIONE_GENERICA_INSERIMENTO.getQuery(idPrenotazione, dataGara, fasciaOraria, tipologia, costo);

                queryIndicator = db.executeUpdateQuery(INSERT);
                responder.sendResponse(clientSocket, queryIndicator);
                break;

            default:
                responder.sendResponse(clientSocket, "0");
                break;
        }

    }

    /** Metodo per mostrare all'organizzatore tutte le
     * prenotazioni in cui è possibile inserire i soci.
     *
     * @param clientSocket Il socket di risposta
     */

    //Una pagina vuole solo idP come risposta, l'altra vuole più roba
    public void mostraPrenotazioniOrganizzatore(Socket clientSocket) {
        responder = new PHPResponseHandler();
        db = new DBConnector();
        SELECT = Query.MOSTRA_PRENOTAZIONI_ORGANIZZATORE.getQuery();
        result = db.executeReturnQuery(SELECT);

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idP"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    /** Metodo per mostrare a un determinato socio
     * le prenotazioni a cui è associato.
     *
     * @param s Il socio in esame
     * @param clientSocket Il socket di risposta
     */
    public void mostraPrenotazioniSocio(Socio s,Socket clientSocket) {
        responder = new PHPResponseHandler();
        db = new DBConnector();
        SELECT = Query.MOSTRA_PRENOTAZIONI_SOCIO.getQuery(s.getCf());
        result = db.executeReturnQuery(SELECT);

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "dataG", "fasciaO", "tipologia"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }
}

