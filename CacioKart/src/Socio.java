import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;


public class Socio extends Persona implements Iinventario{
    private String INSERT;
    private PHPResponseHandler responder;
    private int queryIndicator;
    private DBConnector db;
    private Prenotazione p;
    private String UPDATE;
    private String DELETE;


    public Socio(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {
        super(nome, cognome, dataNascita, cF, mail, password);
    }

    //metodo classifica gara?

    /**Metodo per registrare un utente nel db
     * La query viene eseguita tramite il metodo sendResponse della classe DBConnector
     *
     * @param clientSocket
     */
    public void registrazione(Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT = "INSERT INTO socio (socio, nome, cognome, mail, passw, dataN) VALUES('" +
                this.getcF() + "', '" +
                this.getNome() + "', '" +
                this.getCognome() +"', '" +
                this.getMail() +"', '" +
                this.getPassword() +"', '" +
                this.getDataNascita() +"')";

        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));

    }

    public void richiestaP(String tipologia,LocalDate dataG,LocalTime orarioI,LocalTime orarioF,Socket clientSocket) throws SQLException{
        p = new Prenotazione();
        p.prenotation(tipologia,dataG,orarioI,orarioF,clientSocket);
    }

    public void compraKart(String info, Socket clientSocket) throws SQLException {
        // Inserisco la targa in quell'utente specifico
        String[] kartUtente = info.split(" ");
        String cf = kartUtente[0];
        String targa = kartUtente[1];
        db = new DBConnector();
        responder = new PHPResponseHandler();

        /*Fare un IF che controlla se l'utente ha già un kart (per impedire l'acquisto?)

         */

        UPDATE = "UPDATE socio SET targa = '" + targa + "' WHERE socio = '" + cf + "'";
        db.executeUpdateQuery(UPDATE);
        DELETE = "DELETE FROM concessionaria WHERE tipol = '" + targa + "'";
        queryIndicator = db.executeUpdateQuery(DELETE);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }

    //public int

    //Override metodi Iinventario


}
