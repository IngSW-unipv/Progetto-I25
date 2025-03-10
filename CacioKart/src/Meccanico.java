import java.net.Socket;
import java.sql.SQLException;
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

    private int queryIndicator;
    private String ultimaManutenzione;

    public Meccanico() {

    }

    //aggiunta kart noleggio??
    //interfaccia rimozione kart
    //Override metodi Iinventario

    public void aggiornamentoManutenzione(String targa, String text, double prezzo, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        String idM;
        //kart = db.executeReturnQuery(SELECT);

        SELECT = "SELECT COALESCE(MAX(idM), '0') FROM manutenzione";
        idM = db.executeReturnQuery(SELECT).toString().replaceAll("\\D", "");
        //System.out.println("Ecco l'id massimo delle manutenzioni: " + idM);
        idM = String.valueOf(Integer.parseInt(idM) + 1);

        System.out.println("Dati dell'INSERT: " + idM + " " + text + " " + prezzo + " " + LocalDate.now() + " " + targa);

        INSERT = "INSERT INTO manutenzione (idM, tipoInt, costo, dataM, targa) VALUES ('" +
                idM + "', '" +
                text + "', " +
                prezzo + ", '" +
                LocalDate.now() + "', '" +
                targa + "')";

        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));

    }

    public void aggiuntaKart(String targa, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        DELETE = "DELETE FROM caciokart.concessionaria WHERE tipol = '" + targa + "'";
        queryIndicator = db.executeUpdateQuery(DELETE);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }

    public void aggiuntaBenzina(String info, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        UPDATE = "UPDATE caciokart.kart SET kart.serbatoio = '20' WHERE kart.targa = '" + info + "'";
        queryIndicator = db.executeUpdateQuery(UPDATE);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }

    ;

    public void mostraKart(String query, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        kart = db.executeReturnQuery(query);
        listaKart = new StringBuilder();

        if (kart != null) {
            for (Map<String, Object> row : kart) {
                targa = row.get("targa").toString();
                cilindrata = row.get("cilindrata").toString();
                serbatoio = row.get("serbatoio").toString();
                listaKart.append(targa).append(" ").append(cilindrata).append(" ").append(serbatoio).append("\n");
            }
            listaKart.append("end");
            responder.sendResponse(clientSocket, listaKart.toString());

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void mostraKartManutenzione(String query, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        kart = db.executeReturnQuery(query);
        listaKart = new StringBuilder();

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

    public void rimozioneKart(String targaDaRimuovere, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        DELETE = "DELETE FROM caciokart.kart WHERE targa = '" + targaDaRimuovere + "'";
        queryIndicator = db.executeUpdateQuery(DELETE);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }

}
