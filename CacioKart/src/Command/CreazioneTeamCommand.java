package Command;

import Logic.Organizzatore;
import Objects.Team;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class CreazioneTeamCommand implements RequestCommand {
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();
        try {
            System.out.println("team" + in);  // Ricevi i dati come "nome fondatore nomeTeam colore altroDato"
            String[] team = in.split(" ");

            if (team.length != 4) {
                responder.sendResponse(clientSocket, "Formato dati errato");
                return;
            }

            Team t = new Team(team[0], team[1], team[2], team[3]);
            Organizzatore o = new Organizzatore();
            o.creaTeam(t, clientSocket);

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore creazione team: " + e.getMessage());
        }
    }
}
