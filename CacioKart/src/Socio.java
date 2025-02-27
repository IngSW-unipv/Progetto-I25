import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;


public class Socio extends Persona implements Iinventario{
    private final int MAX = 20;
    private String INSERT;
    private PHPResponseHandler responder;
    private int queryIndicator;
    private DBConnector db;
    private Prenotazione p;

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
        p.prenotation(tipologia,dataG,orarioI,orarioF,clientSocket);
    }

    public void comprareKart(){

    }

    //public int

    //Override metodi Iinventario


}
