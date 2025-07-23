package Logic;

import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AggiungiPenalitaCommand implements RequestCommand {

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();

        try {
           System.out.println("penalita" + in );// Es: "CF1234 12 00:02:30"
            String[] info = in.split(" ");
            if (info.length != 3) {
                responder.sendResponse(clientSocket, "Formato penalità non valido");
                return;
            }

            Socio s = new Socio();
            s.setCf(info[0]);

            String idGara = info[1];
            LocalTime penalita = LocalTime.parse(info[2], DateTimeFormatter.ofPattern("HH:mm:ss"));

            Arbitro a = new Arbitro();
            a.inserimentoPenalita(s, idGara, penalita, clientSocket);

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore inserimento penalità: " + e.getMessage());
        }
    }
}
