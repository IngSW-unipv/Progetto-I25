package Command;

import Logic.BilancioOperazione;
import Logic.DBConnector;
import Logic.OperazioneProprietario;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class BilancioCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        OperazioneProprietario operazione = new BilancioOperazione(DBConnector.getInstance(), new PHPResponseHandler());
        operazione.esegui(clientSocket);
    }
}
