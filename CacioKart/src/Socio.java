import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Socio extends Persona implements Iinventario{
    private String INSERT;
    private PHPResponseHandler responder;
    private int insertError;
    private DBConnector db;

    public Socio(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {
        super(/*nome, cognome, dataNascita, cF, mail, password*/);
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
        INSERT = "INSERT INTO socio (cf, nome, cognome, mail, pass, dataN) VALUES('" +
                this.getcF() + "', '" +
                this.getNome() + "', '" +
                this.getCognome() +"', '" +
                this.getMail() +"', '" +
                this.getPassword() +"', '" +
                this.getDataNascita() +"')";

        insertError = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(insertError));

    }

    public void comprareKart(){

    }

    //public int

    //Override metodi Iinventario
}
