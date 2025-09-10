package Command;

import DAO.implementazioni.KartDAO;
import Objects.Kart;
import Logic.TableMaker;
import WebTalker.PHPResponseHandler;
import java.net.Socket;
import java.util.List;

public class MostraKartCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        List<Kart> kartNoleggio = KartDAO.getInstance().getAllKart();
        TableMaker maker = new TableMaker();
        String response = maker.kartListToString(kartNoleggio);
        new PHPResponseHandler().sendResponse(clientSocket, response);
    }
}