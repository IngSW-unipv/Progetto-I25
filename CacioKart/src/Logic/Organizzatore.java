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
    private String[] INSERT_ITERATOR;
    private String queryIndicator;
    private List<Map<String, Object>> result;
    private String INSERT;
    private TableMaker maker;

    public Organizzatore() {

    }

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

    public void mostraSociInserimento(String query, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        soci = db.executeReturnQuery(query);

        if (soci != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(soci, "socio", "nome", "cognome"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void mostraCampionato(String query, Socket clientSocket) {
        responder = new PHPResponseHandler();
        db = new DBConnector();
        result = db.executeReturnQuery(query);

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idCampionato"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void mostraGareInserimento(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = Query.MOSTRA_GARE_INSERIMENTO.getQuery();

        result = db.executeReturnQuery(SELECT);

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idGara", "ora"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void aggiungiGaraPartecipa(String idGara, String idCamp, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        INSERT = Query.AGGIUNGI_GARA_PARTECIPA_CAMPIONATO.getQuery(idGara, idCamp);
        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryIndicator);

    }

    public void mostraPrenotazioni(String query, Socket clientSocket) {
        responder = new PHPResponseHandler();
        db = new DBConnector();
        result = db.executeReturnQuery(query);

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idP"));

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

