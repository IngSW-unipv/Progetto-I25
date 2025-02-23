import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class PHPResponseHandler {
    PrintWriter out;

    public PHPResponseHandler() {
    }

    /**Metodo per inviare risposte al client.
     * Il chiamante deve passare il socket di comunicazione con il client e
     * il messaggio da spedire.
     *
     * @param clientSocket
     * @param messaggio
     */
    public void sendResponse(Socket clientSocket, String messaggio){
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(messaggio);
            //System.out.println("Il messaggio spedito è: " + messaggio);
            out.flush();    //Pulisco il buffer di dati per evitare problemi
            Thread.sleep(100);  //Attendo a chiudere il socket per dare tempo al client di ricevere la risposta

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
