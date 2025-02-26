import java.net.Socket;
import java.time.LocalDate;

public class Proprietario extends Dipendente {
    private DBConnector db;
    private PHPResponseHandler responder;

    public Proprietario(/*String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password, double stipendio*/) {
        super(/*nome, cognome, dataNascita, cF, mail, password, stipendio*/);
    }

    public void visioneBilancio(){

    };

    public void aggiuntaDipendenti(Socket clientSocket){
        db = new DBConnector();
        responder = new PHPResponseHandler();
        Kart nuovoKart = new Kart(targa, cilindrata, serbatoio);

        INSERT = "INSERT INTO kart (targa, cilindrata, serbatoio) VALUES('" +
                nuovoKart.getTarga() + "', '" +
                nuovoKart.getCilindrata() + "', '" +
                nuovoKart.getSerbatoio() +"')";

        insertError = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(insertError));
    };

    public void rimozioneDipendenti(){

    };
}
