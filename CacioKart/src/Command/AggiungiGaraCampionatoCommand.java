package Command;

import DAO.OrganizzatoreDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class AggiungiGaraCampionatoCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        // Esempio comando: "aggiungiGaraCampionato G001 C005"
        String[] parti = in.split(" ");
        if (parti.length < 3) {
            new PHPResponseHandler().sendResponse(clientSocket, "Parametri insufficienti.\nend\n");
            return;
        }
        String idGara = parti[1];
        String idCampionato = parti[2];

        OrganizzatoreDAO dao = new OrganizzatoreDAO(DBConnector.getInstance());
        dao.aggiungiGaraACampionato(idGara, idCampionato);

        new PHPResponseHandler().sendResponse(clientSocket, "Gara aggiunta al campionato con successo.\nend\n");
    }
}
