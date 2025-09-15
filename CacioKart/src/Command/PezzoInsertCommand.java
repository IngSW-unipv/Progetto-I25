package Command;

import Logic.InserimentoContext;
import Objects.Pezzo;
import Strategy.ConcessionariaStrategyFactory;

import java.net.Socket;

public class PezzoInsertCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        System.out.println("pezzo " + in);
        String[] parti = in.split(" ");
        if (parti.length < 3) {
            System.out.println("Parametri insufficienti per il comando aggiungiPezzi.");
            return;
        }
        Pezzo nuovoPezzo = new Pezzo();
        nuovoPezzo.setIdProdotto(parti[1]);
        int quantita = Integer.parseInt(parti[2]);
        InserimentoContext ctx = new InserimentoContext();
        ctx.setStrategy(ConcessionariaStrategyFactory.getStrategy(nuovoPezzo));
        ctx.eseguiInserimento(nuovoPezzo, quantita, clientSocket);
    }
}
