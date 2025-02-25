import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Meccanico extends Dipendente implements Iinventario {
    private List<Map<String, Object>> kart;
    private DBConnector db;
    private PHPResponseHandler responder;
    private String SELECT;
    private String listaKart;

    public Meccanico(/*String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password, double stipendio*/) {
        super(/*nome, cognome, dataNascita, cF, mail, password, stipendio*/);
    }

    //aggiunta kart noleggio??
    //interfaccia rimozione kart
    //Override metodi Iinventario

    public void aggiornamentoManutenzione(){

    };

    public void aggiuntaBenzina(){

    };

    public void mostraKart(Socket clientSocket) throws SQLException {
        //Utilizzo DBConnector per ricevere tutte le informazioni dei kart
        //Spedisco le targhe tramite ResponseHandler
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = "SELECT * FROM caciokart.kart";
        kart = db.executeReturnQuery(SELECT);

        //Kart è una map con svariati campi
        String targa = kart.get(0).get("targa").toString();
        String cilindrata = kart.get(0).get("cilindrata").toString();
        String serbatoio = kart.get(0).get("serbatoio").toString();
        listaKart = targa + " " + cilindrata + " " + serbatoio;

        responder.sendResponse(clientSocket, listaKart);

        }

    }
