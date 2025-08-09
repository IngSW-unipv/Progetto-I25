package Command;

import DAO.implementazioni.ClassificaDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ClassificaUtenteCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        // Esempio comando: "richiestaClassUsr Andrea"
        String[] parti = in.split(" ");
        if (parti.length < 2) {
            new PHPResponseHandler().sendResponse(clientSocket, "Parametro utente mancante.\nend\n");
            return;
        }
        String nome = parti[1];

        ClassificaDAO dao = new ClassificaDAO(DBConnector.getInstance());
        List<Map<String, Object>> classifica = dao.getClassificaUtente(nome);

        StringBuilder sb = new StringBuilder();
        if (classifica == null || classifica.isEmpty()) {
            sb.append("Nessuna classifica trovata.\nend\n");
        } else {
            for (Map<String, Object> row : classifica) {
                sb.append(row.get("idGara")).append(" ")
                        .append(row.get("targa")).append(" ")
                        .append(row.get("bGiro")).append(" ")
                        .append(row.get("tempTot")).append("\n");
            }
            sb.append("end\n");
        }
        new PHPResponseHandler().sendResponse(clientSocket, sb.toString());
    }
}
