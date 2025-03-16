package Logic;

import Enums.Query;
import Enums.Ruoli;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Persona {
    private String nome, cognome, cf, mail, password, ruolo;
    private LocalDate dataNascita;
    private String SELECT;
    private List<Map<String, Object>> result;
    private DBConnector db;
    private PHPResponseHandler responder;
    private Ruoli ruoloConverter;

    public Persona(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.cf = cF;
        this.mail = mail;
        this.password = password;
    }

    /**
     * Costruttore alternativo per chiamare i metodi
     */
    public Persona() {

    }

    /**
     * Metodo per effettuare la login.
     * Il chiamante deve fornire il socket da utilizzare per rispondere al client.
     * Dopo aver controllato il db, il metodo invierà una risposta di questo tipo: *1 o 0* *valore del ruolo*
     *
     * @param clientSocket
     */
    public void login(Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        SELECT = Query.LOGIN_SOCIO.getQuery(this.getCf(), this.getPassword());

        result = db.executeReturnQuery(SELECT);

        if (!result.isEmpty()) {
            nome = result.get(0).get("nome").toString();
            responder.sendResponse(clientSocket, "1 0 " + nome);

        } else {
            SELECT = Query.LOGIN_DIPENDENTE.getQuery(this.getCf(), this.getPassword());
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

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
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