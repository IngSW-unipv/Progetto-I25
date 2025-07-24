// Command/MostraGaraCommand.java
package Command;

import DAO.ClassificaDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class MostraGareSvolteCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        String[] parti = in.split(" ");
        if (parti.length < 2) {
            new PHPResponseHandler().sendResponse(clientSocket, "Parametro mancante.");
            return;
        }
        String idGara = parti[1];
        ClassificaDAO dao = new ClassificaDAO(DBConnector.getInstance());
        List<Map<String, Object>> classifica = dao.mostraClassificaPenalita(idGara);

        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> row : classifica) {
            sb.append(row.get("idGara")).append(" ")    // <-- aggiungi questa colonna!
                    .append(row.get("socio")).append(" ")
                    .append(row.get("targa")).append(" ")
                    .append(row.get("bGiro")).append(" ")
                    .append(row.get("tempTot")).append("\n");
        }
        sb.append("end\n");
        new PHPResponseHandler().sendResponse(clientSocket, sb.toString());

    }
}
