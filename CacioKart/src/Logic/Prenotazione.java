package Logic;

import Enums.Query;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Prenotazione {
    private String SELECT, INSERT_ITERATOR[];
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
        prenotazioniConcorrenti = result.get(0).toString().replaceAll("\\D", "");

        if (prenotazioniConcorrenti.equals("20")) {
            System.out.println("Nessun posto disponibile\n!");
            responder.sendResponse(clientSocket, "0");
            return;
        }
        SELECT = Query.PRENOTAZIONE_MAX_ID.getQuery();
        result = db.executeReturnQuery(SELECT);
        idPrenotazione = result.toString().replaceAll("\\D", "");

        if (!idPrenotazione.equals("0")) {
            idPrenotazione = String.valueOf(Integer.parseInt(idPrenotazione) + 1);

        } else {
            idPrenotazione = "1"; // Se non ci sono prenotazioni, partiamo da 1
        }

        costo = 15;
        INSERT_ITERATOR = new String[2];
        switch (tipologia) {

            case "libera":

                SELECT = "SELECT dip FROM caciokart.dipendente"; //WHERE dip = cf
                result = db.executeReturnQuery(SELECT);

                if (result.contains(cf)) { //se il cf è nei dipendenti, allora non associamo alla prenotazione nessun cf

                    INSERT_ITERATOR[0] = "INSERT INTO prenotazione (idP, dataG , fasciaO, tipologia, costo) VALUES('" +
                            idPrenotazione + "', '" +
                            dataGara + "', '" +
                            fasciaOraria + "', '" +
                            tipologia + "', '" +
                            costo + "')";

                    INSERT_ITERATOR[1] = "INSERT INTO prenota (idP, socio,data) VALUES ('" +
                            idPrenotazione + "', 'NULL', '" +
                            dataO + "')";

                } else {

                    INSERT_ITERATOR[0] = "INSERT INTO prenotazione (idP, dataG , fasciaO, tipologia, costo) VALUES('" +
                            idPrenotazione + "', '" +
                            dataGara + "', '" +
                            fasciaOraria + "', '" +
                            tipologia + "', '" +
                            costo + "')";

                    INSERT_ITERATOR[1] = "INSERT INTO prenota (idP, socio,data) VALUES ('" +
                            idPrenotazione + "', '" +
                            cf + "', '" +
                            dataO + "')";
                }


                for (String gara : INSERT_ITERATOR) {
                    queryIndicator = db.executeUpdateQuery(gara);
                    if (queryIndicator == "0") {
                        responder.sendResponse(clientSocket, queryIndicator);
                        return;
                    }
                }

                break;

            case "secca":

                INSERT_ITERATOR[0] = "INSERT INTO prenotazione (idP, dataG , fasciaO, tipologia, costo) VALUES('"
                        + idPrenotazione + "', '" + dataGara + "', '" + fasciaOraria + "', '"
                        + tipologia + "', '" + costo + "')";
                queryIndicator = db.executeUpdateQuery(INSERT_ITERATOR[0]);
                if (queryIndicator == "0") {
                    responder.sendResponse(clientSocket, queryIndicator);
                    return;
                }
                break;


            default:
                responder.sendResponse(clientSocket, "0");
                break;
        }

    }
}



