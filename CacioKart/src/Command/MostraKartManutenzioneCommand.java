package Command;

import DAO.implementazioni.KartDAO;
import WebTalker.PHPResponseHandler;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class MostraKartManutenzioneCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        List<Map<String, Object>> lista = KartDAO.getInstance().getKartManutenzione();
        PHPResponseHandler responder = new PHPResponseHandler();

        if (lista == null || lista.isEmpty()) {
            responder.sendResponse(clientSocket, "Nessun kart trovato");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> r : lista) {
            String targa = String.valueOf(r.get("targa"));
            String ultimaManutenzione = String.valueOf(r.get("ultimaManutenzione"));
            if (ultimaManutenzione == null || ultimaManutenzione.equals("null")) {
                ultimaManutenzione = "MAI_FATTA";
            }
            sb.append(targa).append(" ").append(ultimaManutenzione).append("\n");
        }
        sb.append("end");

        System.out.println("Invio lista manutenzione:");
        System.out.println(sb);

        responder.sendResponse(clientSocket, sb.toString());
    }
}