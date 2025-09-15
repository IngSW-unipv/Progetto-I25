package Command;

import DAO.implementazioni.KartDAO;
import Objects.Kart;
import Logic.TableMaker;
import WebTalker.PHPResponseHandler;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class MostraKartCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        List<Map<String, Object>> kartNoleggio = KartDAO.getInstance().getAllKart();
        TableMaker maker = new TableMaker();
        String response = maker.stringTableMaker(
                kartNoleggio, "targa", "cilindrata", "serbatoio"
        );
        new PHPResponseHandler().sendResponse(clientSocket, response);
    }
}