package Command;

import Logic.Classifica;
import Strategy.ClassificaUtenteStrategy;
import Logic.Socio;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class ClassificaUtenteCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();
        try {
            System.out.println("cfPilota" + in);
            String cfPilota =  in;
            if (cfPilota == null || cfPilota.isBlank()) {
                responder.sendResponse(clientSocket, "Codice fiscale mancante");
                return;
            }

            Socio s = new Socio();
            s.setCf(cfPilota);
            Classifica service = new Classifica();
            service.generaClassifica(new ClassificaUtenteStrategy(s), clientSocket);
        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore classifica utente: " + e.getMessage());
        }
    }
}
