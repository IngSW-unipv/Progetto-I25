package Strategy;

import Logic.DBConnector;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;

public interface PrenotazioneStrategy {
    int eseguiPrenotazione(String idPrenotazione, String cf, LocalDate dataGara, LocalTime fasciaOraria, LocalDate dataO, DBConnector db /*,PHPResponseHandler responder, Socket clientSocket*/);
}
