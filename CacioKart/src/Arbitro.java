import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalTime;

public class Arbitro {
    private DBConnector db;
    private PHPResponseHandler responder;
    private String UPDATE;
    private int queryIndicator;
    private LocalTime nuovoTempo;
    private int minutiTotali;

    public Arbitro() {

    }

    public void inserimentoPenalita(String cf, String idGara, LocalTime penalità, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        UPDATE = "UPDATE caciokart.classifica SET tempTot = ADDTIME(tempTot, '" + penalità + "') WHERE idGara = '" + idGara + "' AND socio  = '" + cf + "'";

        queryIndicator = db.executeUpdateQuery(UPDATE);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }
}
