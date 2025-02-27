import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
                responder.sendResponse(clientSocket, "1 0");

            } else {
                SELECT = "SELECT * FROM caciokart.dipendente WHERE dip = '"
                        + this.getcF() + "' AND passw = '"
                        + this.getPassword() + "'";
                result = db.executeReturnQuery(SELECT);

                if (result.isEmpty()) {
                    responder.sendResponse(clientSocket, "0 0");

                } else {

                    ruolo = result.get(0).get("ruolo").toString();
                    ruoloConverter = Ruoli.requestedRole(ruolo);

                    switch (ruoloConverter) {
                        case MECCANICO:
                            responder.sendResponse(clientSocket, "1 1");
                            break;

                        case GESTORE:
                            responder.sendResponse(clientSocket, "1 2");
                            break;

                        case ARBITRO:
                            responder.sendResponse(clientSocket, "1 3");
                            break;

                        case ORGANIZZATORE:
                            responder.sendResponse(clientSocket, "1 4");
                            break;

                        case PROPRIETARIO:
                            responder.sendResponse(clientSocket, "1 5");
                            break;

                        default:
                            responder.sendResponse(clientSocket, "0 0");
                            System.out.println("Ruolo non trovato");
                            break;

                    }
                }
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