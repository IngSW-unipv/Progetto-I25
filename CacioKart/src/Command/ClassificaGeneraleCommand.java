package Logic;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClassificaGeneraleCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        Classifica service = new Classifica();
        service.generaClassifica(new ClassificaCompletaStrategy(), clientSocket);
    }
}
