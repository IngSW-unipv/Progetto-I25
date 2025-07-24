// Command/MostraGaraCommand.java
package Command;

import DAO.ClassificaDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ClassificaArbitroCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        ClassificaDAO dao = new ClassificaDAO(DBConnector.getInstance());
        List<String> gare = dao.mostraGareSvolte(); // Usa la query CLASSIFICA_ARBITRO

        StringBuilder sb = new StringBuilder();
        for (String idGara : gare) {
            sb.append(idGara).append("\n");
        }
        new PHPResponseHandler().sendResponse(clientSocket, sb.toString());
    }
}


