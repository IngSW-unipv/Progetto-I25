package Logic;

import Enums.Query;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MostraSociCampCommand implements RequestCommand{

    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        Organizzatore o = new Organizzatore();
        String query = Query.MOSTRA_SOCI_INSERIMENTO_CAMPIONATO.getQuery();
        o.mostraSociInserimento(query, clientSocket);
    }
}
