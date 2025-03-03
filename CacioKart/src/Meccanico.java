import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Meccanico{
    private List<Map<String, Object>> kart;
    private DBConnector db;
    private PHPResponseHandler responder;
    private String SELECT;
    private StringBuilder listaKart = new StringBuilder();
    private String targa;
    private String cilindrata;
    private String serbatoio;
    private String DELETE;
    private int queryIndicator;
    private String UPDATE;

    public Meccanico() {

    }

    //aggiunta kart noleggio??
    //interfaccia rimozione kart
    //Override metodi Iinventario

    public void aggiornamentoManutenzione(String dati, Socket clientSocket){
        //targa
    };

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

        UPDATE = "UPDATE kart SET kart.serbatoio = '20' WHERE kart.targa = '" + info + "'";
        queryIndicator = db.executeUpdateQuery(UPDATE);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    };

    public void mostraKart(String query,Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        kart = db.executeReturnQuery(query);

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

    public void rimozioneKart(String targaDaRimuovere, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        DELETE = "DELETE FROM caciokart.kart WHERE targa = '" + targaDaRimuovere + "'";
        queryIndicator = db.executeUpdateQuery(DELETE);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }

}
