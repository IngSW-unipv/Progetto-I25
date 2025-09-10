package Logic;

import DAO.implementazioni.BilancioDAO;
import WebTalker.PHPResponseHandler;
import java.net.Socket;

//Spostare dentro il command usando bilancioDAO

public class BilancioOperazione {

    private final BilancioDAO bilancioDAO;
    private final PHPResponseHandler responder;

    public BilancioOperazione(BilancioDAO bilancioDAO, PHPResponseHandler responder) {
        this.bilancioDAO = bilancioDAO;
        this.responder = responder;
    }

    public void esegui(Socket clientSocket) {
        double entrate = bilancioDAO.getEntrate();
        double uscite = bilancioDAO.getUscite();
        double saldo = bilancioDAO.getSaldo();

        String response = String.format("%.2f %.2f %.2f", entrate, uscite, saldo);
        responder.sendResponse(clientSocket, response);
    }
}
