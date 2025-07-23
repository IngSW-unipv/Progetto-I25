package Logic;

import Objects.Kart;
import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class KartInsertCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        String[] parti = in.split(" ");

        if (parti.length != 4) {
            new PHPResponseHandler().sendResponse(clientSocket, "Formato input non valido. Atteso: comando targa cilindrata prezzo");
            return;
        }

        String targa = parti[1];
        int cilindrata;
        int prezzo;

        try {
            cilindrata = Integer.parseInt(parti[2]);
            prezzo = Integer.parseInt(parti[3]);
        } catch (NumberFormatException e) {
            new PHPResponseHandler().sendResponse(clientSocket, "Cilindrata o prezzo non numerici.");
            return;
        }

        Kart nuovoKart = new Kart();
        nuovoKart.setTarga(targa);
        nuovoKart.setCilindrata(cilindrata);

        InserimentoContext ctx = new InserimentoContext();
        ctx.setStrategy(StrategyFactory.getStrategy(nuovoKart));
        ctx.eseguiInserimento(nuovoKart, prezzo, clientSocket);
    }
}
