package Logic;

import Strategy.InserimentoConcessionariaStrategy;

import java.net.Socket;

//Ci pensa Luca?

public class InserimentoContext {
    private InserimentoConcessionariaStrategy strategy;

    public void setStrategy(InserimentoConcessionariaStrategy strategy) {
        this.strategy = strategy;
    }

    // Cambia “prezzo” in “quantita” anche qui
    public void eseguiInserimento(Object obj, int quantita, Socket clientSocket) {
        strategy.inserisci(obj, quantita, clientSocket);
    }
}
