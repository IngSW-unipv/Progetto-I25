package Strategy;

import DAO.PezzoDAO;
import Objects.Pezzo;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class InserimentoPezzoStrategy implements InserimentoStrategy {
    @Override
    public void inserisci(Object obj, int quantita, Socket clientSocket) {
        Pezzo p = (Pezzo) obj;
        new PezzoDAO().insertPezzo(p, quantita); // Passa la quantità!
        new PHPResponseHandler().sendResponse(clientSocket, "Inserimento pezzo riuscito");
    }
}
