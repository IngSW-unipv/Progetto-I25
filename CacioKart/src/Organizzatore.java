import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Organizzatore {
    private DBConnector db;
    private PHPResponseHandler responder;
    private String SELECT;
    private List<Map<String, Object>> soci;
    private String cf;
    private String nome;
    private String cognome;
    private StringBuilder listaUtenti;
    private String[] INSERT;
    private int queryIndicator;
    private String idGara;
    private StringBuilder gara;
    private List<Map<String, Object>> result;

    public Organizzatore() {

    }

    public void creaGara(){

    };

    public void creaTeam(Team t, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        System.out.println("Nome del team: " + t.getNome());
        System.out.println("Colore del team: " + t.getColore());
        System.out.println("Nome dei soci: " + t.getSoci());

        INSERT = new String[3];
        INSERT[0] = "INSERT INTO caciokart.team (nome, colore) VALUES ('" + t.getNome() + "', '" + t.getColore() +"')";
        INSERT[1] = "INSERT INTO caciokart.appartenenza (socio, nome) VALUES ('" + t.getSoci()[0].getcF() +"', '" + t.getNome() + "')";
        INSERT[2] = "INSERT INTO caciokart.appartenenza (socio, nome) VALUES ('" + t.getSoci()[1].getcF() + "', '" + t.getNome() +"'); ";

        for (String team : INSERT) {
            queryIndicator = db.executeUpdateQuery(team);

            if (queryIndicator == 0) {
                responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
                return;
            }
        }

        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }

    public void creaCampionato(){

    }

    //solo gara campionato??
    public void inserimentoGara(){

    }

    public void mostraSociInserimento(Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = "SELECT socio, nome, cognome FROM caciokart.socio WHERE socio.socio NOT IN (SELECT socio FROM appartenenza)";
        soci = db.executeReturnQuery(SELECT);
        listaUtenti = new StringBuilder();

        if(soci != null) {
            for(Map<String, Object> row : soci) {
                cf = row.get("socio").toString();
                nome = row.get("nome").toString();
                cognome = row.get("cognome").toString();
                listaUtenti.append(cf).append(" ").append(nome).append(" ").append(cognome).append("\n");
            }
            listaUtenti.append("end");
            responder.sendResponse(clientSocket, listaUtenti.toString());

        }else{
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void mostraGara(String query, Socket clientSocket) throws SQLException {
        responder = new PHPResponseHandler();
        result = getGara(query);
        gara = new StringBuilder();

        if(result != null) {
            for(Map<String, Object> row : result) {
                idGara = row.get("idGara").toString();
                gara.append(idGara).append("\n");
            }
            gara.append("end");
            responder.sendResponse(clientSocket, gara.toString());

        }else{
            responder.sendResponse(clientSocket, "end");
        }
    }

    public List<Map<String, Object>> getGara(String SELECT) throws SQLException {
        db = new DBConnector();
        return db.executeReturnQuery(SELECT);

    }
}

