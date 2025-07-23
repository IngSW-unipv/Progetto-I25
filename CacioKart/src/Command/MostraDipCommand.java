package Logic;

import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MostraDipCommand implements RequestCommand{
    public void execute(String in, Socket clientSocket) throws Exception {
        OperazioneProprietario operazione = new MostraDipendentiOperazione(
                new DBConnector(),
                new PHPResponseHandler()
        );
        operazione.esegui(clientSocket);
    }
}
