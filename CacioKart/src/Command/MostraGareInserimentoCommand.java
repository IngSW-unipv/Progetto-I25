package Command;

import DAO.OrganizzatoreDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class MostraGareInserimentoCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        OrganizzatoreDAO dao = new OrganizzatoreDAO(DBConnector.getInstance());
        List<Map<String, Object>> gare = dao.mostraGareInserimento();

        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> row : gare) {
            sb.append(row.get("idGara")).append(" ")
                    .append(row.get("ora")).append("\n");
        }
        sb.append("end\n");
        new PHPResponseHandler().sendResponse(clientSocket, sb.toString());
    }
}
