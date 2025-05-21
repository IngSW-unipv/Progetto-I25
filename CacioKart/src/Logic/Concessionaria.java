package Logic;

import Enums.Query;
import Objects.Kart;
import Objects.Pezzo;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Concessionaria {

    private DBConnector db;
    private PHPResponseHandler responder;
    private String INSERT, SELECT, idProdotto, ultimoProdotto;
    private String queryResult, queryIndicator;
    private List<Map<String, Object>> result;

    public Concessionaria() {

    }

    /** Metodo per inserire i kart nella concessionaria.
     * Il nuovo kart viene inserito nella tabella kart.
     * Dopodiché si prende l'id massimo dei prodotti presenti
     * nella concessionaria e lo si utilizza per calcolare l'id prodotto
     * del kart nella concessionaria, per poi eseguire la query di inserimento
     * nella tabella concessionaria.
     * Il metodo non controlla targhe doppie perché non ci aspettiamo che
     * ci siano targhe che appaiono due volte.
     *
     * @param nuovoKart I dati del nuovo kart da inserire
     * @param prezzo Il prezzo del nuovo kart
     * @param clientSocket Il socket di risposta
     */
    public void inserimentoKart(Kart nuovoKart, int prezzo, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT = Query.INSERIMENTO_KART_CONCESSIONARIA_TABELLA_KART.getQuery(nuovoKart.getTarga(), nuovoKart.getCilindrata(), nuovoKart.getSerbatoio());
        db.executeUpdateQuery(INSERT);

        SELECT = Query.INSERIMENTO_KART_CONCESSIONARIA_MAX_ID.getQuery();
        result = db.executeReturnQuery(SELECT);

        ultimoProdotto = result.get(0).get("max").toString();

        if (ultimoProdotto == "") {
            idProdotto = "1";

        } else {
            idProdotto = String.valueOf((Integer.parseInt(ultimoProdotto) + 1));

        }

        INSERT = Query.INSERIMENTO_KART_CONCESSIONARIA_TABELLA_CONCESSIONARIA.getQuery(idProdotto, nuovoKart.getTarga(), 1, prezzo);
        queryResult = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryResult);

    }

    /** Metodo per mostrare tutti i pezzi presenti nella concessionaria.
     * Si fa una query per tutte le tuple presenti nella tabella concessionaria
     * il cui nome non inizia con KRT (targa standard dei kart) per poi creare
     * un table con tutti i risultati.
     *
     * @param clientSocket Il socket di risposta
     */
    public void mostraPezzo(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = Query.MOSTRA_PEZZI_CONCESSIONARIA.getQuery();
        result = db.executeReturnQuery(SELECT);

        if (result != null) {
            TableMaker maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idProdotto", "tipol", "quantita", "prezzo"));

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    /** Metodo per inserire i pezzi nuovi nella concessionaria.
     * Il metodo esegue la query di inserimento e risponde al client.
     *
     * @param p Il pezzo da inserire
     * @param clientSocket Il socket di risposta
     */
    public void inserimentoPezzo(Pezzo p, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT = Query.INSERIMENTO_NUOVI_PEZZI.getQuery(p.getQuantita(), p.getIdProdotto());
        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryIndicator);
    }
}
