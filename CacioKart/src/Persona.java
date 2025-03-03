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
        db = new DBConnector();
        responder = new PHPResponseHandler();
        int idGara;
        String cfSocio,targa,bGiro,tempoTot;
        StringBuilder classifica = new StringBuilder();
        SELECT="SELECT g.idGara, g.socio, g.targa, g.pos, g.bGiro, g.tempTot " +
                "FROM ( " +
                "    SELECT  distinct idGara " +
                "    FROM classifica " +
                "    LIMIT 10 " +
                ") AS ultime_gare " +
                "JOIN classifica g ON ultime_gare.idGara = g.idGara " +
                "WHERE g.pos = 1 " +
                "ORDER BY g.tempTot DESC;";
        result = db.executeReturnQuery(SELECT);
        if(result!=null){
            for(Map<String, Object> row : result) {
               idGara= Integer.parseInt(row.get("idGara").toString());
               cfSocio = row.get("socio").toString();
               targa = row.get("targa").toString();
               bGiro = row.get("pos").toString();
               tempoTot = row.get("pos").toString();
                classifica.append(idGara).append(" ").append(cfSocio).append(" ").append(targa).append(" ").append(bGiro).append(" ").append(tempoTot).append("\n");

            }
            classifica.append("end");
            responder.sendResponse(clientSocket, classifica.toString());
        }else{
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