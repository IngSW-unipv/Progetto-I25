package Command;

import DAO.implementazioni.OrganizzatoreDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class MostraSociPrenotazioniCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        OrganizzatoreDAO dao = new OrganizzatoreDAO(DBConnector.getInstance());
        List<Map<String, Object>> soci = dao.mostraSociAggiuntaPrenotazione();

        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> row : soci) {
            sb.append(row.get("socio")).append(" ")
                    .append(row.get("nome")).append(" ")
                    .append(row.get("cognome")).append("\n");
        }
        sb.append("end\n");
        new PHPResponseHandler().sendResponse(clientSocket, sb.toString());
    }
}
