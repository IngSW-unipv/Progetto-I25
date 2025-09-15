package Command;

import DAO.implementazioni.OrganizzatoreDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;

public class InserimentoSociGaraCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        String[] parti = in.split(" ");
        if (parti.length < 3) {
            new PHPResponseHandler().sendResponse(clientSocket, "Parametri insufficienti.\nend\n");
            return;
        }
        String idP = parti[1];
        String socio = parti[2];
        String data = LocalDate.now().toString();

        OrganizzatoreDAO dao = new OrganizzatoreDAO(DBConnector.getInstance());
        String esito = dao.inserisciSocioInPrenotazione(idP, socio, data);

        if ("OK".equals(esito)) {
            new PHPResponseHandler().sendResponse(clientSocket, "Socio inserito correttamente.\nend\n");
        } else {
            new PHPResponseHandler().sendResponse(clientSocket, "Errore nell'inserimento.\nend\n");
        }
    }
}
