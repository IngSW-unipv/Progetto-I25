import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Proprietario extends Dipendente {
    private DBConnector db;
    private PHPResponseHandler responder;
    private String INSERT;
    private int queryIndicator;

    public Proprietario(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password, double stipendio, String ruolo, LocalTime oreL) {
        super(nome, cognome, dataNascita, cF, mail, password, stipendio, ruolo, oreL);
    }

    public void visioneBilancio(){

    };

    public void aggiuntaDipendenti(Dipendente nuovoDip,Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT = "INSERT INTO dipendente (dip, nome, cognome, mail, passw, dataN, ruolo, oreL, stipendio) VALUES('" +
                nuovoDip.getcF() + "', '" +
                nuovoDip.getNome() + "', '" +
                nuovoDip.getCognome() + "', '" +
                nuovoDip.getMail() + "', '" +
                nuovoDip.getPassword() + "', '" +
                nuovoDip.getDataNascita() + "', '" +
                nuovoDip.getRuolo() + "', '" +
                nuovoDip.getOreL() + "', '" +
                nuovoDip.getStipendio() +"')";

        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    };

    public void rimozioneDipendenti(){

    };
}
