package Command;

import DAO.implementazioni.PrenotazioneDAO;
import DAO.implementazioni.SocioDAO;
import Logic.DBConnector;
import Logic.Prenotazione;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PrenotazioneCommand implements RequestCommand {

    public PrenotazioneCommand() {
    }

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        String[] parti = in.split(" ");
        String cf = parti[3];
        LocalDate dataGara = LocalDate.parse(parti[1]);
        String[] orario = parti[2].split("-");
        LocalTime fasciaOraria = LocalTime.parse(orario[0], DateTimeFormatter.ofPattern("HH:mm"));
        String tipologia = parti[4];
        PHPResponseHandler responder = new PHPResponseHandler();
        PrenotazioneDAO dao = new PrenotazioneDAO(DBConnector.getInstance());

        int res = dao.prenota(cf, tipologia, dataGara, fasciaOraria, LocalDate.now());
        responder.sendResponse(clientSocket, "" + res);
    }
}
