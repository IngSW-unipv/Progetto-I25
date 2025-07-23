package Logic;

import Enums.TipoComandi;
import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class RichiestaGaraSCommand implements RequestCommand{

    private final TipoComandi tipo;

    public RichiestaGaraSCommand(TipoComandi tipo) {
        this.tipo = tipo;
    }

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        PHPResponseHandler responder = new PHPResponseHandler();
        MostraPrenotazioniSocioStrategy strategyS;
        MostraPrenotazioniOrganizzatoreStrategy strategyO;
        try {
            System.out.println("dati" + in );  // Es. CF utente o nessun dato
            switch (tipo) {
                case MOSTRA_PRENOTAZIONI_UTENTE -> {
                     strategyS = new MostraPrenotazioniSocioStrategy(in);
                    Prenotazione p = new Prenotazione();
                    p.mostraPrenotazioni(strategyS, clientSocket);
                }
                case RICHIESTA_GARA_SECCA -> {
                    strategyO = new MostraPrenotazioniOrganizzatoreStrategy();
                    Prenotazione p = new Prenotazione();
                    p.mostraPrenotazioni(strategyO, clientSocket);
                }
                default -> {
                    responder.sendResponse(clientSocket, "Comando non riconosciuto");
                    return;
                }
            }

        } catch (Exception e) {
            responder.sendResponse(clientSocket, "Errore durante la visualizzazione delle prenotazioni: " + e.getMessage());
        }
    }
}
