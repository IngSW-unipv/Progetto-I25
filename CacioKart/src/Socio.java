import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class Socio extends Persona implements Iinventario {
    private String SELECT, UPDATE, INSERT;
    private PHPResponseHandler responder;
    private int queryIndicator;
    private DBConnector db;
    private String[] querys;

    public Socio(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {
        super(nome, cognome, dataNascita, cF, mail, password);
    }

    public Socio() {

    }

    //metodo classifica gara?

    /**
     * Metodo per registrare un utente nel db
     * La query viene eseguita tramite il metodo sendResponse della classe DBConnector
     *
     * @param clientSocket
     */
    public void registrazione(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = Query.REGISTRAZIONE_SOCIO.getQuery(this.getCf(), this.getNome(), this.getCognome(), this.getMail(), this.getPassword(), this.getDataNascita());

        queryIndicator = db.executeUpdateQuery(SELECT);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));

    }

    public void compraKart(String info, Socket clientSocket) {
        // Inserisco la targa in quell'utente specifico
        String[] kartUtente = info.split(" ");
        String cf = kartUtente[0];
        String targa = kartUtente[1];
        db = new DBConnector();
        responder = new PHPResponseHandler();

        UPDATE = Query.ACQUISTO_KART_UTENTE_TABELLA_SOCIO.getQuery(targa, cf);
        SELECT = Query.ACQUISTO_KART_UTENTE_TROVA_ID_PRODOTTO.getQuery(targa);

        queryIndicator = db.executeUpdateQuery(UPDATE);
        if (queryIndicator == 0) {
            responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
            return;
        }

        List<Map<String, Object>> idProdotto = db.executeReturnQuery(SELECT);
        System.out.println("Ecco la targa che vogliamo acquistare: " + idProdotto);
        INSERT = Query.ACQUISTO_KART_UTENTE_TABELLA_ACQUISTA.getQuery(cf, idProdotto.toString().replaceAll("\\D", ""), LocalDate.now());
        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }


    public void acquistaPezzi(Pezzo p, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        UPDATE = "UPDATE concessionaria SET quantita = quantita - 1 WHERE idProdotto ='" + p.getIdProdotto() + "'";
        INSERT = "INSERT INTO acquista (socio, idProdotto, data) VALUES('" + this.getCf() + "', '" + p.getIdProdotto() + "', '" + LocalDate.now() + "')";
        querys = new String[2];
        querys[0] = UPDATE;
        querys[1] = INSERT;

        for (String prodotto : querys) {
            queryIndicator = db.executeUpdateQuery(prodotto);

            if (queryIndicator == 0) {
                responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
                return;
            }
        }
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }


}
