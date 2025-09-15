package Command;

import DAO.implementazioni.OrganizzatoreDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class MostraPrenotazioniCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        OrganizzatoreDAO dao = new OrganizzatoreDAO(DBConnector.getInstance());
        List<Map<String, Object>> prenotazioni = dao.mostraPrenotazioniOrganizzatore();

        StringBuilder sb = new StringBuilder();
        if (prenotazioni == null || prenotazioni.isEmpty()) {
            sb.append("Nessuna prenotazione trovata.\nend\n");
        } else {
            for (Map<String, Object> row : prenotazioni) {
                sb.append(row.get("idP")).append("\n");
            }
            sb.append("end\n");
        }
        new PHPResponseHandler().sendResponse(clientSocket, sb.toString());
    }
}
