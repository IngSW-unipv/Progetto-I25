import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Classifica {
    private DBConnector db;
    private PHPResponseHandler responder;
    private StringBuilder classifica;
    private List<Map<String, Object>> result;
    private String nomep, cognomep, targa, bGiro, tempoTot, idGara, cf, SELECT;

    public Classifica() {
    }

    public void classificaArbitro( Socket clientSocket) {
        responder = new PHPResponseHandler();
        SELECT = Query.CLASSIFICA_ARBITRO.getQuery();
        result = getClassifica(SELECT);
        classifica = new StringBuilder();

        if (result != null) {
            for (Map<String, Object> row : result) {
                idGara = row.get("idGara").toString();
                classifica.append(idGara).append("\n");
            }
            classifica.append("end");
            responder.sendResponse(clientSocket, classifica.toString());

        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void classificaCompleta(Socket clientSocket) {
        // Inizializza il responder.
        responder = new PHPResponseHandler();
        SELECT = Query.CLASSIFICA_GENERALE.getQuery();
        // Esecuzione della query
        result = getClassifica(SELECT);

        classifica = new StringBuilder();

        if (result != null) {
            // Iterazione sui risultati
            for (Map<String, Object> row : result) {
                idGara = row.get("idGara").toString();
                nomep = row.get("nome").toString();
                cognomep = row.get("cognome").toString();
                targa = row.get("targa").toString();
                bGiro = row.get("bGiro").toString();
                tempoTot = row.get("tempTot").toString();

                // Componiamo la riga di output
                classifica.append(idGara).append(" ")
                        .append(nomep).append(" ")
                        .append(cognomep).append(" ")
                        .append(targa).append(" ")
                        .append(bGiro).append(" ")
                        .append(tempoTot).append("\n");
            }

            // Aggiungiamo un marcatore di fine
            classifica.append("end");

            // Invio della risposta al client
            responder.sendResponse(clientSocket, classifica.toString());
        } else {
            // Se la query non ha restituito risultati
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void classificaUtente(Socio s, Socket clientSocket) {
        responder = new PHPResponseHandler();
        // Esegui la query
        SELECT = Query.CLASSIFICA_UTENTE.getQuery(s.getCf());
        result = getClassifica(SELECT);
        classifica = new StringBuilder();

        if (result != null) {
            for (Map<String, Object> row : result) {
                idGara = row.get("idGara").toString();
                targa = row.get("targa").toString();
                bGiro = row.get("bGiro").toString();
                tempoTot = row.get("tempTot").toString();

                classifica.append(idGara).append(" ")
                        .append(targa).append(" ")
                        .append(bGiro).append(" ")
                        .append(tempoTot).append("\n");
            }
            classifica.append("end");
            responder.sendResponse(clientSocket, classifica.toString());
        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void classificaPenalità(String query, Socket clientSocket) {
        // Inizializza il responder.
        responder = new PHPResponseHandler();

        // Esecuzione della query
        result = getClassifica(query);

        classifica = new StringBuilder();

        if (result != null) {
            // Iterazione sui risultati
            for (Map<String, Object> row : result) {
                idGara = row.get("idGara").toString();
                cf = row.get("socio").toString();
                targa = row.get("targa").toString();
                bGiro = row.get("bGiro").toString();
                tempoTot = row.get("tempTot").toString();

                // Componiamo la riga di output
                classifica.append(idGara).append(" ")
                        .append(cf).append(" ")
                        .append(targa).append(" ")
                        .append(bGiro).append(" ")
                        .append(tempoTot).append("\n");
            }

            // Aggiungiamo un marcatore di fine
            classifica.append("end");

            // Invio della risposta al client
            responder.sendResponse(clientSocket, classifica.toString());
        } else {
            // Se la query non ha restituito risultati
            responder.sendResponse(clientSocket, "end");
        }
    }

    public List<Map<String, Object>> getClassifica(String SELECT) {
        db = new DBConnector();
        return db.executeReturnQuery(SELECT);

    }
}
