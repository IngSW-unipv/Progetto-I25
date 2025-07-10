package Logic;

import Objects.Dipendente;
import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Arrays;

public class EliminaDipCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
            PHPResponseHandler responder = new PHPResponseHandler();

            // 1) Log iniziale per debug
            System.out.println("[DEBUG] EliminaDipCommand input raw: \"" + in + "\"");

            // 2) Split generale
            String[] tokens = in.trim().split("\\s+");
            if (tokens.length < 2) {
                responder.sendResponse(clientSocket,
                        "Formato input non valido. Atteso: <comando> <codiceFiscale>");
                return;
            }

            // Primo token = nome comando, resto = argomenti
            String command = tokens[0];
            String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);

            // 3) Controllo numero di argomenti
            if (args.length != 1) {
                responder.sendResponse(clientSocket,
                        "Formato input non valido. Atteso: " + command + " <codiceFiscale>");
                return;
            }

            String cf = args[0];
            System.out.println("[DEBUG] CF da eliminare: " + cf);
            if (cf.isBlank()) {
                responder.sendResponse(clientSocket, "Codice fiscale non può essere vuoto");
                return;
            }

            // 4) Prepara l'operazione di rimozione
            Dipendente dipendente = new Dipendente();
            dipendente.setCf(cf);

            OperazioneProprietario operazione = new RimozioneDipendenteOperazione(
                    new DBConnector(),
                    responder,
                    dipendente
            );

            // 5) Esegui e lascia i log dentro l'operazione
            operazione.esegui(clientSocket);
        }

}
