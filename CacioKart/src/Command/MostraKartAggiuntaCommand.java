package Command;

import DAO.implementazioni.KartDAO;
import Objects.Kart;
import Logic.TableMaker;
import WebTalker.PHPResponseHandler;
import java.net.Socket;
import java.util.List;

public class MostraKartAggiuntaCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        List<Kart> kartDisponibili = KartDAO.getInstance().getAllKartDisponibiliPerAggiunta();
        TableMaker maker = new TableMaker();
        String response = maker.kartListToString(kartDisponibili);
        new PHPResponseHandler().sendResponse(clientSocket, response);
    }
}