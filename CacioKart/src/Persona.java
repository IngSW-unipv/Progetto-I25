import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Persona {
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String cF;
    private String mail;
    private String password;
    private String SELECT;
    private List<Map<String, Object>> result;
    private String ruolo;
    private DBConnector db;
    private PHPResponseHandler responder;
    private Ruoli ruoloConverter;

    public Persona(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.cF = cF;
        this.mail = mail;
        this.password = password;
    }

    /**Metodo per effettuare la login.
     * Il chiamante deve fornire il socket da utilizzare per rispondere al client.
     * Dopo aver controllato il db, il metodo invierà una risposta di questo tipo: *1 o 0* *valore del ruolo*
     *
     * @param clientSocket
     */
    public void login(Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = "SELECT * FROM caciokart.socio WHERE socio = '"
                + this.getcF() + "' AND passw = '"
                + this.getPassword() + "'";

            result = db.executeReturnQuery(SELECT);

            if (!result.isEmpty()) {
                nome = result.get(0).get("nome").toString();
                responder.sendResponse(clientSocket, "1 0 " + nome);

            } else {
                SELECT = "SELECT * FROM caciokart.dipendente WHERE dip = '"
                        + this.getcF() + "' AND passw = '"
                        + this.getPassword() + "'";
                result = db.executeReturnQuery(SELECT);

                if (result.isEmpty()) {
                    responder.sendResponse(clientSocket, "0 0 0");

                } else {
                    nome = result.get(0).get("nome").toString();
                    ruolo = result.get(0).get("ruolo").toString();
                    ruoloConverter = Ruoli.requestedRole(ruolo);

                    switch (ruoloConverter) {
                        case MECCANICO:
                            responder.sendResponse(clientSocket, "1 1 " + nome);
                            break;

                        case GESTORE:
                            responder.sendResponse(clientSocket, "1 2 " + nome);
                            break;

                        case ARBITRO:
                            responder.sendResponse(clientSocket, "1 3 " + nome);
                            break;

                        case ORGANIZZATORE:
                            responder.sendResponse(clientSocket, "1 4 " + nome);
                            break;

                        case PROPRIETARIO:
                            responder.sendResponse(clientSocket, "1 5 " + nome);
                            break;

                        default:
                            responder.sendResponse(clientSocket, "0 0 0");
                            System.out.println("Ruolo non trovato");
                            break;

                    }
                }
            }

    }

    public void classificaG(Socket clientSocket) throws SQLException {
        // Inizializza il connettore al DB e il responder (i tuoi oggetti).
        db = new DBConnector();
        responder = new PHPResponseHandler();

        // Dichiarazione variabili per i dati estratti
        String nomep, cognomep, targa, bGiro, tempoTot, idGara;

        // Costruiamo la query correttamente con gli spazi necessari
        String SELECT =
                "SELECT c.idGara, s.nome, s.cognome, c.targa, c.bGiro, c.tempTot " +
                        "FROM caciokart.classifica AS c " +
                        "JOIN (SELECT idGara, MIN(tempTot) AS tempo_migliore FROM caciokart.classifica GROUP BY idGara) AS agg " +
                        "ON c.idGara = agg.idGara AND c.tempTot = agg.tempo_migliore " +
                        "JOIN caciokart.socio AS s ON c.socio = s.socio " +
                        "ORDER BY c.tempTot DESC " +
                        "LIMIT 10";

        // Esecuzione della query
        List<Map<String, Object>> result = db.executeReturnQuery(SELECT);

        StringBuilder classifica = new StringBuilder();

        if (result != null) {
            // Iterazione sui risultati
            for (Map<String, Object> row : result) {
                idGara = row.get("idGara").toString();
                nomep = row.get("nome").toString();
                cognomep = row.get("cognome").toString();
                targa = row.get("targa").toString();
                bGiro = row.get("bGiro").toString();
                tempoTot = row.get("tempTot").toString();

                // Componiamo la riga di output come preferisci
                classifica.append(idGara).append(" ")
                        .append(nomep).append(" ")
                        .append(cognomep).append(" ")
                        .append(targa).append(" ")
                        .append(bGiro).append(" ")
                        .append(tempoTot).append("\n");
            }

            // Aggiungiamo un marcatore di fine, se serve
            classifica.append("end");

            // Invio della risposta al client
            responder.sendResponse(clientSocket, classifica.toString());
        } else {
            // Se la query non ha restituito risultati
            responder.sendResponse(clientSocket, "end");
        }
    }

    public void classificaUtente(String cfPilota, Socket clientSocket) throws SQLException {
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




    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getcF() {
        return cF;
    }

    public void setcF(String cF) {
        this.cF = cF;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}