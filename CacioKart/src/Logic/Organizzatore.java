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

    /** Il metodo per inserire un team nuovo nel db.
     * Servono 3 query per inserire un nuovo team nel db:
     * una per la tabella team e due per la tabella appartenenza,
     * una per ogni membro.
     *
     * @param t Il team da inserire nel db
     * @param clientSocket Il socket di risposta
     */
    public void creaTeam(Team t, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        //System.out.println("Nome del team: " + t.getNome());
        //System.out.println("Colore del team: " + t.getColore());
        //System.out.println("Nome dei soci: " + t.getSoci());

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

    /** Metodo per mostrare all'organizzatore i soci disponibili a essere associati
     * a una determinata prenotazione.
     * Dopo aver effettuato la query, si utilizza tablemaker per ottenere la stringa da
     * spedire al client.
     *
     * @param query La query da eseguire
     * @param clientSocket Il socket di risposta
     */
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

    /** Metodo per mostrare al client tutti gli id campionato
     * attualmente presenti nel db.
     * La risposta è ottenuta con una query di lettura e
     * formattata tramite tablemaker.
     *
     * @param clientSocket Il socket di risposta
     */
    public void mostraCampionato(Socket clientSocket) {
        responder = new PHPResponseHandler();
        db = new DBConnector();
        SELECT = Query.MOSTRA_CAMPIONATI.getQuery();
        result = db.executeReturnQuery(SELECT);

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idCampionato"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    /** Metodo per mostrare al client tutte le
     * gare secche ancora non appartenenti a un campionato.
     *
     * @param clientSocket Il socket di risposta
     */
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

    /** Metodo per associare una gara a un determinato campionato.
     * Avendo gli id della gara e del campionato si può effettuare
     * una query che modifica la tabella "partecipa", utilizzata
     * per indicare quale gara appartiene a quale campionato.
     *
     * @param idGara L'id della gara
     * @param idCamp L'id del campionato
     * @param clientSocket Il socket di risposta
     */
    public void aggiungiGaraPartecipa(String idGara, String idCamp, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        INSERT = Query.AGGIUNGI_GARA_PARTECIPA_CAMPIONATO.getQuery(idGara, idCamp);
        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryIndicator);

    }



    /** Metodo per associare un socio a una prenotazione.
     * Dopo aver effettuato la query di modifica del db, rispondo al client
     * con l'esito dell'operazione.
     *
     * @param idP Id della prenotazione
     * @param s Il socio da associare
     * @param clientSocket Il socket di risposta
     */
    public void aggiornaPrenota(String idP, Socio s, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT = Query.INSERIMENTO_SOCIO_GARA.getQuery(idP, s.getCf(), LocalDate.now());
        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryIndicator);
    }
}

