package Command;

import DAO.implementazioni.KartDAO;
import Objects.Kart;
import Logic.TableMaker;
import WebTalker.PHPResponseHandler;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class MostraKartAggiuntaCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        List<Map<String, Object>> kartDisponibili = KartDAO.getInstance().getAllKartDisponibiliPerAggiunta();
        TableMaker maker = new TableMaker();
        String response = maker.stringTableMaker(
                kartDisponibili, "targa", "cilindrata", "serbatoio"
        );
        new PHPResponseHandler().sendResponse(clientSocket, response);
    }
}