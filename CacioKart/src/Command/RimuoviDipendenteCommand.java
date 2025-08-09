package Command;

import DAO.implementazioni.DipendenteDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class RimuoviDipendenteCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        // Messaggio esempio: "rimuoviDipendente dip123"
        String[] parti = in.split(" ");
        if (parti.length < 2) {
            new PHPResponseHandler().sendResponse(clientSocket, "Parametro dip mancante.");
            return;
        }
        String dip = parti[1];
        DipendenteDAO dao = new DipendenteDAO(DBConnector.getInstance());
        dao.rimuoviDipendente(dip);
        new PHPResponseHandler().sendResponse(clientSocket, "Dipendente rimosso correttamente");
    }
}
