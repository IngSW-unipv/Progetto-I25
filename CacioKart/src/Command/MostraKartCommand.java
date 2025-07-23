package Command;

import Logic.Meccanico;

import java.net.Socket;

public class MostraKartCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        Meccanico m = new Meccanico();
        m.mostraKartNoleggio(clientSocket);
    }
}
