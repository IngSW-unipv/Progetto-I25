import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Classifica {

    public Classifica() {
    }

    public void classificaUtente(String query, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        // Dichiarazione variabili per i dati estratti
        String nomep, cognomep, targa, bGiro, tempoTot, idGara;

        // Query corretta: metti gli apici attorno al valore e aggiungi "AS nomePilota" / "AS cognomePilota"
        String SELECT =
                "SELECT c.idGara, " +
                        "       c.targa, " +
                        "       c.bGiro, " +
                        "       c.tempTot " +
                        "FROM caciokart.classifica AS c " +
                        "JOIN caciokart.socio AS s ON c.socio = s.socio " +
                        // Importante: apici e spazio!
                        "WHERE s.nome = '" + cfPilota + "' " +
                        "ORDER BY c.tempTot DESC " +
                        "LIMIT 10";

        // Esegui la query
        List<Map<String, Object>> result = db.executeReturnQuery(SELECT);

        StringBuilder classifica = new StringBuilder();

        if (result != null) {
            for (Map<String, Object> row : result) {
                idGara    = row.get("idGara").toString();
                targa     = row.get("targa").toString();
                bGiro     = row.get("bGiro").toString();
                tempoTot  = row.get("tempTot").toString();

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
}
