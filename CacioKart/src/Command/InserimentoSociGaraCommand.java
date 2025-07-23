package Command;

import Logic.Organizzatore;
import Logic.Socio;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class InserimentoSociGaraCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        try {
            System.out.println("Criando inserimentoSociGaraCommand" + in);
            String[] info = in.split(" ");

            if (info.length < 2) {
                new PHPResponseHandler().sendResponse(clientSocket, "Dati insufficienti");
                return;
            }

            String idPrenotazione = info[0];
            String cfSocio = info[1];

            Organizzatore o = new Organizzatore();
            Socio s = new Socio();
            s.setCf(cfSocio);

            o.aggiornaPrenota(idPrenotazione, s, clientSocket);
        } catch (Exception e) {
            new PHPResponseHandler().sendResponse(clientSocket, "Errore aggiornamento prenotazione: " + e.getMessage());
        }
    }
}
