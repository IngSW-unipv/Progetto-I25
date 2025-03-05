import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Arbitro {
    private DBConnector db;
    private PHPResponseHandler responder;
    private String SELECT;
    private String idGara;
    private String tipol;
    private String quantita;
    private String prezzo;
    private List<Map<String, Object>> gare;
    private StringBuilder listaPezzi = new StringBuilder();

    public Arbitro() {

    }

}
