package Command;

import DAO.implementazioni.ClassificaDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class AggiungiPenalitaCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        // Messaggio: "inserisciPenalita 00:02:00 17 socio123"
        String[] parti = in.split(" ");
        if (parti.length < 4) {
            new PHPResponseHandler().sendResponse(clientSocket, "Parametri insufficienti.");
            return;
        }
        String socio = parti[1];
        String idGara = parti[2];
        String tempo = parti[3];


        ClassificaDAO dao = new ClassificaDAO(DBConnector.getInstance());
        dao.inserisciPenalita(tempo, idGara, socio);
        new PHPResponseHandler().sendResponse(clientSocket, "Penalità inserita con successo.");
    }
}
