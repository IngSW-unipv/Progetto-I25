package Logic;

import Objects.Kart;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class InserimentoKartStrategy implements InserimentoStrategy{
    @Override
    public void inserisci(Object obj, int prezzo, Socket clientSocket) {
        Kart k = (Kart) obj;
        ConcessionariaService service = new ConcessionariaService();
        service.inserisciKartInConcessionaria(k, prezzo);
        new PHPResponseHandler().sendResponse(clientSocket, "Inserimento kart riuscito");
    }
}
