package Logic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;

public class PrenotazioneCommand implements RequestCommand {

    public PrenotazioneCommand() {
    }

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        String[] parti = in.split(" ");
        String cf = parti[2];
        LocalDate dataGara = LocalDate.parse(parti[0]);
        LocalTime fasciaOraria = LocalTime.parse(parti[1]);
        String tipologia = parti[3];
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.prenotazioneGara(cf, tipologia, dataGara, fasciaOraria, clientSocket);
    }
}
