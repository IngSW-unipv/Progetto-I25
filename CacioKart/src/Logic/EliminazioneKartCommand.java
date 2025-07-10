package Logic;

import Objects.Kart;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class EliminazioneKartCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        System.out.println("EliminaDipCommand" + in);
        String[] parti = in.split(" ");
        Kart kart = new Kart();
        kart.setTarga(parti[0]);
        Meccanico meccanico = new Meccanico();
        meccanico.rimozioneKart(kart, clientSocket);

    }
}
