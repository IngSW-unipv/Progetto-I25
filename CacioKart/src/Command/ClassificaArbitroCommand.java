package Command;

import Logic.Arbitro;

import java.net.Socket;

public class ClassificaArbitroCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        Arbitro a = new Arbitro();
        a.gareArbitro(clientSocket);
    }
}
