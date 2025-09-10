package Command;

import DAO.implementazioni.OrganizzatoreDAO;
import Logic.DBConnector;
import Logic.TableMaker;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class SelectGareCampCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        try {
            OrganizzatoreDAO dao = new OrganizzatoreDAO(DBConnector.getInstance());
            List<Map<String, Object>> result = dao.mostraGareInserimento();
            PHPResponseHandler responder = new PHPResponseHandler();

            if (result != null) {
                TableMaker maker = new TableMaker();
                responder.sendResponse(clientSocket, maker.stringTableMaker(result, "idGara", "ora"));

            } else {
                responder.sendResponse(clientSocket, "end");
            }
        } catch (Exception e) {
            PHPResponseHandler responder = new PHPResponseHandler();
            responder.sendResponse(clientSocket, "Errore durante la selezione delle gare: " + e.getMessage());
        }
    }
}
