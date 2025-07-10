package Logic;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public interface RequestCommand {
    void execute(String in, Socket clientSocket) throws Exception;

}
