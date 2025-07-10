package Logic;

import Objects.Pezzo;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class PezzoInsertCommand implements RequestCommand{
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        System.out.println("pezzo" + in);
        String[] parti = in.split(" ");
        Pezzo nuovoPezzo = new Pezzo();
        nuovoPezzo.setIdProdotto(parti[0]);
        int prezzo = Integer.parseInt(parti[1]);
        InserimentoContext ctx = new InserimentoContext();
        ctx.setStrategy(StrategyFactory.getStrategy(nuovoPezzo));
        ctx.eseguiInserimento(nuovoPezzo, prezzo, clientSocket);
    }
}
