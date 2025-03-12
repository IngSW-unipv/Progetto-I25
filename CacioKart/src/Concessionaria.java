import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Concessionaria {
    //List<Kart> kart;
    //List<Pezzo> pezzi;
    private DBConnector db;
    private PHPResponseHandler responder;
    private String INSERT, SELECT, idProdotto, tipol, quantita, prezzo, ultimoProdotto;
    private int queryResult, queryIndicator;
    private List<Map<String, Object>> maxIDProdotto, pezzi;
    private StringBuilder listaPezzi = new StringBuilder();

    public Concessionaria(/*List<Pezzo> pezzi, List<Kart> kart*/) {
        //this.pezzi = pezzi;
        //this.kart = kart;
    }

    //Override metodi Iinventario

    public void venditaKart() {

    }

    ;

    /**
     * Metodo per inserire i kart nella concessionaria.
     * In ingresso prendiamo i dati del kart e il socket per spedire la risposta.
     * Creo un oggetto di tipo Kart in modo da poterlo manipolare con i vari get,
     * dopodiché utilizzo il metodo di DBConnector per inserire il nuovo kart e
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
        INSERT = "INSERT INTO kart (targa, cilindrata, serbatoio) VALUES('" +
                nuovoKart.getTarga() + "', '" +
                nuovoKart.getCilindrata() + "', '" +
                nuovoKart.getSerbatoio() + "')";
        db.executeUpdateQuery(INSERT);

        SELECT = "SELECT MAX(idProdotto) FROM concessionaria";
        maxIDProdotto = db.executeReturnQuery(SELECT);
        //System.out.println(maxIDProdotto.get(0));

        //Logica per rimuovere tutti i caratteri tranne i numeri
        ultimoProdotto = maxIDProdotto.get(0).toString().replaceAll("\\D", "");

        if (ultimoProdotto == "") {
            idProdotto = "1";

        } else {
            idProdotto = String.valueOf((Integer.parseInt(ultimoProdotto) + 1));

        }
        //e impostare concessionario tipol
        INSERT = "INSERT INTO concessionaria (idProdotto, tipol, quantita, prezzo) VALUES('" +
                idProdotto + "', '" +
                nuovoKart.getTarga() + "', '" +
                1 + "', '" +
                prezzo + "')";

        queryResult = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(queryResult));

    }

    ;

    //mostra tutti i pezzi della concessionaria tranne quelli che iniziano per KRT
    public void mostraPezzo(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = "SELECT * FROM concessionaria WHERE tipol NOT LIKE 'KRT%'";
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

    public void inserimentoPezzo(String idPezzo, String quantita, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT = "UPDATE caciokart.concessionaria SET quantita = quantita + " + quantita + " WHERE idProdotto = '" + idPezzo + "'";
        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }
}
