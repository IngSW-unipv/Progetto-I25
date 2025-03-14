import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Proprietario {
    private DBConnector db;
    private PHPResponseHandler responder;
    private String INSERT, SELECT, DELETE;
    private int queryIndicator;

    public Proprietario() {

    }

    /**
     * Metodo per mostrare i dipendenti.
     * Identico al metodo presente in Kart per mostrare tutti i kart.
     *
     * @param clientSocket Socket per inviare la risposta
     */
    public void mostraDipendenti(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = Query.MOSTRA_DIPENDENTI_PROPRIETARIO.getQuery();
        List<Map<String, Object>> dipendenti = db.executeReturnQuery(SELECT);

        StringBuilder listaDipen = new StringBuilder();
        String cf, nome, cognome;

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
     * @param nuovoDip Il dipendente da aggiungere
     * @param clientSocket Socket per inviare la risposta
     */
    public void aggiuntaDipendente(Dipendente nuovoDip, Socket clientSocket) {
        db = new DBConnector();
        //Gestire i diversi ruoli
        responder = new PHPResponseHandler();
        INSERT = Query.AGGIUNTA_DIPENDENTE_PROPRIETARIO.getQuery(
                nuovoDip.getCf(),
                nuovoDip.getNome(),
                nuovoDip.getCognome(),
                nuovoDip.getMail(),
                nuovoDip.getPassword(),
                nuovoDip.getDataNascita(),
                nuovoDip.getRuolo(),
                nuovoDip.getOreL(),
                nuovoDip.getStipendio());

        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }

    /**
     * Metodo per rimuovere i dipendenti.
     * Identico al metodo presente in Kart per rimuovere i kart.
     *
     * @param d Il dipendente da rimuovere
     * @param clientSocket Socket per inviare la risposta
     */
    public void rimozioneDipendenti(Dipendente d, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        DELETE = Query.RIMOZIONE_DIPENDENTE_PROPRIETARIO.getQuery(d.getCf());
        queryIndicator = db.executeUpdateQuery(DELETE);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
    }
}
