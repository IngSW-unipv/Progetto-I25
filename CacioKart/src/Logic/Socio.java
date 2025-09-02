package Logic;

import DAO.implementazioni.KartDAO;
import DAO.implementazioni.PezzoDAO;
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
    private TableMaker maker;
    private List<Map<String, Object>> result;

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
        db = DBConnector.getInstance();
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
    public void mostraKartUtente(Socket clientSocket) {
        KartDAO dao = new KartDAO();
        PHPResponseHandler responder = new PHPResponseHandler();

        List<Map<String, Object>> kartUtente = dao.getKartByCf(this.getCf());

        if (kartUtente != null && !kartUtente.isEmpty()) {
            TableMaker maker = new TableMaker();
            String tabella = maker.stringTableMaker(kartUtente, "targa", "cilindrata", "serbatoio");
            responder.sendResponse(clientSocket, tabella);
        } else {
            responder.sendResponse(clientSocket, "Nessun kart associato al socio.");
        }
    }
    /** Metodo per finalizzare l'acquisto dei pezzi da parte dell'utente.
     * Si riduce di 1 la quantità del pezzo disponibile nell'inventario del concessionario,
     * per poi associare all'utente l'id del pezzo acquistato nella tabella acquista.
     *
     * @param
     * @param clientSocket
     */
    public void acquistaPezzi(Pezzo pezzo, Socket clientSocket) {
        PezzoDAO dao = new PezzoDAO();
        PHPResponseHandler responder = new PHPResponseHandler();

        boolean successo = dao.acquistaPezzo(pezzo, this.getCf());

        if (successo) {
            responder.sendResponse(clientSocket, "✅ Pezzo acquistato con successo!");
        } else {
            responder.sendResponse(clientSocket, "❌ Errore durante l'acquisto del pezzo.");
        }
    }
    /**
     *
     * @param clientSocket
     */

    public void compraKart(Kart kart, Socket clientSocket) {
        KartDAO dao = new KartDAO();
        PHPResponseHandler responder = new PHPResponseHandler();

        boolean successo = dao.aggiungiKart(kart, this.getCf());

        if (successo) {
            responder.sendResponse(clientSocket, "✅ Kart acquistato con successo!");
        } else {
            responder.sendResponse(clientSocket, "❌ Errore durante l'acquisto del kart.");
        }
    }
}
