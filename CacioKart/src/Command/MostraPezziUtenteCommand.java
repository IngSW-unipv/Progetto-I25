package Command;

import DAO.implementazioni.PezzoDAO;
import Logic.Socio;
import Logic.TableMaker;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class MostraPezziUtenteCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PezzoDAO dao = new PezzoDAO();
        PHPResponseHandler responder = new PHPResponseHandler();

        try {
            List<Map<String, Object>> result = dao.mostraPezziDisponibili();

            if (result != null && !result.isEmpty()) {
                TableMaker maker = new TableMaker();
                String tabella = maker.stringTableMaker(result, "idProdotto", "tipol", "quantita", "prezzo");
                responder.sendResponse(clientSocket, tabella);
            } else {
                responder.sendResponse(clientSocket, "end");
            }

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore nel recupero dei pezzi: " + e.getMessage());
        }
    }
}
