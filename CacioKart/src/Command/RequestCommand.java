package Command;

import java.net.Socket;

public interface RequestCommand {
    void execute(String in, Socket clientSocket) throws Exception;

}
