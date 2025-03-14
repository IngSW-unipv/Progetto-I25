import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalTime;

public class Arbitro {
    private DBConnector db;
    private PHPResponseHandler responder;
    private String UPDATE;
    private int queryIndicator;

    public Arbitro() {

    }

    public void inserimentoPenalita(Socio s, String idGara, LocalTime penalità, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        UPDATE = Query.INSERIMENTO_PENALITA_ARBITRO.getQuery(penalità, idGara, s.getCf());

        queryIndicator = db.executeUpdateQuery(UPDATE);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }
}
