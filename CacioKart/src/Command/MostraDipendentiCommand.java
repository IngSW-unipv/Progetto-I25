package Command;

import DAO.DipendenteDAO;
import Logic.DBConnector;
import Objects.Dipendente;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;

public class MostraDipendentiCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        DipendenteDAO dao = new DipendenteDAO(DBConnector.getInstance());
        List<Dipendente> lista = dao.mostraDipendenti();
        StringBuilder sb = new StringBuilder();
        for (Dipendente d : lista) {
            sb.append(d.getCf()).append(" ")        // <--- usa getCf()
                    .append(d.getNome()).append(" ")
                    .append(d.getCognome()).append(" ")
                    .append(d.getRuolo()).append("\n");
        }
        new PHPResponseHandler().sendResponse(clientSocket, sb.toString());
    }
}
