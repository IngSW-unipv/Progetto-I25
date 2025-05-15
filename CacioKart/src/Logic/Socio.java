package Logic;

import Enums.Query;
import Objects.Kart;
import Objects.Pezzo;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Socio extends Persona {
    private String SELECT, UPDATE, INSERT;
    private PHPResponseHandler responder;
    private String queryIndicator;
    private DBConnector db;

    public Socio(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {
        super(nome, cognome, dataNascita, cF, mail, password);
    }

    public Socio() {

    }

    /** Metodo per registrare un utente nel db
     * La query viene eseguita tramite il metodo sendResponse della classe Logic.DBConnector
     *
     * @param clientSocket Il socket di risposta
     */
    public void registrazione(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = Query.REGISTRAZIONE_SOCIO.getQuery(this.getCf(), this.getNome(), this.getCognome(), this.getMail(), this.getPassword(), this.getDataNascita());

        queryIndicator = db.executeUpdateQuery(SELECT);
        responder.sendResponse(clientSocket, queryIndicator);

    }

    /** Metodo per associare un kart a un socio.
     * Dopo aver associato la targa al socio, si inserisce in acquista
     * la tupla che indica l'avvenuto acquisto.
     *
     * @param k Il kart da associare
     * @param clientSocket Il socket di risposta
     */
    public void compraKart(Kart k, Socket clientSocket) {
        String cf = this.getCf();
        String targa = k.getTarga();
        db = new DBConnector();
        responder = new PHPResponseHandler();

        UPDATE = Query.ACQUISTO_KART_UTENTE_TABELLA_SOCIO.getQuery(targa, cf);
        SELECT = Query.ACQUISTO_KART_UTENTE_TROVA_ID_PRODOTTO.getQuery(targa);

        queryIndicator = db.executeUpdateQuery(UPDATE);
        if (queryIndicator == "0") {
            responder.sendResponse(clientSocket, queryIndicator);
            return;
        }

        List<Map<String, Object>> idProdotto = db.executeReturnQuery(SELECT);
        //System.out.println("Ecco la targa che vogliamo acquistare: " + idProdotto);
        INSERT = Query.ACQUISTO_KART_UTENTE_TABELLA_ACQUISTA.getQuery(cf, idProdotto.get(0).get("idProdotto").toString(), LocalDateTime.now());
        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryIndicator);
    }

    /** Metodo per finalizzare l'acquisto dei pezzi da parte dell'utente.
     * Si riduce di 1 la quantità del pezzo disponibile nell'inventario del concessionario,
     * per poi associare all'utente l'id del pezzo acquistato nella tabella acquista.
     *
     * @param p
     * @param clientSocket
     */
    public void acquistaPezzi(Pezzo p, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        UPDATE = Query.ACQUISTA_PEZZI_TABELLA_CONCESSIONARIA.getQuery(p.getIdProdotto());
        INSERT = Query.ACQUISTA_PEZZI_TABELLA_ACQUISTA.getQuery(this.getCf(), p.getIdProdotto(), LocalDateTime.now());
        String[] querys = new String[2];
        querys[0] = UPDATE;
        querys[1] = INSERT;

        for (String prodotto : querys) {
            queryIndicator = db.executeUpdateQuery(prodotto);

            if (queryIndicator == "0") {
                responder.sendResponse(clientSocket, queryIndicator);
                return;
            }
        }
        responder.sendResponse(clientSocket, queryIndicator);
    }


}
