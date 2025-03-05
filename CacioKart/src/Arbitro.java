import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Arbitro {
    private DBConnector db;
    private PHPResponseHandler responder;
    private String SELECT;
    private String idGara;
    private String tipol;
    private String quantita;
    private String prezzo;
    private List<Map<String, Object>> gare;
    private StringBuilder listaPezzi = new StringBuilder();

    public Arbitro() {

    }

    //metodo per inviare a schermo tutti i possibili idGara
    public void mostraGare(Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT =  "SELECT DISTINCT idGara FROM caciokart.classifica ORDER BY idGara";
        gare = db.executeReturnQuery(SELECT);

        if(gare != null) {
            for(Map<String, Object> row : gare) {
                idGara = row.get("idGara").toString();
                listaPezzi.append(idGara).append("\n");
            }
            listaPezzi.append("end");
            responder.sendResponse(clientSocket, listaPezzi.toString());

        }else{
            responder.sendResponse(clientSocket, "end");
        }
    }
}
