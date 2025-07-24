package Strategy;

import java.net.Socket;

public interface InserimentoStrategy {
        void inserisci(Object obj, int quantita, Socket clientSocket);
}

