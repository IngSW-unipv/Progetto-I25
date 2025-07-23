package Command;

import java.net.Socket;
import Logic.Meccanico;

public class MostraKartAggiuntaCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        Meccanico m = new Meccanico();
        m.mostraKartConcessionaria(clientSocket);
    }
}
