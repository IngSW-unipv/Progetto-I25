import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Meccanico{
    private List<Map<String, Object>> kart;
    private DBConnector db;
    private PHPResponseHandler responder;
    private String SELECT;
    private StringBuilder listaKart;
    private String targa;
    private String cilindrata;
    private String serbatoio;
    private String data;
    private String DELETE;
    private int queryIndicator;
    private String UPDATE;
    private String ultimaManutenzione;

    public Meccanico() {

    }

    //aggiunta kart noleggio??
    //interfaccia rimozione kart
    //Override metodi Iinventario

    public void aggiornamentoManutenzione(String query,String targa,String text,double prezzo, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        boolean trovata = false;
        String elenco;
        String idM;
        kart = db.executeReturnQuery(query);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
        for (Map<String, Object> row : kart) {
            elenco = row.get("targa").toString();
            if (row.containsKey("targa") && row.get("targa") != null) {
                if (elenco.equals(targa)) {
                    trovata = true;

                }
            }
        }
        if (trovata) {
            SELECT = "SELECT MAX(idM) FROM PRENOTAZIONE";
            kart = db.executeReturnQuery(SELECT);
            idM = kart.toString().replaceAll("\\D", "");
            idM = String.valueOf(Integer.parseInt(idM) + 1);
            SELECT = "INSERT INTO mauntenzione (idM, tipoInt , costo, dataM) VALUES('" +
                    idM + "', '" +
                    text +"', '" +
                    prezzo +"', '" +
                    LocalDate.now() + "', '" +
                     "')";
            queryIndicator = db.executeUpdateQuery(SELECT);
            responder.sendResponse(clientSocket,Integer.toString(queryIndicator));
            SELECT = "INSERT INTO eseguita (idM, targa) VALUES('" +
                    idM + "', '" +
                    targa +"', '" +
                    "')";
            System.out.println("manutenziobe avvenuta con successo\n");
        }else{
            System.out.println("manutenzio non avvenuta\n");
        }

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
    };

    public void mostraKart(String query, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        kart = db.executeReturnQuery(query);
        listaKart = new StringBuilder();

        if(kart != null) {
            for(Map<String, Object> row : kart) {
                targa = row.get("targa").toString();
                cilindrata = row.get("cilindrata").toString();
                serbatoio = row.get("serbatoio").toString();
                listaKart.append(targa).append(" ").append(cilindrata).append(" ").append(serbatoio).append("\n");
            }
            listaKart.append("end");
            responder.sendResponse(clientSocket, listaKart.toString());

        }else{
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void mostraKartmanutenzione(String query, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        kart = db.executeReturnQuery(query);
        listaKart = new StringBuilder();

        if(kart != null) {
            for(Map<String, Object> row : kart) {
                targa = row.get("targa").toString();
                ultimaManutenzione = row.get("giorniDallaManutenzione").toString();
                listaKart.append(targa).append(" ").append(ultimaManutenzione).append("\n");
            }
            listaKart.append("end");
            responder.sendResponse(clientSocket, listaKart.toString());

        }else{
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
