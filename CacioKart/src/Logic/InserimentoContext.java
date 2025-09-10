package Logic;

import Strategy.InserimentoStrategy;

import java.net.Socket;

//Ci pensa Luca?

public class InserimentoContext {
    private InserimentoStrategy strategy;

    public void setStrategy(InserimentoStrategy strategy) {
        this.strategy = strategy;
    }

    // Cambia “prezzo” in “quantita” anche qui
    public void eseguiInserimento(Object obj, int quantita, Socket clientSocket) {
        strategy.inserisci(obj, quantita, clientSocket);
    }
}
