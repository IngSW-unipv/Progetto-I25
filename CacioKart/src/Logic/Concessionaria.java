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

    /**
     * Metodo per inserire i kart nella concessionaria.
     * In ingresso prendiamo i dati del kart e il socket per spedire la risposta.
     * Creo un oggetto di tipo Kart in modo da poterlo manipolare con i vari get,
     * dopodiché utilizzo il metodo di DBConnector per inserire il nuovo kart e
     * il metodo di ResponseHandler per mandare una risposta.
     *
     * @param nuovoKart
     * @param prezzo
     * @param clientSocket
     */
    public void inserimentoKart(Kart nuovoKart, int prezzo, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        //devo aggiungere il kart in kart
        INSERT = Query.INSERIMENTO_KART_CONCESSIONARIA_TABELLA_KART.getQuery(nuovoKart.getTarga(), nuovoKart.getCilindrata(), nuovoKart.getSerbatoio());
        db.executeUpdateQuery(INSERT);

        SELECT = Query.INSERIMENTO_KART_CONCESSIONARIA_MAX_ID.getQuery();
        result = db.executeReturnQuery(SELECT);
        //System.out.println(maxIDProdotto.get(0));

        //Logica per rimuovere tutti i caratteri tranne i numeri
        ultimoProdotto = result.get(0).get("idProdotto").toString();
        //ultimoProdotto = result.get(0).toString().replaceAll("\\D", "");

        if (ultimoProdotto == "") {
            idProdotto = "1";

        } else {
            idProdotto = String.valueOf((Integer.parseInt(ultimoProdotto) + 1));

        }

        INSERT = Query.INSERIMENTO_KART_CONCESSIONARIA_TABELLA_CONCESSIONARIA.getQuery(idProdotto, nuovoKart.getTarga(), 1, prezzo);
        queryResult = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryResult);

    }

    //mostra tutti i pezzi della concessionaria tranne quelli che iniziano per KRT
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

    public void inserimentoPezzo(Pezzo p, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT = Query.INSERIMENTO_NUOVI_PEZZI.getQuery(p.getQuantita(), p.getIdProdotto());
        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryIndicator);
    }
}
