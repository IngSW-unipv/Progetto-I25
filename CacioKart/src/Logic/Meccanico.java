package Logic;

import Objects.Kart;
import WebTalker.PHPResponseHandler;
import DAO.KartDAO;
import DAO.ManutenzioneDAO;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Meccanico {

    public Meccanico() {}

    // 1. Mostra tutti i kart disponibili da aggiungere al noleggio (in concessionaria)
    public void mostraKartConcessionaria(Socket clientSocket) {
        List<Kart> kartDisponibili = KartDAO.getInstance().getAllKartDisponibiliPerAggiunta();
        PHPResponseHandler responder = new PHPResponseHandler();
        responder.sendKartList(clientSocket, kartDisponibili);
    }

    // 1bis. Aggiungi un kart al noleggio (tabella kart)
    public void aggiuntaKart(Kart k, Socket clientSocket) {
        boolean inserito = KartDAO.getInstance().insertKart(k);
        PHPResponseHandler responder = new PHPResponseHandler();
        responder.sendResponse(clientSocket, inserito ? "OK" : "ERRORE");
    }

    // 2. Mostra tutti i kart a noleggio
    public void mostraKartNoleggio(Socket clientSocket) {
        List<Kart> kartNoleggio = KartDAO.getInstance().getAllKart();
        PHPResponseHandler responder = new PHPResponseHandler();
        responder.sendKartList(clientSocket, kartNoleggio);
    }

    // 2bis. Rimuovi un kart dal noleggio
    public void rimuoviKartDaNoleggio(Kart k, Socket clientSocket) {
        boolean rimosso = KartDAO.getInstance().deleteKart(k);
        PHPResponseHandler responder = new PHPResponseHandler();
        responder.sendResponse(clientSocket, rimosso ? "OK" : "ERRORE");
    }

    // 3. Effettua il pieno su un kart
    public void effettuaPieno(Kart k, Socket clientSocket) {
        boolean eseguito = KartDAO.getInstance().refillKart(k);
        PHPResponseHandler responder = new PHPResponseHandler();
        responder.sendResponse(clientSocket, eseguito ? "OK" : "ERRORE");
    }

    // 4. Effettua la manutenzione su un kart
    public void aggiornaManutenzione(Kart k, String tipoIntervento, double costo, Socket clientSocket) {
        boolean eseguita = ManutenzioneDAO.getInstance().insertManutenzione(k, tipoIntervento, costo);
        PHPResponseHandler responder = new PHPResponseHandler();
        responder.sendResponse(clientSocket, eseguita ? "OK" : "ERRORE");
    }

    // 5. Mostra tutti i kart con info manutenzione (solo targa e ultima manutenzione o MAI_FATTA)
    public void mostraKartManutenzione(Socket clientSocket) {
        List<Map<String, Object>> lista = KartDAO.getInstance().getKartManutenzione();
        PHPResponseHandler responder = new PHPResponseHandler();

        if (lista == null || lista.isEmpty()) {
            responder.sendResponse(clientSocket, "Nessun kart trovato");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> r : lista) {
            String targa = String.valueOf(r.get("targa"));
            String ultimaManutenzione = String.valueOf(r.get("ultimaManutenzione"));
            if (ultimaManutenzione == null || ultimaManutenzione.equals("null")) {
                ultimaManutenzione = "MAI_FATTA";
            }
            sb.append(targa).append(" ").append(ultimaManutenzione).append("\n");
        }
        sb.append("end");

        System.out.println("Invio lista manutenzione:");
        System.out.println(sb.toString());

        responder.sendResponse(clientSocket, sb.toString());
    }
}
