package Logic;

import Objects.Kart;
import java.net.Socket;

public class EliminazioneKartCommand implements RequestCommand {
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        // 'in' è: "eliminaKart KRT000"
        String[] parti = in.trim().split("\\s+");
        String targa = parti.length > 1 ? parti[1] : ""; // prendi la seconda parola (la targa)
        System.out.println("Elimino targa: " + targa);
        Kart kart = new Kart();
        kart.setTarga(targa);
        Meccanico meccanico = new Meccanico();
        meccanico.rimuoviKartDaNoleggio(kart, clientSocket);
    }
}


