import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class Socio extends Persona implements Iinventario {
    private String SELECT, UPDATE, INSERT;
    private PHPResponseHandler responder;
    private int queryIndicator;
    private DBConnector db;

    public Socio(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {
        super(nome, cognome, dataNascita, cF, mail, password);
    }

    public Socio() {

    }

    //metodo classifica gara?

    /**
     * Metodo per registrare un utente nel db
     * La query viene eseguita tramite il metodo sendResponse della classe DBConnector
     *
     * @param clientSocket
     */
    public void registrazione(Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = "INSERT INTO socio (socio, nome, cognome, mail, passw, dataN) VALUES('" +
                this.getCf() + "', '" +
                this.getNome() + "', '" +
                this.getCognome() + "', '" +
                this.getMail() + "', '" +
                this.getPassword() + "', '" +
                this.getDataNascita() + "')";

        queryIndicator = db.executeUpdateQuery(SELECT);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));

    }

    public void compraKart(String info, Socket clientSocket) throws SQLException {
        // Inserisco la targa in quell'utente specifico
        String[] kartUtente = info.split(" ");
        String cf = kartUtente[0];
        String targa = kartUtente[1];
        db = new DBConnector();
        responder = new PHPResponseHandler();

        //Fare un IF che controlla se l'utente ha già un kart (per impedire l'acquisto?)
        UPDATE = "UPDATE socio SET targa = '" + targa + "' WHERE socio = '" + cf + "'";
        SELECT = "SELECT idProdotto FROM concessionaria WHERE tipol = '" + targa + "'";

        queryIndicator = db.executeUpdateQuery(UPDATE);
        if (queryIndicator == 0) {
            responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
            return;
        }

        List<Map<String, Object>> idProdotto = db.executeReturnQuery(SELECT);
        System.out.println("Ecco la targa che vogliamo acquistare: " + idProdotto);
        INSERT = "INSERT INTO acquista (socio, idProdotto) VALUES('" + cf + "', '" + idProdotto.toString().replaceAll("\\D", "") + "')";
        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }

    // DA COMPLETARE MA L'IDEA è QUESTA
    public void acquistaPezzi(String info, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        UPDATE = "UPDATE concessionaria SET quantita = quantita - 1 WHERE idProdotto ='" + info + "'";
        queryIndicator = db.executeUpdateQuery(UPDATE);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }


}
