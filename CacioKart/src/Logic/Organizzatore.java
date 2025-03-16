package Logic;

import Enums.Query;
import Objects.Team;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Organizzatore {
    private DBConnector db;
    private PHPResponseHandler responder;
    private String SELECT;
    private List<Map<String, Object>> soci;
    private String cf;
    private String nome;
    private String cognome;
    private StringBuilder listaUtenti;
    private String[] INSERT_ITERATOR;
    private String queryIndicator;
    private StringBuilder campionato;
    private String idCampionato;
    private List<Map<String, Object>> result;
    private List<Map<String, Object>> gare;
    private String idGara;
    private String ora;
    private StringBuilder listaGare;
    private String INSERT;
    private StringBuilder prenotazione;
    private String idP;

    public Organizzatore() {

    }

    public void creaGara(){

    };

    public void creaTeam(Team t, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        System.out.println("Nome del team: " + t.getNome());
        System.out.println("Colore del team: " + t.getColore());
        System.out.println("Nome dei soci: " + t.getSoci());

        INSERT_ITERATOR = new String[3];
        INSERT_ITERATOR[0] = Query.CREA_TEAM_TABELLA_TEAM.getQuery(t.getNome(), t.getColore());
        INSERT_ITERATOR[1] = Query.CREA_TEAM_TABELLA_APPARTENENZA.getQuery(t.getSoci()[0].getCf(), t.getNome());
        INSERT_ITERATOR[2] = Query.CREA_TEAM_TABELLA_APPARTENENZA.getQuery(t.getSoci()[1].getCf(), t.getNome());

        for (String team : INSERT_ITERATOR) {
            queryIndicator = db.executeUpdateQuery(team);

            if (queryIndicator == "0") {
                responder.sendResponse(clientSocket, queryIndicator);
                return;
            }
        }

        responder.sendResponse(clientSocket, queryIndicator);
    }

    public void creaCampionato() {

    }

    //solo gara campionato??
    public void inserimentoGara() {

    }

    public void mostraSociInserimento(String query, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        soci = db.executeReturnQuery(query);
        listaUtenti = new StringBuilder();

        if (soci != null) {
            for (Map<String, Object> row : soci) {
                cf = row.get("socio").toString();
                nome = row.get("nome").toString();
                cognome = row.get("cognome").toString();
                listaUtenti.append(cf).append(" ").append(nome).append(" ").append(cognome).append("\n");
            }
            listaUtenti.append("end");
            responder.sendResponse(clientSocket, listaUtenti.toString());

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void mostraCampionato(String query, Socket clientSocket) {
        responder = new PHPResponseHandler();
        db = new DBConnector();
        result = db.executeReturnQuery(query);
        campionato = new StringBuilder();

        if (result != null) {
            for (Map<String, Object> row : result) {
                idCampionato = row.get("idCampionato").toString();
                campionato.append(idCampionato).append("\n");
            }
            campionato.append("end");
            responder.sendResponse(clientSocket, campionato.toString());

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void mostraGareInserimento(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = Query.MOSTRA_GARE_INSERIMENTO.getQuery();

        gare = db.executeReturnQuery(SELECT);
        listaGare = new StringBuilder();

        if (gare == null) {
            responder.sendResponse(clientSocket, "end");
        }
        else {
            for(Map<String, Object> row : gare) {
                idGara = row.get("idGara").toString();
                ora = row.get("ora").toString();
                listaGare.append(idGara).append(" ").append(ora).append("\n");
            }
            listaGare.append("end");

            responder.sendResponse(clientSocket, listaGare.toString());
        }
    }

    public void aggiungiGaraPartecipa(String idGara, String idCamp, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        INSERT = Query.AGGIUNGI_GARA_PARTECIPA_CAMPIONATO.getQuery(idGara, idCamp);
        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryIndicator);

    }

    public void mostraPren(String query, Socket clientSocket) {
        responder = new PHPResponseHandler();
        db = new DBConnector();
        result = db.executeReturnQuery(query);
        prenotazione = new StringBuilder();

        if (result != null) {
            for (Map<String, Object> row : result) {
                idP = row.get("idP").toString();
                prenotazione.append(idP).append("\n");
            }
            prenotazione.append("end");
            responder.sendResponse(clientSocket, prenotazione.toString());

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void aggiornaPrenota(String idP, String socio, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT = Query.INSERIMENTO_SOCIO_GARA.getQuery(idP, socio, LocalDate.now());
        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryIndicator);
    }
}

