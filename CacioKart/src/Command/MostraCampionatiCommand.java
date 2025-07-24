package Command;

import DAO.OrganizzatoreDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;
import java.net.Socket;
import java.util.List;

public class MostraCampionatiCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        OrganizzatoreDAO dao = new OrganizzatoreDAO(DBConnector.getInstance());
        List<String> campionati = dao.mostraCampionati();

        StringBuilder sb = new StringBuilder();
        for (String idCampionato : campionati) {
            sb.append(idCampionato).append("\n");
        }
        sb.append("end\n");
        new PHPResponseHandler().sendResponse(clientSocket, sb.toString());
    }
}
