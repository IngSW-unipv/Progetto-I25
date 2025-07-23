package Command;

import Logic.DBConnector;
import Logic.MostraDipendentiOperazione;
import Logic.OperazioneProprietario;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class MostraDipCommand implements RequestCommand {
    public void execute(String in, Socket clientSocket) throws Exception {
        OperazioneProprietario operazione = new MostraDipendentiOperazione(
                new DBConnector(),
                new PHPResponseHandler()
        );
        operazione.esegui(clientSocket);
    }
}
