package Command;

import DAO.implementazioni.OrganizzatoreDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class AggiungiTeamCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        // Messaggio: "aggiungiTeam TEAMRED #ff0000 S0009 S0010"
        String[] parti = in.split(" ");
        if (parti.length < 4) {
            new PHPResponseHandler().sendResponse(clientSocket, "Parametri insufficienti.\nend\n");
            return;
        }
        String nomeTeam = parti[1];
        String colore = parti[2];
        // Dal terzo elemento in poi sono i soci
        List<String> soci = Arrays.asList(Arrays.copyOfRange(parti, 3, parti.length));

        OrganizzatoreDAO dao = new OrganizzatoreDAO(DBConnector.getInstance());
        dao.aggiungiTeam(nomeTeam, colore, soci);

        new PHPResponseHandler().sendResponse(clientSocket, "Team creato con successo.\nend\n");
    }
}
