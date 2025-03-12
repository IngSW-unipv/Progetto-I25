import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Proprietario {
    private DBConnector db;
    private PHPResponseHandler responder;
    private String INSERT, SELECT, DELETE;
    private int queryIndicator;
    private List<Map<String, Object>> dipendenti;
    private String cf, nome, cognome;
    private StringBuilder listaDipen = new StringBuilder();

    public Proprietario() {

    }

    public void visioneBilancio() {

    }

    ;

    /**
     * Metodo per mostrare i dipendenti.
     * Identico al metodo presente in Kart per mostrare tutti i kart.
     *
     * @param clientSocket
     * @throws SQLException
     */
    public void mostraDipendenti(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = "SELECT * FROM caciokart.dipendente";
        dipendenti = db.executeReturnQuery(SELECT);

        if (dipendenti != null) {
            for (Map<String, Object> row : dipendenti) {
                cf = row.get("dip").toString();
                nome = row.get("nome").toString();
                cognome = row.get("cognome").toString();
                listaDipen.append(cf).append(" ").append(nome).append(" ").append(cognome).append("\n");
            }
            listaDipen.append("end");
            responder.sendResponse(clientSocket, listaDipen.toString());

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    /**
     * Metodo per aggiungere i dipendenti.
     * Pressoché identico al metodo presente in Kart per aggiungere i kart,
     * tranne la gestione dei ruoli tramite l'ENUM Ruoli
     *
     * @param nuovoDip
     * @param clientSocket
     * @throws SQLException
     */
    public void aggiuntaDipendenti(Dipendente nuovoDip, Socket clientSocket) {
        db = new DBConnector();
        //Gestire i diversi ruoli
        responder = new PHPResponseHandler();
        INSERT = "INSERT INTO dipendente (dip, nome, cognome, mail, passw, dataN, ruolo, oreL, stipendio) VALUES('" +
                nuovoDip.getCf() + "', '" +
                nuovoDip.getNome() + "', '" +
                nuovoDip.getCognome() + "', '" +
                nuovoDip.getMail() + "', '" +
                nuovoDip.getPassword() + "', '" +
                nuovoDip.getDataNascita() + "', '" +
                nuovoDip.getRuolo() + "', '" +
                nuovoDip.getOreL() + "', '" +
                nuovoDip.getStipendio() + "')";

        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }

    ;

    /**
     * Metodo per rimuovere i dipendenti.
     * Identico al metodo presente in Kart per rimuovere i kart.
     *
     * @param cfDaRimuovere
     * @param clientSocket
     * @throws SQLException
     */
    public void rimozioneDipendenti(String cfDaRimuovere, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        DELETE = "DELETE FROM caciokart.dipendente WHERE dip = '" + cfDaRimuovere + "'";
        queryIndicator = db.executeUpdateQuery(DELETE);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }
}
