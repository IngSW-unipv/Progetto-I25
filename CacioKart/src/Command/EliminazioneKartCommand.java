package Command;

import DAO.implementazioni.KartDAO;
import Objects.Kart;
import WebTalker.PHPResponseHandler;
import java.net.Socket;

public class EliminazioneKartCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        // 'in' è: "eliminaKart KRT000"
        String[] parti = in.trim().split("\\s+");
        String targa = parti.length > 1 ? parti[1] : ""; // prendi la seconda parola (la targa)
        System.out.println("Elimino targa: " + targa);
        Kart kart = new Kart();
        kart.setTarga(targa);

        boolean rimosso = KartDAO.getInstance().deleteKart(kart);
        new PHPResponseHandler().sendResponse(clientSocket, rimosso ? "OK" : "ERRORE");
    }
}