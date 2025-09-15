package Strategy;

import java.net.Socket;

public interface InserimentoConcessionariaStrategy {
        void inserisci(Object obj, int quantita, Socket clientSocket);
}

