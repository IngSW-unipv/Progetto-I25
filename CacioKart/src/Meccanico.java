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
    private StringBuilder listaKart;
    private String targa;
    private String cilindrata;
    private String serbatoio;

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
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = "SELECT * FROM caciokart.kart";
        kart = db.executeReturnQuery(SELECT);

        //Fare l'if di 0 kart
        if(!kart.isEmpty()) {
            for(Map<String, Object> row : kart) {
                targa = row.get("targa").toString();
                cilindrata = row.get("cilindrata").toString();
                serbatoio = row.get("serbatoio").toString();
                listaKart.append(targa).append(" ").append(cilindrata).append(" ").append(serbatoio).append("\n");
            }
            responder.sendResponse(clientSocket, listaKart+"end");

        }else{
            responder.sendResponse(clientSocket, "end");
        }
    }

}
