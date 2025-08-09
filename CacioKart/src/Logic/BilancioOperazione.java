package Logic;

import DAO.implementazioni.BilancioDAO;
import WebTalker.PHPResponseHandler;
import java.net.Socket;

public class BilancioOperazione implements OperazioneProprietario {

    private final BilancioDAO bilancioDAO;
    private final PHPResponseHandler responder;

    public BilancioOperazione(BilancioDAO bilancioDAO, PHPResponseHandler responder) {
        this.bilancioDAO = bilancioDAO;
        this.responder = responder;
    }

    @Override
    public void esegui(Socket clientSocket) {
        double entrate = bilancioDAO.getEntrate();
        double uscite = bilancioDAO.getUscite();
        double saldo = bilancioDAO.getSaldo();

        String response = String.format("%.2f %.2f %.2f", entrate, uscite, saldo);
        responder.sendResponse(clientSocket, response);
    }
}
