import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Meccanico implements Iinventario {
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

    public Meccanico() {

    }

    //aggiunta kart noleggio??
    //interfaccia rimozione kart
    //Override metodi Iinventario

    public void aggiornamentoManutenzione(){

    };

    public void aggiuntaBenzina(){

    };

    public void mostraKart(Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = "SELECT * FROM caciokart.kart";
        kart = db.executeReturnQuery(SELECT);

        if(!kart.isEmpty()) {
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
