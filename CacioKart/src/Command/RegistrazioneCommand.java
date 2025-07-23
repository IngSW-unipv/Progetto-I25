package Logic;

import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Arrays;

public class RegistrazioneCommand implements RequestCommand{

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();
        try {
            System.out.println("RegistrazioneCommand" + in );
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
            nuovoSocio.registrazione(clientSocket);

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore registrazione: " + e.getMessage());
        }
    }
}
