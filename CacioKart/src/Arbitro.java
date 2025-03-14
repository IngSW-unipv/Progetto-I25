import java.net.Socket;
import java.time.LocalTime;

public class Arbitro {

    public Arbitro() {

    }

    public void inserimentoPenalita(Socio s, String idGara, LocalTime penalita, Socket clientSocket) {
        DBConnector db = new DBConnector();
        PHPResponseHandler responder = new PHPResponseHandler();
        String UPDATE = Query.INSERIMENTO_PENALITA_ARBITRO.getQuery(penalita, idGara, s.getCf());

        int queryIndicator = db.executeUpdateQuery(UPDATE);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }
}
