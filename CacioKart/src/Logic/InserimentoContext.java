package Logic;
import java.net.Socket;

public class InserimentoContext {
        private InserimentoStrategy strategy;

        public void setStrategy(InserimentoStrategy strategy) {
            this.strategy = strategy;
        }

        public void eseguiInserimento(Object obj, int prezzo, Socket socket) {
            strategy.inserisci(obj, prezzo, socket);
        }
}
