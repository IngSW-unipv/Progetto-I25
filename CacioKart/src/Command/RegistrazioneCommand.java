package Command;

import DAO.implementazioni.SocioDAO;
import Logic.DBConnector;
import Logic.Socio;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.util.Arrays;

public class RegistrazioneCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();
        try {
            String[] data = in.split(" ");

            if (data.length == 7 && data[0].equalsIgnoreCase("registrazioneSocio")) {
                data = Arrays.copyOfRange(data, 1, data.length);
            }

            if (data.length != 6) {
                responder.sendResponse(clientSocket, "Formato dati errato");
                return;
            }

            String nome = data[0];
            String cognome = data[1];
            LocalDate dataNascita = LocalDate.parse(data[2]);
            String cf = data[3];
            String mail = data[4];
            String password = data[5];
            Socio nuovoSocio = new Socio(nome, cognome, dataNascita, cf, mail, password);

            SocioDAO dao = new SocioDAO(DBConnector.getInstance());
            int res = dao.registrazione(nuovoSocio);

            responder.sendResponse(clientSocket, "" + res);

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore registrazione: " + e.getMessage());
        }
    }
}
