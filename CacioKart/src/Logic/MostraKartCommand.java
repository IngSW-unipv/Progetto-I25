package Logic;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MostraKartCommand implements RequestCommand {
    private final String query;
    private final String[] colonne;

    public MostraKartCommand(String query, String... colonne) {
        this.query = query;
        this.colonne = colonne;
    }

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        Meccanico m = new Meccanico();
        m.mostraKart(query, clientSocket, in);
    }

}
