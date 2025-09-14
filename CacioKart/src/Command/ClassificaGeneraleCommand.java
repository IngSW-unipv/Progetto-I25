package Command;

import DAO.implementazioni.ClassificaDAO;
import Logic.DBConnector;
import Logic.TableMaker;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ClassificaGeneraleCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        ClassificaDAO dao = new ClassificaDAO(DBConnector.getInstance());
        List<Map<String, Object>> classifica = dao.getClassificaGenerale();

        String s;
        if (classifica == null || classifica.isEmpty()) {
            s = "Nessuna classifica trovata.\nend\n";
        } else {
            TableMaker maker = new TableMaker();
            s = maker.stringTableMaker(
                    classifica, "idGara", "nome", "cognome", "targa", "bGiro", "TempTot"
            );
        }
        new PHPResponseHandler().sendResponse(clientSocket, s);
    }
}
