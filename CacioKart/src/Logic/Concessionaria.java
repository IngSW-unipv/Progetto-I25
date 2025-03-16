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
    private String INSERT, SELECT, idProdotto, tipol, quantita, prezzo, ultimoProdotto;
    private String queryResult, queryIndicator;
    private List<Map<String, Object>> maxIDProdotto, pezzi;
    private StringBuilder listaPezzi = new StringBuilder();

    public Concessionaria() {

    }

    /**
     * Metodo per inserire i kart nella concessionaria.
     * In ingresso prendiamo i dati del kart e il socket per spedire la risposta.
     * Creo un oggetto di tipo Objects.Kart in modo da poterlo manipolare con i vari get,
     * dopodiché utilizzo il metodo di Logic.DBConnector per inserire il nuovo kart e
     * il metodo di ResponseHandler per mandare una risposta
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
        maxIDProdotto = db.executeReturnQuery(SELECT);
        //System.out.println(maxIDProdotto.get(0));

        //Logica per rimuovere tutti i caratteri tranne i numeri
        ultimoProdotto = maxIDProdotto.get(0).toString().replaceAll("\\D", "");

        if (ultimoProdotto == "") {
            idProdotto = "1";

        } else {
            idProdotto = String.valueOf((Integer.parseInt(ultimoProdotto) + 1));

        }

        INSERT = Query.INSERIMENTO_KART_CONCESSIONARIA_TABELLA_CONCESSIONARIA.getQuery(idProdotto, nuovoKart.getTarga(), 1, prezzo);
        queryResult = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryResult);

    }

    ;

    //mostra tutti i pezzi della concessionaria tranne quelli che iniziano per KRT
    public void mostraPezzo(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = Query.MOSTRA_PEZZI_CONCESSIONARIA.getQuery();
        pezzi = db.executeReturnQuery(SELECT);

        if (pezzi != null) {
            for (Map<String, Object> row : pezzi) {
                idProdotto = row.get("idProdotto").toString();
                tipol = row.get("tipol").toString();
                quantita = row.get("quantita").toString();
                prezzo = row.get("prezzo").toString();
                listaPezzi.append(idProdotto).append(" ").append(tipol).append(" ").append(quantita).append(" ").append(prezzo).append("\n");
            }
            listaPezzi.append("end");
            responder.sendResponse(clientSocket, listaPezzi.toString());

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
