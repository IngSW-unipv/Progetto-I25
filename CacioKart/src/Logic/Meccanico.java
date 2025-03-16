package Logic;

import Enums.Query;
import Objects.Kart;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Meccanico {
    private List<Map<String, Object>> kart;
    private DBConnector db;
    private PHPResponseHandler responder;
    private String SELECT, DELETE, UPDATE, INSERT;
    private StringBuilder listaKart;

    //DEVE DIVENTARE UN KART
    private String targa;
    private String cilindrata;
    private String serbatoio;

    private String queryIndicator;
    private String ultimaManutenzione;

    public Meccanico() {

    }

    public void aggiornamentoManutenzione(String targa, String text, double prezzo, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        String idM;
        //kart = db.executeReturnQuery(SELECT);

        SELECT = Query.AGGIORNAMENTO_MANUTENZIONE_MAX_ID.getQuery();
        idM = db.executeReturnQuery(SELECT).toString().replaceAll("\\D", "");
        //System.out.println("Ecco l'id massimo delle manutenzioni: " + idM);

        idM = String.valueOf(Integer.parseInt(idM) + 1);

        //System.out.println("Dati dell'INSERT: " + idM + " " + text + " " + prezzo + " " + LocalDate.now() + " " + targa);

        INSERT = Query.AGGIORNAMENTO_MANUTENZIONE_TABELLA_MANUTENZIONE.getQuery(idM, text, prezzo, LocalDate.now(), targa);

        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryIndicator);

    }

    public void aggiuntaKart(String targa, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        DELETE = Query.INSERIMENTO_KART_MECCANICO.getQuery(targa);
        queryIndicator = db.executeUpdateQuery(DELETE);
        responder.sendResponse(clientSocket, queryIndicator);
    }

    public void aggiuntaBenzina(Kart k, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        UPDATE = Query.AGGIUNTA_BENZINA_MECCANICO.getQuery(k.getTarga());
        queryIndicator = db.executeUpdateQuery(UPDATE);
        responder.sendResponse(clientSocket, queryIndicator);
    }

    public void mostraKart(String query, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        kart = db.executeReturnQuery(query);

        if (kart != null) {
            TableMaker maker = new TableMaker();
            responder.sendResponse(clientSocket,  maker.stringTableMaker(kart, "targa", "cilindrata", "serbatoio"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void mostraKartManutenzione(String query, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        kart = db.executeReturnQuery(query);
        listaKart = new StringBuilder();

        //Oggetto in entrata non nullo di tipo List<Map<String, Object>>
        //Per ogni oggetto nella mappa prendo la chiave e il valore associato
        //Il nome e la quantità di chiavi cambia ogni volta
        //Creo una super stringa e la ritorno



        if (kart != null) {
            for (Map<String, Object> row : kart) {
                targa = row.get("targa").toString();
                ultimaManutenzione = row.get("giorniDallaManutenzione").toString();
                listaKart.append(targa).append(" ").append(ultimaManutenzione).append("\n");
            }
            listaKart.append("end");
            responder.sendResponse(clientSocket, listaKart.toString());

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void rimozioneKart(Kart k, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        DELETE = Query.RIMUOVI_KART_MECCANICO.getQuery(k.getTarga());
        queryIndicator = db.executeUpdateQuery(DELETE);
        responder.sendResponse(clientSocket, queryIndicator);
    }

}
