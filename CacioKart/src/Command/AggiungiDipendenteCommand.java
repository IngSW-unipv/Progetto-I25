package Command;

import DAO.DipendenteDAO;
import Logic.DBConnector;
import Objects.Dipendente;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;

public class AggiungiDipendenteCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();

        try {
            System.out.println("dip " + in);

            // Rimuove il comando iniziale e splitta i dati
            String[] dipendente = in.replaceFirst("registrazioneDipen ", "").split(" ");

            if (dipendente.length != 9) {
                responder.sendResponse(clientSocket, "Formato dati errato");
                return;
            }

            // Parsing dei dati
            String nome = dipendente[0];
            String cognome = dipendente[1];
            LocalDate dataNascita = LocalDate.parse(dipendente[2]);
            String cf = dipendente[3];
            String mail = dipendente[4];
            String password = dipendente[5];
            double stipendio = Double.parseDouble(dipendente[6]);
            String ruolo = dipendente[7];
            LocalTime oreLavoro = LocalTime.parse(dipendente[8]);

            // Creazione oggetto Dipendente
            Dipendente d = new Dipendente(nome, cognome, dataNascita, cf, mail, password, stipendio, ruolo, oreLavoro);

            // USO DIRETTO DEL DAO (Pattern consigliato)
            DipendenteDAO dao = new DipendenteDAO(DBConnector.getInstance());
            dao.inserisciDipendente(d);

            responder.sendResponse(clientSocket, "Dipendente aggiunto correttamente");
        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore aggiunta dipendente: " + e.getMessage());
        }
    }
}
