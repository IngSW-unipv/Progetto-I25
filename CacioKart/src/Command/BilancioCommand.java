package Command;

import Logic.BilancioOperazione;
import DAO.implementazioni.BilancioDAO;
import Logic.DBConnector;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class BilancioCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        BilancioDAO bilancioDAO = new BilancioDAO(DBConnector.getInstance());
        PHPResponseHandler responder = new PHPResponseHandler();
        BilancioOperazione bilancioOp = new Logic.BilancioOperazione(bilancioDAO, responder);
        bilancioOp.esegui(clientSocket);
    }
}
