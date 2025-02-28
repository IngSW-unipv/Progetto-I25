import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Proprietario {
    private DBConnector db;
    private PHPResponseHandler responder;
    private String INSERT;
    private String SELECT;
    private int queryIndicator;
    private List<Map<String, Object>> dipendenti;
    private String cf;
    private String nome;
    private String cognome;
    private StringBuilder listaDipen = new StringBuilder();

    public Proprietario() {

    }

    public void visioneBilancio(){

    };

    public void mostraDipendenti(Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = "SELECT * FROM caciokart.kart";
        dipendenti = db.executeReturnQuery(SELECT);

        if(dipendenti == null) {
            for(Map<String, Object> row : dipendenti) {
                cf = row.get("dip").toString();
                nome = row.get("nome").toString();
                cognome = row.get("cognome").toString();
                listaDipen.append(cf).append(" ").append(nome).append(" ").append(cognome).append("\n");
            }
            listaDipen.append("end");
            responder.sendResponse(clientSocket, listaDipen.toString());

        }else{
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void aggiuntaDipendenti(Dipendente nuovoDip,Socket clientSocket) throws SQLException {
        db = new DBConnector();
        //Gestire i diversi ruoli
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
