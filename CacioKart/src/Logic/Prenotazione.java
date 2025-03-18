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

    public void prenotazione(String cf, String tipologia, LocalDate dataGara, LocalTime fasciaOraria, Socket clientSocket) {
        //cf dataGara fasciaOraria
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
                SELECT = "SELECT dip FROM caciokart.dipendente WHERE dip = '" + cf + "'";
                result.clear();
                result = db.executeReturnQuery(SELECT);
                INSERT_ITERATOR[0] = Query.PRENOTAZIONE_GENERICA_INSERIMENTO.getQuery(idPrenotazione, dataGara, fasciaOraria, tipologia, costo);

                if (result.get(0).get("dip").equals(cf)) { //se il cf è nei dipendenti, allora non associamo alla prenotazione nessun cf
                    INSERT_ITERATOR[1] = Query.PRENOTAZIONE_LIBERA_INSERIMENTO.getQuery(idPrenotazione, "NULL", dataO);

                } else {
                    INSERT_ITERATOR[1] = Query.PRENOTAZIONE_LIBERA_INSERIMENTO.getQuery(idPrenotazione, cf, dataO);

                }


                for (String gara : INSERT_ITERATOR) {
                    queryIndicator = db.executeUpdateQuery(gara);
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
}



